const TRUNCATION_PSI = 0.7;
function apiBase() {
  const raw = document.body?.dataset?.apiBase ?? "";
  return String(raw).trim().replace(/\/$/, "");
}

function birthdayToSeed(isoDate) {
  const parts = isoDate.split("-").map(Number);
  if (parts.length !== 3 || parts.some((n) => Number.isNaN(n))) {
    return 0;
  }
  const [y, m, d] = parts;
  return y * 10000 + m * 100 + d;
}

function hasDate() {
  const v = document.getElementById("birthday").value;
  return Boolean(v && v.trim());
}

function syncEnterButton() {
  const btn = document.getElementById("enter-btn");
  const ok = hasDate();
  btn.disabled = !ok;
  btn.setAttribute("aria-disabled", ok ? "false" : "true");
}

function setLoading(on) {
  const overlay = document.getElementById("loading");
  const pane = document.getElementById("image-pane");
  const btn = document.getElementById("enter-btn");

  if (on) {
    overlay.hidden = false;
    overlay.setAttribute("aria-hidden", "false");
    pane.setAttribute("aria-busy", "true");
    btn.disabled = true;
    btn.setAttribute("aria-disabled", "true");
  } else {
    overlay.hidden = true;
    overlay.setAttribute("aria-hidden", "true");
    pane.removeAttribute("aria-busy");
    syncEnterButton();
  }
}

async function loadSoulmate() {
  const input = document.getElementById("birthday");
  const img = document.getElementById("soulmate-img");
  const value = input.value;
  if (!value) {
    return;
  }

  const seed = birthdayToSeed(value);

  const resultSection = document.getElementById("result");
  const resultMessage = document.getElementById("result-message");
  const resultFlag = document.getElementById("result-flag");

  img.alt = "";
  resultSection.hidden = true;
  resultMessage.textContent = "";
  resultFlag.hidden = true;
  resultFlag.textContent = "";

  setLoading(true);

  try {
    const q = new URLSearchParams({
      seed: String(seed),
      truncation_psi: String(TRUNCATION_PSI),
      _: String(Date.now()),
    });
    const base = apiBase();
    const url = `${base}/generate-random?${q.toString()}`;
    const res = await fetch(url);
    const data = await res.json().catch(() => ({}));
    if (!res.ok) {
      const detail =
        typeof data.detail === "string"
          ? data.detail
          : Array.isArray(data.detail)
            ? data.detail.map((d) => d.msg || d).join(" ")
            : `HTTP ${res.status}`;
      throw new Error(detail || `HTTP ${res.status}`);
    }

    const old = img.dataset.blobUrl;
    if (old) {
      URL.revokeObjectURL(old);
      delete img.dataset.blobUrl;
    }
    const b64 = data.image_base64;
    if (b64) {
      img.src = `data:image/png;base64,${b64}`;
    } else {
      img.removeAttribute("src");
    }
    img.alt = `Soulmate face for birthday ${value}`;

    resultMessage.textContent = data.message || "";
    const tom = data.tom_score;
    const thr = data.threshold;
    if (typeof tom === "number" && typeof thr === "number") {
      const scoreLine = `Tom score: ${tom.toFixed(4)} (threshold: ${thr.toFixed(4)})`;
      resultMessage.textContent = resultMessage.textContent
        ? `${resultMessage.textContent}\n\n${scoreLine}`
        : scoreLine;
    }
    if (data.success && data.flag != null && data.flag !== "") {
      resultFlag.textContent = data.flag;
      resultFlag.hidden = false;
    } else {
      resultFlag.hidden = true;
      resultFlag.textContent = "";
    }
    resultSection.hidden = false;
  } catch (e) {
    img.removeAttribute("src");
    delete img.dataset.blobUrl;
    img.alt = "";
    resultSection.hidden = true;
    console.error(e);
    alert(e.message || "Could not load soulmate. Try again.");
  } finally {
    setLoading(false);
  }
}

const birthdayInput = document.getElementById("birthday");
const enterBtn = document.getElementById("enter-btn");

birthdayInput.addEventListener("input", syncEnterButton);
birthdayInput.addEventListener("change", syncEnterButton);
syncEnterButton();

enterBtn.addEventListener("click", () => {
  if (enterBtn.disabled || !hasDate()) {
    return;
  }
  loadSoulmate();
});