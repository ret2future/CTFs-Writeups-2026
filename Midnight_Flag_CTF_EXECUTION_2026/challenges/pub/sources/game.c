#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "shadow_stack.h"

void win(void) { puts("You found the light you were looking for. You are saved!");execve("/bin/sh", (char *[]){"/bin/sh", NULL}, NULL); }

static int nights   = 0;   /* score  */
static int entities = 0;   /* secret */

static void new_night(void) { entities = rand() % 256; }


static void do_stats(void) {
    printf("  Survivor : %.63s\n  Nights survived: %d\n", username, nights);
}


static void do_rename(void) {
    printf("  New callsign: ");
    read(0, username, 16);
}

void play(void);


static void do_reset(void) {
    puts("\n  You died. The darkness takes you.\n  But something pulls you back...\n");
    nights = 0;
    new_night();
    play();
}

void play(void) {
    shadow_stack_push((uintptr_t)__builtin_return_address(0));
    char buf[32];
    new_night();
    printf("\n=== REVENANT ===\n"
           "You wake up in the dark. Again.\n"
           "Survivor name:\n");
    read(0, username, 16);

    int choice;
    do {
        printf("\n[1] Count the entities  [2] Status  [3] Change callsign"
               "  [4] Die and restart  [0] Flee\n> ");
        scanf("%d%*c", &choice);
        switch (choice) {
            case 1:
                printf("  How many entities do you hear? (0-255):\n");
                read(0, buf, 128);
                int n = atoi(buf);
                if (n == entities) {
                    puts("  >> You survive the night...");
                    nights++;
                    new_night();
                } else {
                    printf("  >> Wrong. They are %s.\n", n < entities ? "more" : "fewer");
                }
                break;
            case 2: do_stats();  break;
            case 3: do_rename(); break;
            case 4: do_reset();  choice = 0; break;
        }
    } while (choice != 0);

    if (!shadow_stack_pop((uintptr_t)__builtin_return_address(0))) {
        puts("  [!] Something is wrong with your memory...");
        _exit(1);
    }
}

int main(void) {
    shadow_stack_init();
    srand(1337);
    play();
    printf("\n  You flee into the darkness, never to be seen again...\n");
    return 0;
}
