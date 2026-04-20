// Load known users on index page
async function loadUsers() {
    const list = document.getElementById('users-list');
    if (!list) return;

    try {
        const res = await fetch('/api/users');
        const users = await res.json();
        list.innerHTML = '';
        users.forEach((name) => {
            const li = document.createElement('li');
            li.textContent = name;
            list.appendChild(li);
        });
    } catch {
        list.innerHTML = '<li>Could not load users</li>';
    }
}

// Handle spot submission
function initSpotForm() {
    const form = document.getElementById('spot-form');
    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const resultEl = document.getElementById('result');
        resultEl.classList.add('hidden');
        resultEl.classList.remove('success', 'error');

        const formData = new FormData(form);

        try {
            const res = await fetch('/api/spot', {
                method: 'POST',
                body: formData,
            });

            const data = await res.json();

            if (!res.ok) {
                resultEl.textContent = data.error || 'Something went wrong';
                resultEl.classList.add('error');
            } else if (data.target) {
                resultEl.textContent = `🎯 Congrats, you just spotted ${data.target}!`;
                resultEl.classList.add('success');
            } else {
                resultEl.textContent = '📸 Could not identify anyone in the photo.';
                resultEl.classList.add('error');
            }

            resultEl.classList.remove('hidden');
        } catch {
            resultEl.textContent = 'Network error';
            resultEl.classList.add('error');
            resultEl.classList.remove('hidden');
        }
    });
}

// Rotation countdown timer
async function initTimer() {
    const timerEl = document.getElementById('timer');
    if (!timerEl) return;

    let secondsLeft = 0;

    async function sync() {
        try {
            const res = await fetch('/api/rotation-timer');
            const data = await res.json();
            secondsLeft = data.secondsRemaining;
        } catch { /* ignore */ }
    }

    function tick() {
        const mins = String(Math.floor(secondsLeft / 60)).padStart(2, '0');
        const secs = String(secondsLeft % 60).padStart(2, '0');
        timerEl.textContent = `${mins}:${secs}`;

        if (secondsLeft <= 0) {
            timerEl.textContent = '00:00';
            sync();
        } else {
            secondsLeft--;
        }
    }

    await sync();
    tick();
    setInterval(tick, 1000);
    setInterval(sync, 30_000);
}

loadUsers();
initSpotForm();
initTimer();
