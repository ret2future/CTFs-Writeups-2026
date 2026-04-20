-- Schema
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    role TEXT NOT NULL DEFAULT 'user'
);

CREATE TABLE IF NOT EXISTS spots (
    id SERIAL PRIMARY KEY,
    spotter TEXT NOT NULL,
    target TEXT NOT NULL,
    photo_path TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Seed data
INSERT INTO users (name, role) VALUES
    ('alice',       'user'),
    ('bob',         'user'),
    ('charlie',     'user'),
    ('diana',       'user'),
    ('eve',         'user'),
    ('s3cr3t_4dm1n', 'admin')
ON CONFLICT (name) DO NOTHING;
