import pexpect
import sys

cmds = [
    "id",
    "uname -a",
    "ls -la /dev/feedback",
    "zcat /proc/config.gz | head -n 5",
    "cat /proc/cmdline",
    "exit",
]

child = pexpect.spawn("./run.sh", cwd=".", encoding="utf-8", timeout=120)
child.logfile_read = sys.stdout

child.expect("Welcome to Student Feedback")
child.sendline("")
child.expect(r"~ \$")

for command in cmds:
    child.sendline(command)
    if command != "exit":
        child.expect(r"~ \$")

child.expect(pexpect.EOF)
