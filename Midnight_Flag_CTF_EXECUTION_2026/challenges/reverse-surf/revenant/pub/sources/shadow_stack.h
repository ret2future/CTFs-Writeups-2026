#ifndef SHADOW_STACK_H
#define SHADOW_STACK_H

#include <stdint.h>
#include <stddef.h>

#define SHADOW_STACK_SIZE 512

extern uintptr_t shadow_stack[SHADOW_STACK_SIZE];
extern size_t    shadow_stack_ptr;
extern char      username[64];

void shadow_stack_init(void);

void shadow_stack_push(uintptr_t ret_addr);

int shadow_stack_pop(uintptr_t ret_addr);

size_t shadow_stack_depth(void);

void shadow_stack_reset(void);

#endif
