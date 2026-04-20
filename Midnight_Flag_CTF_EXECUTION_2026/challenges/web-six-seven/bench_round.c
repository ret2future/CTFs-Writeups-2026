#include <stdint.h>
#include <stdio.h>
#include <time.h>

static inline uint32_t step(uint32_t x) {
    x ^= x << 13;
    x ^= x >> 17;
    x ^= x << 5;
    x *= 0x2545F491u;
    return x;
}

static inline uint32_t round_out(uint32_t seed) {
    uint32_t x = seed;
    for (int i = 0; i < 1000; i++) {
        x = step(x);
    }
    return x ^ seed;
}

int main(void) {
    const uint32_t prev = 0xDEADBEEFu;
    const uint32_t target = 0x29A5B7CCu;

    const uint32_t limit = 1000000u;
    struct timespec t0, t1;
    clock_gettime(CLOCK_MONOTONIC, &t0);

    uint32_t found = 0;
    int ok = 0;
    for (uint32_t b = 0; b < limit; b++) {
        uint32_t seed = prev ^ b;
        if (round_out(seed) == target) {
            found = b;
            ok = 1;
            break;
        }
    }

    clock_gettime(CLOCK_MONOTONIC, &t1);
    double dt = (double)(t1.tv_sec - t0.tv_sec) + (double)(t1.tv_nsec - t0.tv_nsec) / 1e9;

    printf("checked=%u time=%.3f s rate=%.1f M/s\n", limit, dt, (limit / dt) / 1e6);
    if (ok) {
        printf("found=%06x\n", found & 0xFFFFFFu);
    } else {
        printf("not found in range\n");
    }

    return 0;
}
