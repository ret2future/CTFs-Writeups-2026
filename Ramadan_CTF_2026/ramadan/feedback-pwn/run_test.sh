#!/bin/sh
cp flag.txt /tmp/flag.txt && chmod 666 /tmp/flag.txt
qemu-system-x86_64 \
    -kernel bzImage \
    -cpu qemu64,+smep,+smap \
    -m 1G \
    -smp 4 \
    -initrd initramfs_test.cpio.gz \
    -drive file=/tmp/flag.txt,format=raw,index=1 \
    -append "console=ttyS0 quiet loglevel=3 kaslr kpti=1" \
    -monitor /dev/null \
    -nographic \
    -no-reboot
rm -rf /tmp/flag.txt
