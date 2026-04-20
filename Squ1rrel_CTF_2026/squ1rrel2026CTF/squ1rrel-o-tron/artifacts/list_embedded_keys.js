const fs = require("fs");
const path = require("path");
const vm = require("vm");

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
  atob: (input) => Buffer.from(input, "base64").toString("binary"),
  btoa: (input) => Buffer.from(input, "binary").toString("base64"),
  performance: { now: () => Date.now() },
  globalThis: null,
  window: null,
  self: null,
  exports: undefined,
  module: undefined,
  define: undefined,
};

sandbox.globalThis = sandbox;
sandbox.window = sandbox;
sandbox.self = sandbox;
sandbox.getField = () => ({
  value: "",
  buttonSetCaption() {},
  checkThisBox() {},
  setAction() {},
});
sandbox.app = {
  alert: console.error,
  setInterval() {
    return 0;
  },
  setTimeOut() {
    return 0;
  },
};

const context = vm.createContext(sandbox);
let mainJs = fs.readFileSync(path.join(__dirname, "main.js"), "utf8");
mainJs = mainJs.replace("var Module = {};", "var Module = {INITIAL_MEMORY: 536870912};");
vm.runInContext(mainJs, context, { filename: "main.js" });

const keys = Object.keys(sandbox.embedded_files || {})
  .filter((key) => key.includes("root/files") || key.includes("root/head") || key.endsWith(".cfg"))
  .sort();

console.log(`count ${keys.length}`);
for (const key of keys) {
  const value = sandbox.embedded_files[key];
  console.log(`${key}\t${typeof value}\t${value.length}`);
}