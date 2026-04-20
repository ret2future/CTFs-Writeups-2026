'use client';

import { useState, useEffect } from 'react';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

export default function Dashboard() {
    const [notes, setNotes] = useState([]);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [loading, setLoading] = useState(true);
    const router = useRouter();

    useEffect(() => {
        fetchNotes();
    }, []);

    async function fetchNotes() {
        try {
            const res = await fetch('/api/notes/list');
            if (res.ok) {
                const data = await res.json();
                setNotes(data);
            } else if (res.status === 401) {
                router.push('/');
            }
        } finally {
            setLoading(false);
        }
    }

    async function handleCreateNote(e) {
        e.preventDefault();
        if (!title && !content) return;

        const res = await fetch('/api/notes', {
            method: 'POST',
            body: JSON.stringify({ title, content }),
            headers: { 'Content-Type': 'application/json' }
        });

        if (res.ok) {
            setTitle('');
            setContent('');
            fetchNotes();
        }
    }

    return (
        <main>
            <nav className="nav">
                <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    <span style={{ fontSize: '20px' }}>📓</span>
                    <Link href="/dashboard" className="nav-link" style={{ fontWeight: 700, color: 'var(--foreground)', paddingLeft: 0 }}>Notey</Link>
                </div>
                <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    <Link href="/report" className="nav-link">Report</Link>
                    <div style={{ width: '1px', height: '16px', background: 'var(--border)', margin: '0 4px' }}></div>
                    <button className="nav-link" style={{ border: 'none', background: 'none', cursor: 'pointer', fontFamily: 'inherit' }} onClick={async () => {
                        await fetch('/api/auth/signout', { method: 'POST' });
                        router.push('/');
                    }}>Sign out</button>
                </div>
            </nav>

            <section style={{ marginBottom: '60px' }}>
                <h1 style={{ marginBottom: '12px' }}>Workspace</h1>
                <p style={{ color: 'var(--gray-dark)', fontSize: '16px', marginBottom: '40px' }}>Capture your thoughts and organize your life.</p>

                <form onSubmit={handleCreateNote} style={{
                    background: 'var(--gray-light)',
                    padding: '24px',
                    borderRadius: '12px',
                    border: '1px solid var(--border)'
                }}>
                    <input
                        className="note-title-input"
                        style={{ fontSize: '24px', marginBottom: '8px' }}
                        placeholder="Untitled"
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                    />
                    <textarea
                        className="note-input"
                        placeholder="Start typing your markdown..."
                        rows="3"
                        value={content}
                        onChange={e => setContent(e.target.value)}
                    />
                    <div style={{ marginTop: '16px', display: 'flex', justifyContent: 'flex-end' }}>
                        <button type="submit" className="button button-primary">Save Note</button>
                    </div>
                </form>
            </section>

            <section>
                <h2 style={{ fontSize: '14px', textTransform: 'uppercase', letterSpacing: '0.05em', color: 'var(--gray-dark)', marginBottom: '20px' }}>Recently Active</h2>

                {loading ? (
                    <div style={{ color: 'var(--gray-dark)', fontSize: '14px' }}>Loading your storage...</div>
                ) : (
                    <div className="notes-list">
                        {notes.length === 0 && (
                            <div style={{
                                textAlign: 'center',
                                padding: '60px 40px',
                                border: '2px dashed var(--border)',
                                borderRadius: '12px',
                                color: 'var(--gray-dark)'
                            }}>
                                <div style={{ fontSize: '32px', marginBottom: '16px' }}>☁️</div>
                                <p>No notes found. Create one above to get started.</p>
                            </div>
                        )}
                        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(260px, 1fr))', gap: '16px' }}>
                            {notes.map(note => (
                                <Link key={note.id} href={`/notes/${note.id}`} className="card" style={{ display: 'flex', flexDirection: 'column', textDecoration: 'none', color: 'inherit', height: '100%' }}>
                                    <h3 style={{ margin: '0 0 10px 0', fontSize: '18px', fontWeight: 600 }}>{note.title || 'Untitled'}</h3>
                                    <p style={{ margin: '0 0 20px 0', fontSize: '14px', color: 'var(--gray-dark)', flexGrow: 1, overflow: 'hidden', display: '-webkit-box', WebkitLineClamp: 3, WebkitBoxOrient: 'vertical' }}>
                                        {note.content || 'No content yet...'}
                                    </p>
                                    <div style={{ marginTop: 'auto', display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                                        <span className="badge">{new Date(note.createdAt).toLocaleDateString()}</span>
                                    </div>
                                </Link>
                            ))}
                        </div>
                    </div>
                )}
            </section>
        </main>
    );
}
