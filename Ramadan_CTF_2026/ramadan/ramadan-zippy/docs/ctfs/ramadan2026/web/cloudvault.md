---
title: "CloudVault"
category: "Web"
difficulty: "Easy"
points: 75
emoji: "🌐"
---

## Challenge
A secure drive built specifically for storing backup files.

## Solution
The app exposed ZIP upload and file download under:
`/zip/<filename>/download/<path:filepath>`.

The download handler joined user-controlled path segments without enforcing canonical path containment. That allowed path traversal with encoded `../` to read files outside the extracted ZIP directory.

Steps:
1. Register and login through GraphQL API.
2. Upload any ZIP file to unlock the download route.
3. Request traversal path such as:
   `/zip/sample.zip/download/..%2f..%2f..%2f..%2fflag.txt`
4. Read the returned flag.

## Flag
`VBD{z1p_sl1p_1s_fun_adb2c482c74dadf66562129c16748893}`
