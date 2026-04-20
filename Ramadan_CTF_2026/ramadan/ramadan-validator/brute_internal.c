#define _GNU_SOURCE
#include <dlfcn.h>
#include <link.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>

typedef int (*zW_fn_t)(int, int);
typedef int (*hIK_fn_t)(const char *);

int main(void) {
    void *h = dlopen("./validator", RTLD_NOW);
    if (!h) {
        fprintf(stderr, "dlopen failed: %s\n", dlerror());
        return 1;
    }

    struct link_map *lm = NULL;
    if (dlinfo(h, RTLD_DI_LINKMAP, &lm) != 0 || !lm) {
        fprintf(stderr, "dlinfo failed\n");
        return 1;
    }

    uintptr_t base = (uintptr_t)lm->l_addr;
    zW_fn_t zW = (zW_fn_t)(base + 0x3440);
    hIK_fn_t hIK = (hIK_fn_t)(base + 0x1ac0);

    printf("base=%p zW=%p hIK=%p\n", (void *)base, (void *)zW, (void *)hIK);

    char cand[256];
    memset(cand, 0, sizeof(cand));

    int found_any = 0;
    for (int idx = 0; idx < 80; idx++) {
        int count = 0;
        int first = -1;
        printf("idx %02d: ", idx);
        for (int c = 32; c <= 126; c++) {
            int r = zW(c, idx);
            if (r == 0) {
                count++;
                if (first == -1) first = c;
                printf("%c", c);
            }
        }
        printf(" (count=%d)\n", count);
        if (count == 0) {
            cand[idx] = '\0';
            break;
        }
        found_any = 1;
        cand[idx] = (char)first;
    }

    if (found_any) {
        printf("candidate=%s\n", cand);
        int rv = hIK(cand);
        printf("hIK(candidate)=%d\n", rv);
    }

    return 0;
}
