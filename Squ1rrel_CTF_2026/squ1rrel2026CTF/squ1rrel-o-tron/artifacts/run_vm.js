const fs = require("fs");
const path = require("path");
const vm = require("vm");
const zlib = require("zlib");

const fields = new Map();

function makeField(name) {
  return {
    name,
    value: "",
    readonly: false,
    hidden: false,
    display: 0,
    fillColor: null,
    textColor: null,
    buttonSetCaption() {},
    checkThisBox() {},
    setAction() {},
  };
}

const sandbox = {
  console,
  Uint8Array,
  Uint16Array,
  Uint32Array,
  Int8Array,
  Int16Array,
  Int32Array,
  Float32Array,
  Float64Array,
  ArrayBuffer,
  DataView,
  Math,
  Date,
  JSON,
  Buffer,
  TextEncoder,
  TextDecoder,
  __dirname,
  NODE_PATH_JOIN: path.join,
  NODE_WRITE_FILE: (filePath, data) => fs.writeFileSync(filePath, Buffer.from(data)),
  atob: (input) => Buffer.from(input, "base64").toString("binary"),
  btoa: (input) => Buffer.from(input, "binary").toString("base64"),
  performance: { now: () => Date.now() },
  globalThis: null,
  window: null,
  self: null,
  exports: undefined,
  module: undefined,
  define: undefined,
  linesSnapshot: () => {
    const rows = [];
    for (let index = 0; index < 25; index += 1) {
      rows.push((fields.get(`console_${index}`) || makeField(`console_${index}`)).value);
    }
    return rows;
  },
};

const context = vm.createContext(sandbox);
sandbox.globalThis = sandbox;
sandbox.window = sandbox;
sandbox.self = sandbox;

sandbox.getField = (name) => {
  if (!fields.has(name)) {
    fields.set(name, makeField(name));
  }
  return fields.get(name);
};

sandbox.app = {
  alert(message) {
    console.error("ALERT:", message);
  },
  setInterval(code, interval) {
    return setInterval(() => {
      vm.runInContext(code, context);
    }, interval);
  },
  setTimeOut(code, timeout) {
    return setTimeout(() => {
      vm.runInContext(code, context);
    }, timeout);
  },
};

let mainJs = fs.readFileSync(path.join(__dirname, "main.js"), "utf8");
mainJs = mainJs.replace("var Module = {};", "var Module = {INITIAL_MEMORY: 536870912};");
mainJs = mainJs.replace('if(str==="\\n")', 'if(char==="\\n")');
mainJs = mainJs.replace(
  'function _fs_export_file(filename,buf,buf_len){var _filename=UTF8ToString(filename);var data=HEAPU8.subarray(buf,buf+buf_len);var file=new Blob([data],{type:"application/octet-stream"});var url=URL.createObjectURL(file);var a=document.createElement("a");a.href=url;a.setAttribute("download",_filename);a.innerHTML="downloading";document.body.appendChild(a);setTimeout(function(){a.click();document.body.removeChild(a)},50)}',
  'function _fs_export_file(filename,buf,buf_len){var _filename=UTF8ToString(filename);var data=HEAPU8.slice(buf,buf+buf_len);var safe=_filename.replace(/[^A-Za-z0-9._-]/g,"_");var outPath=NODE_PATH_JOIN(__dirname,"exports_"+safe);NODE_WRITE_FILE(outPath,data);console.log("[fs_export]",_filename,"=>",outPath,"len=",buf_len)}'
);

const probeInittab = `::sysinit:/bin/mount -t proc proc /proc
::sysinit:/bin/mount -t sysfs sysfs /sys
::sysinit:/bin/mount -t devtmpfs devtmpfs /dev
::sysinit:/bin/mount -t tmpfs tmpfs /tmp
::sysinit:/bin/sh -c 'cd /root && printf "0000000000000000000000000000000000000000000000000000000000000000\\n" | /root/chall > /root/out 2>&1 && /bin/cat /root/out && echo __INIT_DONE__'
::respawn:/bin/sh
::ctrlaltdel:/bin/reboot
::shutdown:/bin/umount -a -r
`;

function patchEmbeddedElfFloatAbi(name) {
  if (!sandbox.embedded_files || typeof sandbox.embedded_files[name] === "undefined") {
    return false;
  }

  let payload;
  if (typeof sandbox.embedded_files[name] === "string") {
    const compressed = Buffer.from(sandbox.embedded_files[name], "base64");
    payload = zlib.inflateSync(compressed);
  } else {
    payload = Buffer.from(sandbox.embedded_files[name]);
  }

  if (payload.length < 0x28 || payload.toString("binary", 0, 4) !== "\x7fELF") {
    return false;
  }

  const flags = payload.readUInt32LE(0x24);
  const patchedFlags = flags & ~0x6;
  payload.writeUInt32LE(patchedFlags, 0x24);
  sandbox.embedded_files[name] = new Uint8Array(payload);
  console.log(`patched ELF flags for ${name}: 0x${flags.toString(16)} -> 0x${patchedFlags.toString(16)}`);
  return true;
}

vm.runInContext(mainJs, context, { filename: "main.js" });

if (sandbox.embedded_files) {
  patchEmbeddedElfFloatAbi("root/files/0000000000000002");
  patchEmbeddedElfFloatAbi("root/files/0000000000000004");
  sandbox.embedded_files["root/files/0000000000000003"] = new Uint8Array(Buffer.from(sandbox.embedded_files["root/files/0000000000000002"]));
  sandbox.embedded_files["root/files/0000000000000009"] = new Uint8Array(Buffer.from(probeInittab, "utf8"));
  console.log("patched /sbin/init to BusyBox and replaced /etc/inittab");
}

if (typeof sandbox.start !== "function") {
  throw new Error("start() was not defined by main.js");
}

console.log("main.js loaded");

if (typeof sandbox._virt_machine_run === "function") {
  const originalVirtMachineRun = sandbox._virt_machine_run;
  let runCount = 0;
  sandbox._virt_machine_run = (machinePtr) => {
    const result = originalVirtMachineRun(machinePtr);
    runCount += 1;
    if (runCount <= 20) {
      console.log(`[virt_machine_run] call=${runCount} result=${result} ptr=${machinePtr}`);
    }
    return result;
  };
}

sandbox.start();
console.log("start() called");

let ticks = 0;
let previousSnapshot = "";
const interval = setInterval(() => {
  ticks += 1;
  const snapshot = sandbox.linesSnapshot().join("\n");
  const speedField = sandbox.getField("speed_indicator");
  const lineBuffer = typeof sandbox.line_buffer === "string" ? sandbox.line_buffer : "";

  if (snapshot !== previousSnapshot || lineBuffer.length > 0 || ticks % 5 === 0) {
    console.log(`tick=${ticks}`);
    if (speedField && speedField.value) {
      console.log(`[speed] ${speedField.value}`);
    }
    console.log(snapshot);
    if (lineBuffer.length > 0) {
      console.log(`[line_buffer] ${lineBuffer}`);
    }
    console.log("----");
    previousSnapshot = snapshot;
  }

  if (ticks >= 60) {
    clearInterval(interval);
    process.exit(0);
  }
}, 1000);