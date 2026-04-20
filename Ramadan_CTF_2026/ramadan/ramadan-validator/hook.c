#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdarg.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

typedef void* (*dlsym_fn_t)(void*, const char*);
typedef void* (*dlopen_fn_t)(const char*, int);

static __thread int g_in_hook = 0;

static dlsym_fn_t get_real_dlsym(void) {
    static dlsym_fn_t real_dlsym = NULL;
    if (!real_dlsym) {
        real_dlsym = (dlsym_fn_t)dlvsym(RTLD_NEXT, "dlsym", "GLIBC_2.2.5");
    }
    return real_dlsym;
}

static void* get_real_symbol(const char* name) {
    dlsym_fn_t real_dlsym = get_real_dlsym();
    return real_dlsym ? real_dlsym(RTLD_NEXT, name) : NULL;
}

static void safe_log(const char* fmt, ...) {
    if (g_in_hook) {
        return;
    }
    g_in_hook = 1;
    char buf[512];
    va_list ap;
    va_start(ap, fmt);
    int n = vsnprintf(buf, sizeof(buf), fmt, ap);
    va_end(ap);
    if (n > 0) {
        if (n > (int)sizeof(buf)) {
            n = (int)sizeof(buf);
        }
        write(2, buf, (size_t)n);
    }
    g_in_hook = 0;
}

static void preview_string(const char* s, char* out, size_t out_sz) {
    if (!s || out_sz == 0) {
        return;
    }
    size_t j = 0;
    for (size_t i = 0; i < 48 && s[i] != '\0' && j + 2 < out_sz; i++) {
        unsigned char c = (unsigned char)s[i];
        out[j++] = (c >= 32 && c <= 126) ? (char)c : '.';
    }
    out[j] = '\0';
}

void* dlsym(void* handle, const char* symbol) {
    dlsym_fn_t real_dlsym = get_real_dlsym();
    safe_log("[HOOK] dlsym(%p, %s)\n", handle, symbol ? symbol : "<null>");
    return real_dlsym(handle, symbol);
}

void* dlopen(const char* filename, int flags) {
    static dlopen_fn_t real_dlopen = NULL;
    if (!real_dlopen) {
        real_dlopen = (dlopen_fn_t)get_real_symbol("dlopen");
    }
    safe_log("[HOOK] dlopen(%s, 0x%x)\n", filename ? filename : "<null>", flags);
    return real_dlopen(filename, flags);
}

size_t strlen(const char* s) {
    static size_t (*real_strlen)(const char*) = NULL;
    if (!real_strlen) {
        real_strlen = (size_t (*)(const char*))get_real_symbol("strlen");
    }
    if (!real_strlen) {
        return 0;
    }
    size_t len = real_strlen(s);
    if (!g_in_hook && s) {
        char preview[64] = {0};
        preview_string(s, preview, sizeof(preview));
        safe_log("[HOOK] strlen(ptr=%p, len=%zu, text='%s')\n", (void*)s, len, preview);
    }
    return len;
}

size_t strcspn(const char* s, const char* reject) {
    static size_t (*real_strcspn)(const char*, const char*) = NULL;
    if (!real_strcspn) {
        real_strcspn = (size_t (*)(const char*, const char*))get_real_symbol("strcspn");
    }
    size_t r = real_strcspn ? real_strcspn(s, reject) : 0;
    if (!g_in_hook) {
        char p1[64] = {0};
        char p2[64] = {0};
        if (s) preview_string(s, p1, sizeof(p1));
        if (reject) preview_string(reject, p2, sizeof(p2));
        safe_log("[HOOK] strcspn(s='%s', reject='%s') => %zu\n", p1, p2, r);
    }
    return r;
}

char* fgets(char* s, int size, FILE* stream) {
    static char* (*real_fgets)(char*, int, FILE*) = NULL;
    if (!real_fgets) {
        real_fgets = (char* (*)(char*, int, FILE*))get_real_symbol("fgets");
    }
    char* ret = real_fgets ? real_fgets(s, size, stream) : NULL;
    if (!g_in_hook && ret) {
        char preview[64] = {0};
        preview_string(ret, preview, sizeof(preview));
        safe_log("[HOOK] fgets(size=%d) -> '%s'\n", size, preview);
    }
    return ret;
}
