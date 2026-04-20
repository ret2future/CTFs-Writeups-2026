from pathlib import Path


data = Path("artifacts/main.js").read_text()
needles = [
    "function key_pressed",
    "function button_down",
    "function button_up",
    "function machine_tick",
    "function start_machine_interval",
    "function start()",
    'Module.ccall("vm_start"',
    "embedded_files",
    "embedded_files[",
    "Object.keys(embedded_files)",
    "decodeBase64",
    "pako",
    "inflate",
    "decompress",
    "read_file",
    "net_state",
    "recv_packet",
    "send_packet",
    "_net_recv_packet",
    "function _net_recv_packet",
    "function handle_packet",
    "function net_write_packet",
    "function onRuntimeInitialized",
    "Module[\"onRuntimeInitialized\"]",
    "_vm_start(",
    "_fs_import_file(",
    "_net_set_carrier(",
    "_net_write_packet(",
    "_vm_start",
    "_virt_machine_run",
    "_display_key_event",
    "_console_queue_char",
    "console_queue_char",
    "queue_char",
    "print_msg(",
    "Module.print =",
    "set_interval(",
    "start();",
    "start()",
    "line_buffer",
    "console_0",
    "_console_queue_char(",
    "console_queue_char(",
    "packet",
    "carrier",
    "terminal",
    "serial",
    "console",
    "tty",
    "_net_write_packet",
    "nonce",
    "challenge",
    "flag",
    "sha",
    "md5",
    "digest",
    "encrypt",
    "decrypt",
]

for needle in needles:
    offset = data.find(needle)
    print(f"{needle}: {offset}")
    if offset == -1:
        continue
    start = max(0, offset - 160)
    end = min(len(data), offset + 640)
    print(data[start:end])
    print("====")