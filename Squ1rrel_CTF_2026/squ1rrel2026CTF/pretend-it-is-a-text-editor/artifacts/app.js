import { layoutWithLines, prepareWithSegments } from "/lib/layout/layout.js";

let user = null;
let notes = [];
let selectedId = null;
let preview = null;

async function api(path, opts = {}) {
  const res = await fetch(path, {
    headers: { "Content-Type": "application/json" },
    ...opts,
    body: opts.body ? JSON.stringify(opts.body) : undefined,
  });
  const data = await res.json();
  if (!res.ok) throw new Error(data.error || "Request failed");
  return data;
}

class Preview {
  constructor(cvs) {
    this.cvs = cvs;
    this.ctx = cvs.getContext("2d");
    this.text = "";
    this.font = "16px Inter";
    this.lh = 24;
    this.prepared = null;
    this.lines = [];
    this.raf = null;
    this.orbs = [
      ["#6366f1", 0.35],
      ["#ec4899", 0.3],
      ["#14b8a6", 0.3],
      ["#f59e0b", 0.25],
      ["#8b5cf6", 0.35],
      ["#06b6d4", 0.28],
      ["#f43f5e", 0.22],
      ["#a78bfa", 0.3],
    ].map(([color, alpha]) => ({
      x: Math.random() * 500 + 30,
      y: Math.random() * 400 + 30,
      vx: (Math.random() - 0.5) * 80,
      vy: (Math.random() - 0.5) * 80,
      r: 6 + Math.random() * 16,
      color,
      alpha,
    }));
  }

  resize() {
    const rect = this.cvs.parentElement.getBoundingClientRect();
    const dpr = devicePixelRatio;
    this.cvs.width = rect.width * dpr;
    this.cvs.height = rect.height * dpr;
    this.ctx.scale(dpr, dpr);
    this.w = rect.width;
    this.h = rect.height;
    this.relayout();
  }

  setText(text) {
    this.text = text;
    this.prepared = text ? prepareWithSegments(text, this.font) : null;
    this.relayout();
  }

  relayout() {
    if (!this.prepared || !this.w) {
      this.lines = [];
      return;
    }
    const maxW = this.w - 48;
    if (maxW <= 0) {
      this.lines = [];
      return;
    }
    this.lines = layoutWithLines(this.prepared, maxW, this.lh).lines;
  }

  start() {
    if (this.raf) return;
    let last = performance.now();
    const tick = (now) => {
      const dt = Math.min((now - last) / 1000, 0.05);
      last = now;
      this.update(dt);
      this.draw();
      this.raf = requestAnimationFrame(tick);
    };
    this.raf = requestAnimationFrame(tick);
  }

  stop() {
    if (this.raf) cancelAnimationFrame(this.raf);
    this.raf = null;
  }

  update(dt) {
    for (const o of this.orbs) {
      o.x += o.vx * dt;
      o.y += o.vy * dt;
      if (o.x - o.r < 0) {
        o.x = o.r;
        o.vx = Math.abs(o.vx);
      }
      if (o.x + o.r > this.w) {
        o.x = this.w - o.r;
        o.vx = -Math.abs(o.vx);
      }
      if (o.y - o.r < 0) {
        o.y = o.r;
        o.vy = Math.abs(o.vy);
      }
      if (o.y + o.r > this.h) {
        o.y = this.h - o.r;
        o.vy = -Math.abs(o.vy);
      }
    }
  }

  draw() {
    const { ctx, w, h } = this;
    ctx.clearRect(0, 0, w, h);
    ctx.fillStyle = "#0b0b12";
    ctx.fillRect(0, 0, w, h);

    for (const o of this.orbs) {
      ctx.save();
      ctx.globalAlpha = o.alpha;
      ctx.shadowBlur = 40;
      ctx.shadowColor = o.color;
      ctx.fillStyle = o.color;
      ctx.beginPath();
      ctx.arc(o.x, o.y, o.r, 0, Math.PI * 2);
      ctx.fill();
      ctx.restore();
    }

    if (this.lines.length === 0) {
      ctx.fillStyle = "#444";
      ctx.font = "14px Inter";
      ctx.textBaseline = "middle";
      ctx.textAlign = "center";
      ctx.fillText("Select or create a note to preview", w / 2, h / 2);
      ctx.textAlign = "start";
      return;
    }

    ctx.font = this.font;
    ctx.textBaseline = "top";
    for (let i = 0; i < this.lines.length; i++) {
      const y = 28 + i * this.lh;
      let glow = null;
      for (const o of this.orbs) {
        if (Math.abs(o.y - (y + this.lh / 2)) < this.lh * 0.8) {
          glow = o.color;
          break;
        }
      }
      if (glow) {
        ctx.save();
        ctx.shadowBlur = 10;
        ctx.shadowColor = glow;
        ctx.fillStyle = "#fff";
        ctx.fillText(this.lines[i].text, 24, y);
        ctx.restore();
      } else {
        ctx.fillStyle = "#c8c8d8";
        ctx.fillText(this.lines[i].text, 24, y);
      }
    }
  }
}

function el(tag, props, ...children) {
  const node = document.createElement(tag);
  if (props) {
    for (const [k, v] of Object.entries(props)) {
      if (v == null) continue;
      if (k === "className") node.className = v;
      else if (k === "style") node.style.cssText = v;
      else if (k.startsWith("on")) node.addEventListener(k.slice(2), v);
      else node.setAttribute(k, v);
    }
  }
  for (const c of children) {
    if (c == null) continue;
    node.appendChild(typeof c === "string" ? document.createTextNode(c) : c);
  }
  return node;
}

