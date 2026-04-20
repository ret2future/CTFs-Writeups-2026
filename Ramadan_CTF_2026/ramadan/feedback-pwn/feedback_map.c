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
    memset(name, 'N', 0xff);
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

static void del_fb(int fd, uint64_t id) {
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = NULL };
    xioctl(fd, CMD_DELETE, &req);
}

static void map_for_size(int fd, uint64_t size, int n, uint64_t base) {
    printf("\n=== map size=0x%llx n=%d base=%llu ===\n", (unsigned long long)size, n, (unsigned long long)base);

    for (int i = 0; i < n; i++) {
        unsigned char fill = (unsigned char)(0x30 + (i % 60));
        unsigned char ov = (unsigned char)(0x80 + (i % 120));
        if (create_fb(fd, base + (uint64_t)i, size, fill, ov) != 0) {
            printf("create fail i=%d errno=%d\n", i, errno);
            n = i;
            break;
        }
    }

    char *out = calloc(1, size + 0x20);
    if (!out) exit(1);

    int muts = 0;
    for (int j = 0; j < n; j++) {
        uint64_t id = base + (uint64_t)j;
        unsigned char expected = (unsigned char)(0x30 + (j % 60));
        if (read_fb(fd, id, out, size + 0x20) != 0) continue;
        unsigned char got = (unsigned char)out[0];
        if (got != expected) {
            muts++;
            printf("mut id=%llu idx=%d expected=0x%02x got=0x%02x\n",
                   (unsigned long long)id, j, expected, got);
        }
    }
    printf("mutations=%d\n", muts);

    free(out);
    for (int i = 0; i < n; i++) del_fb(fd, base + (uint64_t)i);
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    map_for_size(fd, 0x30, 160, 50000);
    map_for_size(fd, 0x40, 96, 100000);
    map_for_size(fd, 0x80, 64, 200000);

    close(fd);
    return 0;
}
