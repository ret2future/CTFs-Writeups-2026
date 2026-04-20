// yolov7-studio

(() => {
  // /api/detect UI
  const drop    = document.getElementById('drop');
  const file    = document.getElementById('file');
  const resBox  = document.getElementById('detect-results');
  const overlay = document.getElementById('overlay');
  const list    = document.getElementById('det-list');
  const count   = document.getElementById('det-count');

  drop.addEventListener('click', () => file.click());
  drop.addEventListener('dragover', e => {
    e.preventDefault();
    drop.classList.add('hover');
  });
  drop.addEventListener('dragleave', () => drop.classList.remove('hover'));
  drop.addEventListener('drop', e => {
    e.preventDefault();
    drop.classList.remove('hover');
    if (e.dataTransfer.files.length) runDetect(e.dataTransfer.files[0]);
  });
  file.addEventListener('change', () => {
    if (file.files.length) runDetect(file.files[0]);
  });

  async function runDetect(f) {
    list.innerHTML = '';
    count.textContent = '0';
    overlay.removeAttribute('src');
    resBox.hidden = false;
    overlay.alt = 'running yolov7 …';

    const fd = new FormData();
    fd.append('image', f);

    let resp, data;
    try {
      resp = await fetch('/api/detect', { method: 'POST', body: fd });
      data = await resp.json();
    } catch (e) {
      overlay.alt = 'network error: ' + e.message;
      return;
    }

    if (!data.ok) {
      overlay.alt = 'error: ' + (data.error || 'unknown');
      return;
    }

    overlay.src = 'data:image/jpeg;base64,' + data.overlay_jpg_b64;
    overlay.alt = '';
    count.textContent = data.count;

    for (const d of data.detections) {
      const li = document.createElement('li');
      li.innerHTML = `<span class="cls">${escapeHtml(d.class)}</span>
                      <span class="cf">${(d.confidence * 100).toFixed(1)}%</span>`;
      list.appendChild(li);
    }
    if (data.detections.length === 0) {
      const li = document.createElement('li');
      li.innerHTML = '<span class="muted">no objects above confidence threshold</span>';
      list.appendChild(li);
    }
  }

  // /api/model/build UI
  const yamlInput = document.getElementById('yaml-input');
  const ptFile    = document.getElementById('pt-file');
  const ptHint    = document.getElementById('pt-hint');
  const trainBtn  = document.getElementById('train-btn');
  const trainOut  = document.getElementById('train-out');

  ptFile.addEventListener('change', () => {
    if (ptFile.files.length) {
      const f = ptFile.files[0];
      ptHint.textContent = `selected: ${f.name} (${fmtBytes(f.size)})`;
    } else {
      ptHint.textContent = 'nothing selected';
    }
  });

  trainBtn.addEventListener('click', async () => {
    trainOut.className = 'train-out';
    trainOut.textContent = 'building graph …';
    trainBtn.disabled = true;

    const fd = new FormData();
    const yamlText = yamlInput.value;
    if (yamlText.trim().length) {
      fd.append('config',
        new Blob([yamlText], { type: 'application/x-yaml' }),
        'architecture.yaml');
    }
    if (ptFile.files.length) {
      fd.append('weights', ptFile.files[0]);
    }

    let data;
    try {
      const resp = await fetch('/api/model/build', {
        method: 'POST', body: fd,
      });
      data = await resp.json();
    } catch (e) {
      trainOut.className = 'train-out err';
      trainOut.textContent = 'network error: ' + e.message;
      trainBtn.disabled = false;
      return;
    }

    trainOut.className = 'train-out ' + (data.ok ? 'ok' : 'err');
    trainOut.textContent = JSON.stringify(data, null, 2);
    trainBtn.disabled = false;
  });

  function fmtBytes(n) {
    if (n < 1024) return n + ' B';
    if (n < 1024 * 1024) return (n / 1024).toFixed(1) + ' KiB';
    if (n < 1024 * 1024 * 1024) return (n / 1024 / 1024).toFixed(1) + ' MiB';
    return (n / 1024 / 1024 / 1024).toFixed(2) + ' GiB';
  }

  function escapeHtml(s) {
    return String(s)
      .replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;').replace(/'/g, '&#39;');
  }
})();
