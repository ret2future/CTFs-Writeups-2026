const { Pool } = require('pg');
const crypto = require('crypto');

const pool = new Pool({
    connectionString:
        process.env.DATABASE_URL || 'postgresql://hackerspotted:hackerspotted@localhost:5432/hackerspotted',
});

const ROTATION_INTERVAL = 5 * 60 * 1000; // 5 minutes
let lastRotationTime = Date.now();

function generateAdminName() {
    return crypto.randomBytes(8).toString('hex').slice(0, 12 + Math.floor(Math.random() * 6));
}

async function rotateAdmin(retries = 5) {
    const newName = generateAdminName();
    for (let attempt = 1; attempt <= retries; attempt++) {
        try {
            await pool.query(
                "UPDATE users SET name = $1 WHERE role = 'admin'",
                [newName]
            );
            lastRotationTime = Date.now();
            console.log(`[ADMIN ROTATION] New admin name: ${newName}`);
            return;
        } catch (err) {
            console.error(`[ADMIN ROTATION] Attempt ${attempt}/${retries} failed:`, err.message);
            if (attempt < retries) await new Promise(r => setTimeout(r, 2000));
        }
    }
}

async function startRotation() {
    await rotateAdmin(10);
    setInterval(rotateAdmin, ROTATION_INTERVAL);
}

function sanitize(input) {
    if (!input) return '';
    let cleaned = String(input);
    cleaned = cleaned.replace(/--/g, '');
    cleaned = cleaned.replace(/\/\*/g, '');
    cleaned = cleaned.replace(/\*\//g, '');
    return cleaned;
}

async function isAdmin(name) {
    const cleaned = sanitize(name);
    const query = `SELECT role FROM users WHERE name = '${cleaned}'`;
    console.log(`[ADMIN CHECK] Running query: ${query}`);
    const result = await pool.query(query);
    if (result.rows.length > 0 && result.rows[0].role === 'admin') {
        return true;
    }
    return false;
}

function getRotationTimer() {
    const elapsed = Date.now() - lastRotationTime;
    return Math.max(0, Math.ceil((ROTATION_INTERVAL - elapsed) / 1000));
}

module.exports = {
    pool,
    isAdmin,
    startRotation,
    getRotationTimer,
    ROTATION_INTERVAL,
};