function render() {
  const app = document.getElementById("app");
  app.innerHTML = "";

  const header = el(
    "header",
    {},
    el("div", { className: "logo" }, "secure", el("span", {}, "notes")),
    renderAuth(),
  );
  app.appendChild(header);
  app.appendChild(
    el("div", { className: "main" }, renderSidebar(), renderContent()),
  );

  const cvs = document.getElementById("preview-canvas");
  if (cvs) {
    if (!preview) {
      preview = new Preview(cvs);
    } else {
      preview.cvs = cvs;
      preview.ctx = cvs.getContext("2d");
    }
    preview.resize();
    preview.start();
    const note = notes.find((n) => n.id === selectedId);
    preview.setText(note?.content ?? "");
  }
}

function renderAuth() {
  if (user) {
    return el(
      "div",
      { className: "auth-area" },
      el(
        "span",
        { className: "user-badge" },
        "Signed in as ",
        el("strong", {}, user.username),
      ),
      el(
        "button",
        { className: "btn btn-ghost btn-sm", onclick: doLogout },
        "Logout",
      ),
    );
  }
  return el(
    "div",
    { className: "auth-area" },
    el("input", { type: "text", placeholder: "username", id: "auth-u" }),
    el("input", { type: "password", placeholder: "password", id: "auth-p" }),
    el(
      "button",
      { className: "btn btn-primary btn-sm", onclick: () => doAuth("login") },
      "Login",
    ),
    el(
      "button",
      { className: "btn btn-ghost btn-sm", onclick: () => doAuth("register") },
      "Register",
    ),
  );
}

function renderSidebar() {
  const sidebar = el("div", { className: "sidebar" });
  sidebar.appendChild(
    el(
      "div",
      { className: "sidebar-header" },
      el("h2", {}, "Notes"),
      user
        ? el(
            "button",
            { className: "btn btn-primary btn-sm", onclick: doNewNote },
            "+",
          )
        : null,
    ),
  );

  if (!user) {
    sidebar.appendChild(
      el("div", { className: "empty-state" }, "Log in to see your notes"),
    );
    return sidebar;
  }
  if (notes.length === 0) {
    sidebar.appendChild(
      el("div", { className: "empty-state" }, "No notes yet"),
    );
    return sidebar;
  }

  const ul = el("ul", { className: "note-list" });
  for (const n of notes) {
    ul.appendChild(
      el(
        "li",
        {
          className: n.id === selectedId ? "active" : "",
          onclick: () => {
            selectedId = n.id;
            render();
          },
        },
        n.title || "Untitled",
      ),
    );
  }
  sidebar.appendChild(ul);
  return sidebar;
}

function renderContent() {
  const wrap = el("div", { className: "content" });
  wrap.appendChild(
    el(
      "div",
      { className: "canvas-wrap" },
      el("canvas", { id: "preview-canvas" }),
    ),
  );

  const note = notes.find((n) => n.id === selectedId);
  if (note) {
    const panel = el("div", { className: "panel" });
    panel.appendChild(el("h3", {}, note.title));
    panel.appendChild(
      el(
        "p",
        {
          style:
            "color:var(--text-dim);font-size:13px;margin-bottom:8px;white-space:pre-wrap",
        },
        note.content,
      ),
    );

    const embed = el("div", { className: "embed-info" });
    embed.appendChild(el("div", { className: "label" }, "Embed preview API"));
    embed.appendChild(
      el("code", {}, `GET /api/notes/${note.id}/embed?width=400`),
    );
    panel.appendChild(embed);
    wrap.appendChild(panel);
  } else if (user) {
    const panel = el("div", { className: "panel" });
    panel.appendChild(el("h3", {}, "New note"));
    panel.appendChild(
      el(
        "div",
        { className: "form-row" },
        el("input", { type: "text", placeholder: "Title", id: "note-title" }),
      ),
    );
    panel.appendChild(
      el(
        "div",
        { className: "form-row" },
        el("textarea", { placeholder: "Content", id: "note-content", rows: 3 }),
      ),
    );
    panel.appendChild(
      el(
        "div",
        { className: "form-row" },
        el(
          "button",
          { className: "btn btn-primary", onclick: doCreateNote },
          "Save note",
        ),
      ),
    );
    wrap.appendChild(panel);
  } else {
    wrap.appendChild(
      el(
        "div",
        { className: "welcome" },
        el("h2", {}, "SecureNotes"),
        el(
          "p",
          {},
          "A secure notes service. Sign in to create and preview your notes.",
        ),
      ),
    );
  }
  return wrap;
}

async function doAuth(action) {
  const u = document.getElementById("auth-u")?.value;
  const p = document.getElementById("auth-p")?.value;
  if (!u || !p) return;
  try {
    user = await api(`/api/${action}`, {
      method: "POST",
      body: { username: u, password: p },
    });
    await loadNotes();
    render();
  } catch (e) {
    alert(e.message);
  }
}

async function doLogout() {
  await api("/api/logout", { method: "POST" });
  user = null;
  notes = [];
  selectedId = null;
  render();
}

function doNewNote() {
  selectedId = null;
  render();
  document.getElementById("note-content")?.focus();
}

async function doCreateNote() {
  const title = document.getElementById("note-title")?.value || "Untitled";
  const content = document.getElementById("note-content")?.value;
  if (!content) return;
  try {
    const note = await api("/api/notes", {
      method: "POST",
      body: { title, content },
    });
    notes.push(note);
    selectedId = note.id;
    render();
  } catch (e) {
    alert(e.message);
  }
}

async function loadNotes() {
  try {
    notes = await api("/api/notes");
  } catch {
    notes = [];
  }
}

async function init() {
  try {
    const me = await api("/api/me");
    if (me.user) {
      user = me.user;
      await loadNotes();
    }
  } catch {}
  render();
  window.addEventListener("resize", () => preview?.resize());
}

init();
