#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <dlfcn.h>
#include <stdint.h>
#include <stdarg.h>
#include <pthread.h>

static FILE *log_file = NULL;
static __thread int in_hook = 0;

void init_log() {
    if (!log_file) {
        log_file = fopen("/tmp/validator_hook.log", "a");
    }
}

// Hook memcmp to see what's being compared
int memcmp(const void *s1, const void *s2, size_t n) {
    static int (*real_memcmp)(const void*, const void*, size_t) = NULL;
    if (!real_memcmp) {
        real_memcmp = (int (*)(const void*, const void*, size_t)) dlsym(RTLD_NEXT, "memcmp");
    }
    
    if (!in_hook) {
        in_hook = 1;
        init_log();
        
        // Log comparison
        fprintf(log_file, "[MEMCMP] len=%zu | ", n);
        fprintf(log_file, "s1=");
        for (size_t i = 0; i < (n < 32 ? n : 32); i++) {
            fprintf(log_file, "%02x ", ((unsigned char*)s1)[i]);
        }
        fprintf(log_file, " | s2=");
        for (size_t i = 0; i < (n < 32 ? n : 32); i++) {
            fprintf(log_file, "%02x ", ((unsigned char*)s2)[i]);
        }
        fprintf(log_file, "\n");
        fflush(log_file);
        
        in_hook = 0;
    }
    
    return real_memcmp(s1, s2, n);
}

// Hook strcmp too
int strcmp(const char *s1, const char *s2) {
    static int (*real_strcmp)(const char*, const char*) = NULL;
    if (!real_strcmp) {
        real_strcmp = (int (*)(const char*, const char*)) dlsym(RTLD_NEXT, "strcmp");
    }
    
    if (!in_hook) {
        in_hook = 1;
        init_log();
        
        fprintf(log_file, "[STRCMP] s1='%s' | s2='%s'\n", s1, s2);
        fflush(log_file);
        
        in_hook = 0;
    }
    
    return real_strcmp(s1, s2);
}

// Hook strncmp
int strncmp(const char *s1, const char *s2, size_t n) {
    static int (*real_strncmp)(const char*, const char*, size_t) = NULL;
    if (!real_strncmp) {
        real_strncmp = (int (*)(const char*, const char*, size_t)) dlsym(RTLD_NEXT, "strncmp");
    }
    
    if (!in_hook) {
        in_hook = 1;
        init_log();
        
        char s1_safe[65] = {0};
        char s2_safe[65] = {0};
        strncpy(s1_safe, s1, n < 64 ? n : 64);
        strncpy(s2_safe, s2, n < 64 ? n : 64);
        
        fprintf(log_file, "[STRNCMP] n=%zu | s1='%s' | s2='%s'\n", n, s1_safe, s2_safe);
        fflush(log_file);
        
        in_hook = 0;
    }
    
    return real_strncmp(s1, s2, n);
}
