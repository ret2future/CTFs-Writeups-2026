#define _GNU_SOURCE
#include <dlfcn.h>
#include <link.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>

typedef int (*zfunc_t)(int ch, int idx);
static zfunc_t g_z = NULL;
static __thread int g_in_hook = 0;

static int phdr_cb(struct dl_phdr_info *info, size_t size, void *data) {
    (void)size;
    uintptr_t *base = (uintptr_t *)data;
    if (info->dlpi_name == NULL || info->dlpi_name[0] == '\0') {
        *base = (uintptr_t)info->dlpi_addr;
        return 1;
    }
    return 0;
}

static void init_symbols(void) {
    if (g_z) return;
    uintptr_t base = 0;
    dl_iterate_phdr(phdr_cb, &base);
    if (!base) return;
    g_z = (zfunc_t)(base + 0x3440);
}

static void dump_candidates(void) {
    if (!g_z) return;
    fprintf(stderr, "[PROBE] dumping candidate chars per index\n");
    for (int idx = 0; idx < 80; idx++) {
        char out[256];
        int p = 0;
        for (int ch = 32; ch <= 126; ch++) {
            int ok = g_z(ch, idx);
            if (ok) {
                if (p < (int)sizeof(out) - 2) out[p++] = (char)ch;
            }
        }
        out[p] = '\0';
        fprintf(stderr, "[PROBE] idx=%02d ok='%s'\n", idx, out);
    }
}

char *fgets(char *s, int size, FILE *stream) {
    static char *(*real_fgets)(char *, int, FILE *) = NULL;
    if (!real_fgets) real_fgets = (char *(*)(char *, int, FILE *))dlsym(RTLD_NEXT, "fgets");
    char *ret = real_fgets ? real_fgets(s, size, stream) : NULL;
    if (!ret) return ret;

    if (!g_in_hook) {
        g_in_hook = 1;
        init_symbols();
        dump_candidates();
        g_in_hook = 0;
    }

    return ret;
}
