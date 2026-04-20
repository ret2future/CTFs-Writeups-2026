import fs from 'fs';
import path from 'path';

const DB_PATH = path.join(process.cwd(), 'users.json');

if (!fs.existsSync(DB_PATH)) {
    fs.writeFileSync(DB_PATH, JSON.stringify([], null, 2));
}

export function getUsers() {
    const data = fs.readFileSync(DB_PATH, 'utf8');
    return JSON.parse(data);
}

export function saveUsers(users) {
    fs.writeFileSync(DB_PATH, JSON.stringify(users, null, 2));
}

export function findUser(username) {
    const users = getUsers();
    return users.find(u => u.username === username);
}

export function findUserById(id) {
    const users = getUsers();
    return users.find(u => u.id === id);
}

export function addUser(user) {
    const users = getUsers();
    users.push(user);
    saveUsers(users);
}

export function addNote(userId, note) {
    const users = getUsers();
    const user = users.find(u => u.id === userId);
    if (user) {
        if (!user.notes) user.notes = [];
        user.notes.push(note);
        saveUsers(users);
    }
}

export function getNote(noteId) {
    const users = getUsers();
    for (const user of users) {
        const note = user.notes?.find(n => n.id === noteId);
        if (note) return { ...note, authorId: user.id };
    }
    return null;
}

export function getUserNotes(userId) {
    const user = findUserById(userId);
    return user?.notes || [];
}
