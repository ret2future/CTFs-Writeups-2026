import pexpect
import sys

child = pexpect.spawn("./run_test.sh", cwd=".", encoding="utf-8", timeout=180)
child.logfile_read = sys.stdout

child.expect("Welcome to Student Feedback")
child.sendline("")
child.expect(r"~ \$")

for cmd in [
    "id",
    "/root/feedback_diag",
    "echo RC:$?",
    "exit",
]:
    child.sendline(cmd)
    if cmd != "exit":
        child.expect(r"~ \$")

child.expect(pexpect.EOF)
