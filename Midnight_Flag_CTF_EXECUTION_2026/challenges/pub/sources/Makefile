CC     = gcc
CFLAGS = -O0 -no-pie -fno-stack-protector -fno-pie -Wl,-z,relro,-z,now

game: game.c shadow_stack.c shadow_stack.h
	$(CC) $(CFLAGS) -o game game.c shadow_stack.c

clean:
	rm -f game chall
