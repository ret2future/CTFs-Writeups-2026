from binascii import unhexlify
from pathlib import Path
import shutil


KEY = b"z09huvhu33i3bbuvuxzciohzc"


def decrypt_name(name: str) -> str:
    data = unhexlify(name)
    plain = bytes(byte ^ KEY[index % len(KEY)] for index, byte in enumerate(data))
    return plain.decode("utf-8")


def decrypt_data(data: bytes) -> bytes:
    return bytes(byte ^ KEY[index % len(KEY)] for index, byte in enumerate(data))


def main() -> None:
    source = Path("artifacts/mnt/root/home")
    destination = Path("artifacts/decrypted_home_full")

    if destination.exists():
        shutil.rmtree(destination)
    destination.mkdir(parents=True)

    for path in sorted(source.rglob("*")):
        relative = path.relative_to(source)
        decrypted_parts = [decrypt_name(part) for part in relative.parts]
        output = destination.joinpath(*decrypted_parts)

        if path.is_dir():
            output.mkdir(parents=True, exist_ok=True)
            continue

        output.parent.mkdir(parents=True, exist_ok=True)
        output.write_bytes(decrypt_data(path.read_bytes()))

    total = sum(1 for _ in destination.rglob("*"))
    print(f"wrote {total} entries to {destination}")


if __name__ == "__main__":
    main()