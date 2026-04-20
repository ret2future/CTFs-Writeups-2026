from scapy.all import rdpcap, TCP, IP
from collections import defaultdict
from pathlib import Path
import re

pkts = rdpcap('capture.pcap')

flows = defaultdict(list)
for p in pkts:
    if IP in p and TCP in p:
        payload = bytes(p[TCP].payload)
        if not payload:
            continue
        ip, t = p[IP], p[TCP]
        key = (ip.src, t.sport, ip.dst, t.dport)
        flows[key].append((int(t.seq), payload))

requests = []
for key, chunks in flows.items():
    data = b''.join(c for _, c in sorted(chunks, key=lambda x: x[0]))
    if b'GET ' in data[:2000] and b'HTTP/1.1' in data[:4000]:
        m = re.search(br'GET\s+([^\s]+)\s+HTTP/1\.[01]\r?\nHost:\s*([^\r\n]+)', data)
        if m:
            path = m.group(1).decode('latin1', 'ignore')
            host = m.group(2).decode('latin1', 'ignore')
            requests.append((key, host, path))

outdir = Path('extracted_http')
outdir.mkdir(exist_ok=True)

count = 0
for c2s, host, path in requests:
    s2c = (c2s[2], c2s[3], c2s[0], c2s[1])
    if s2c not in flows:
        continue

    resp = b''.join(c for _, c in sorted(flows[s2c], key=lambda x: x[0]))
    i = resp.find(b'HTTP/1.1 ')
    if i < 0:
        continue
    resp = resp[i:]

    hdr_end = resp.find(b'\r\n\r\n')
    sep_len = 4
    if hdr_end < 0:
        hdr_end = resp.find(b'\n\n')
        sep_len = 2
    if hdr_end < 0:
        continue

    headers = resp[:hdr_end].decode('latin1', 'ignore')
    body = resp[hdr_end + sep_len:]

    mcl = re.search(r'(?im)^Content-Length:\s*(\d+)', headers)
    if mcl:
        clen = int(mcl.group(1))
        body = body[:clen]

    ctype = ''
    mct = re.search(r'(?im)^Content-Type:\s*([^\r\n]+)', headers)
    if mct:
        ctype = mct.group(1).strip()

    safe_host = re.sub(r'[^A-Za-z0-9_.-]+', '_', host)
    base = path.split('?')[0].strip('/') or 'root'
    safe_path = re.sub(r'[^A-Za-z0-9_.-]+', '_', base)

    ext = ''
    if safe_path and '.' in safe_path.split('/')[-1]:
        ext = ''
    elif 'image/png' in ctype:
        ext = '.png'
    elif 'javascript' in ctype:
        ext = '.js'
    elif 'json' in ctype:
        ext = '.json'
    elif 'text/plain' in ctype:
        ext = '.txt'

    fname = f"{safe_host}__{safe_path}{ext}"
    fpath = outdir / fname
    with open(fpath, 'wb') as f:
        f.write(body)

    count += 1
    print(f"{count:02d} {host}{path} -> {fpath} ({len(body)} bytes, {ctype})")

print('total_extracted', count)
