const express = require('express');
const multer = require('multer');
const path = require('path');
const { v4: uuidv4 } = require('uuid');
const fs = require('fs');

const { pool, isAdmin, getRotationTimer } = require('./db');
const { analyzeImage } = require('./vision');

const router = express.Router();

const uploadDir = path.join(__dirname, '..', 'uploads');
if (!fs.existsSync(uploadDir)) fs.mkdirSync(uploadDir);

const storage = multer.diskStorage({
    destination: (_req, _file, cb) => cb(null, uploadDir),
    filename: (_req, file, cb) => cb(null, `${uuidv4()}${path.extname(file.originalname)}`),
});
const upload = multer({ storage, limits: { fileSize: 5 * 1024 * 1024 } });

const rateLimitMap = new Map();
const RATE_LIMIT_WINDOW = 1000; // ms

function rateLimit(req, res, next) {
    const ip = req.ip || req.socket.remoteAddress;
    const now = Date.now();
    const lastRequest = rateLimitMap.get(ip) || 0;

    if (now - lastRequest < RATE_LIMIT_WINDOW) {
        return res.status(429).json({ error: 'Too many requests. Try again in a moment.' });
    }

    rateLimitMap.set(ip, now);
    next();
}

setInterval(() => {
    const cutoff = Date.now() - RATE_LIMIT_WINDOW * 2;
    for (const [ip, ts] of rateLimitMap) {
        if (ts < cutoff) rateLimitMap.delete(ip);
    }
}, 60_000);

// POST /api/spot
router.post('/spot', rateLimit, upload.single('photo'), async (req, res) => {
    try {
        const { spotter } = req.body;
        if (!spotter) {
            return res.status(400).json({ error: 'Missing spotter' });
        }
        if (!req.file) {
            return res.status(400).json({ error: 'Missing photo' });
        }

        const photoPath = `/uploads/${req.file.filename}`;
        const fullFilePath = path.join(uploadDir, req.file.filename);

        // Step 1 — run Google Vision API on the photo
        const visionResult = await analyzeImage(fullFilePath);

        if (!visionResult || visionResult.length === 0) {
            return res.status(400).json({ error: 'Could not identify anyone in the photo.' });
        }

        // Step 2 — check if target is an admin
        for (const detectedText of visionResult) {
            const admin = await isAdmin(detectedText);
            if (admin) {
                return res.status(403).json({ error: 'Cannot spot admin!' });
            }
        }

        const target = visionResult.toString();

        // Step 3 — record the spot
        await pool.query(
            `INSERT INTO spots (spotter, target, photo_path) VALUES ($1, $2, $3)`,
            [spotter, target, photoPath],
        );

        return res.json({ success: true, target });
    } catch (err) {
        console.error('[ERROR] /api/spot', err);
        return res.status(500).json({ error: 'Internal server error' });
    }
});

// GET /api/users
router.get('/users', async (_req, res) => {
    try {
        const result = await pool.query(
            "SELECT name FROM users WHERE role = 'user' ORDER BY name"
        );
        return res.json(result.rows.map((r) => r.name));
    } catch (err) {
        console.error('[ERROR] /api/users', err);
        return res.status(500).json({ error: 'Internal server error' });
    }
});

// POST /api/flag
router.post('/flag', express.json(), async (req, res) => {
    try {
        const { username } = req.body;
        if (!username) {
            return res.status(400).json({ error: 'Missing username' });
        }

        const result = await pool.query(
            'SELECT role FROM users WHERE name = $1',
            [username]
        );

        if (result.rows.length === 0 || result.rows[0].role !== 'admin') {
            return res.status(403).json({ error: 'Incorrect admin username.' });
        }

        const flag = process.env.FLAG || 'FLAG{not_set}';
        return res.json({ flag });
    } catch (err) {
        console.error('[ERROR] /api/flag', err);
        return res.status(500).json({ error: 'Internal server error' });
    }
});

// GET /api/rotation-timer
router.get('/rotation-timer', (_req, res) => {
    res.json({ secondsRemaining: getRotationTimer() });
});

module.exports = router;
