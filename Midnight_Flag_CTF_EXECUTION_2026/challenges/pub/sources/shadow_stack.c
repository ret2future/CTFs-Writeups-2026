#define _GNU_SOURCE
#include <string.h>
#include <sys/mman.h>
#include <stdio.h>
#include <stdlib.h>
#include "shadow_stack.h"

__attribute__((section(".bss"), aligned(4096)))
uintptr_t shadow_stack[SHADOW_STACK_SIZE];


char username[64];
size_t shadow_stack_ptr = 0;

static uintptr_t *shadow_stack_base = NULL;
static size_t shadow_stack_page_size = 0;


void shadow_stack_init(void) {
    if (shadow_stack_base != NULL) {
        return;
    }
    
    shadow_stack_base = (uintptr_t *)((uintptr_t)shadow_stack & ~(4095UL));
    shadow_stack_page_size = ((SHADOW_STACK_SIZE * sizeof(uintptr_t)) + 4095) & ~4095UL;
    
    if (mprotect(shadow_stack_base, shadow_stack_page_size, PROT_READ) != 0) {
        perror("mprotect PROT_READ failed");
        exit(EXIT_FAILURE);
    }
    
    shadow_stack_ptr = 0;
}

static inline void shadow_stack_make_rw(void) {
    if (shadow_stack_base == NULL) {
        fprintf(stderr, "Shadow stack not initialized!\n");
        exit(EXIT_FAILURE);
    }
    
    if (mprotect(shadow_stack_base, shadow_stack_page_size, PROT_READ | PROT_WRITE) != 0) {
        perror("mprotect PROT_READ|PROT_WRITE failed");
        exit(EXIT_FAILURE);
    }
}

static inline void shadow_stack_make_ro(void) {
    if (mprotect(shadow_stack_base, shadow_stack_page_size, PROT_READ) != 0) {
        perror("mprotect PROT_READ failed");
        exit(EXIT_FAILURE);
    }
}

void shadow_stack_push(uintptr_t ret_addr) {
    if (shadow_stack_base == NULL) {
        fprintf(stderr, "Shadow stack not initialized!\n");
        exit(EXIT_FAILURE);
    }
    shadow_stack_make_rw();
    shadow_stack[shadow_stack_ptr] = ret_addr;
    shadow_stack_make_ro();
    shadow_stack_ptr++;
}

int shadow_stack_pop(uintptr_t ret_addr) {
    if (shadow_stack_base == NULL) {
        fprintf(stderr, "Shadow stack not initialized!\n");
        exit(EXIT_FAILURE);
    }
    if (shadow_stack_ptr == 0) {
        fprintf(stderr, "Shadow stack underflow!\n");
        exit(EXIT_FAILURE);
    }

    uintptr_t stored = shadow_stack[--shadow_stack_ptr];
    return stored == ret_addr;
}

size_t shadow_stack_depth(void) {
    return shadow_stack_ptr;
}

void shadow_stack_reset(void) {
    if (shadow_stack_base != NULL) {
        shadow_stack_make_rw();
        shadow_stack_ptr = 0;
        memset(shadow_stack, 0, SHADOW_STACK_SIZE * sizeof(uintptr_t));
        shadow_stack_make_ro();
    }
}
