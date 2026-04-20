from binascii import unhexlify


PAIRS = [
    ("0955570C101B091C5F1E1F520E0B1117011D54100802181606", "sendemail-validate.sample"),
    ("0A425C1814040D58505C045E0B16581B061F54100802181606", "prepare-commit-msg.sample"),
    ("0A425C4505031B1D1D40085E120E10", "pre-push.sample"),
    ("0A425C4507130A1440564740030F051A10", "pre-rebase.sample"),
    ("0A425C4507130B105A450C1D11031806191D", "pre-receive.sample"),
    ("0A425C45140618194A430847010A5B0514150A0F0C", "pre-applypatch.sample"),
    ("0A425C45161905185A474740030F051A10", "pre-commit.sample"),
    ("0A425C4518131A12561E0A5C0F0F1C025B0B1B0E19030D", "pre-merge-commit.sample"),
    ("0A454A0058020758505B0C50090D00025B0B1B0E19030D", "push-to-checkout.sample"),
    ("0A5F4A1C5803181152470C1D11031806191D", "post-update.sample"),
    ("0F405D0901134606525E195F07", "update.sample"),
    ("195F54051C02451840544740030F051A10", "commit-msg.sample"),
    ("1B4049040C060901505B445E11055B0514150A0F0C", "applypatch-msg.sample"),
    ("1E554A0B071F18015A5C07", "description"),
    ("195F570E1C11", "config"),
    ("3275782C", "HEAD"),
    ("08555F1B", "refs"),
    ("125F560306", "hooks"),
    ("135E5D0D0D", "index"),
    ("1552530D16021B", "objects"),
    ("165F5E1B", "logs"),
    ("3562702F2A3E2D3477", "ORIG_HEAD"),
    ("397F74253C223730777A3D7E3125", "COMMIT_EDITMSG"),
    ("08555D451D131A075A5D0E", "red-herring"),
    ("135E5F07", "info"),
    ("0944581B1D", "stash"),
    ("0E515E1B", "tags"),
    ("1255580C06", "heads"),
    ("17514A1C1004", "master"),
    ("1F485A0400120D", "exclude"),
]


def main() -> None:
    key = {}
    for enc_hex, plain in PAIRS:
        enc = unhexlify(enc_hex)
        for index, (enc_byte, plain_byte) in enumerate(zip(enc, plain.encode())):
            key_byte = enc_byte ^ plain_byte
            if index in key and key[index] != key_byte:
                raise SystemExit(
                    f"conflict at position {index}: {key[index]:02x} vs {key_byte:02x} for {plain}"
                )
            key[index] = key_byte

    last = max(key)
    ascii_key = "".join(chr(key[i]) if 32 <= key[i] < 127 else "?" for i in range(last + 1))
    hex_key = "".join(f"{key[i]:02x}" for i in range(last + 1))

    print(f"LEN {last + 1}")
    print(f"ASCII {ascii_key}")
    print(f"HEX {hex_key}")


if __name__ == "__main__":
    main()