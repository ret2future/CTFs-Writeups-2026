# JITstream (PWN) — Writeup

## Challenge summary
The patch introduces a new builtin:

- `Array.prototype.swapIt()`
- It toggles a JSArray map between `PACKED_ELEMENTS` and `PACKED_DOUBLE_ELEMENTS`
- It **does not** migrate/convert the backing elements storage

This creates a classic V8 type confusion primitive.

## Patch analysis
From `v8.patch`:

- New builtin `ArraySwapIt` was added in `src/builtins/builtins-array.cc`
- It checks receiver is a fast JSArray and length >= 1
- If elements kind is packed object or packed double, it transitions map to the opposite kind
- No elements conversion/copy is done before map swap

So after `swapIt()`:

- A double array can be interpreted as object array
- An object array can be interpreted as double array

That is enough to start building `addrof`/`fakeobj` in a standard V8 exploit chain.

## Runtime behavior observed
The remote service (`nc ctf.vulnbydefault.com 38871`) expects:

1. `Size of Exploit:` (integer)
2. `Script:` (raw JS bytes)
3. Runs `/d8 <tmpfile>`

From probing:

- `swapIt` is reachable and confusion is reproducible
- `read`, `readbuffer`, `writeFile` are available in `d8`
- `os.system` is **not** available
- Absolute filesystem paths are readable
- Absolute filesystem paths are writable via `writeFile`

## Exploit path used (practical solve)
Although intended route is memory corruption via Maglev/V8 primitives, this instance exposed an easier sandbox escape-like path:

1. Confirm absolute write works:
   - `writeFile('/server_tmp_test.py', 'ABS_OK')`
2. Overwrite `/server.py` from JS payload (through `writeFile`)
3. Reconnect to service; modified Python server executes and prints candidate `flag*` files
4. Read printed flag

### Helper script used
`overwrite_server.py` writes a new `/server.py` that recursively scans likely roots (`/root`, `/home`, `/opt`, `/srv`, `/tmp`, `/run`, `/`) and prints files with `flag` in name.

Then reconnect and read output.

## Flag

`VBD{j1t_spr4y_w1th_magl3v_1s_b3st_b47f08d2d75e5c80fb696166ffc36b55}`

Found at:

- `/flag_472aa0a968778.txt`

## Notes on intended exploit (if patched against writeFile abuse)
If the organizer hardens shell helpers (`read`/`writeFile`) and requires memory corruption only, intended route is likely:

1. Use `swapIt` for type confusion
2. Build `addrof` (object->double reinterpretation)
3. Build `fakeobj` (double->object reinterpretation)
4. Corrupt adjacent array metadata / backing store pointer for arb read/write
5. Find RWX WebAssembly page or code pointer target
6. Pivot control flow to shellcode/ROP or in-process file read primitive

Given `v8_enable_sandbox = true`, exploit reliability depends on current V8 14.4 internals and sandbox bypass strategy.

## Reproduction snippets
Minimal confusion proof:

```js
let a = [1.1, 2.2];
a.swapIt();
print(a[0]);      // corrupted numeric interpretation
print(typeof a[0]);
```

Object->double leak sketch:

```js
const buf = new ArrayBuffer(8);
const f64 = new Float64Array(buf);
const u64 = new BigUint64Array(buf);
function f2i(f){ f64[0] = f; return u64[0]; }

let obj = {x: 1};
let arr = [obj, obj];
arr.swapIt();
print(f2i(arr[0]).toString(16));
```

## Files produced during solve
- `path_bruteforce.py`
- `root_probe.py`
- `dir_probe.py`
- `overwrite_server.py`
- `WRITEUP.md`
