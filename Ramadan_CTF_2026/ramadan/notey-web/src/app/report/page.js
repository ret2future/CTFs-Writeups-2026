'use client';

import { useState, useEffect, Suspense } from 'react';
import { useSearchParams } from 'next/navigation';
import Link from 'next/link';

function ReportForm() {
    const searchParams = useSearchParams();
    const [noteId, setNoteId] = useState('');
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const id = searchParams.get('noteId');
        if (id) setNoteId(id);
    }, [searchParams]);

    async function handleSubmit(e) {
        e.preventDefault();

        const uuidRegex = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
        if (!uuidRegex.test(noteId)) {
            setMessage('Invalid format. Please enter a valid Note UUID (e.g. 550e8400-e29b-41d4-a716-446655440000).');
            return;
        }

        setLoading(true);
        setMessage('');

        const formData = new FormData();
        formData.append('noteId', noteId);

        try {
            const res = await fetch('/api/visit', {
                method: 'POST',
                body: formData,
            });

            const text = await res.text();

            if (res.ok) {
                setMessage(text);
                setNoteId('');
            } else {
                try {
                    const errorData = JSON.parse(text);
                    setMessage(errorData.error || 'Failed to send report.');
                } catch (jsonErr) {
                    setMessage(text || 'Failed to send report.');
                }
            }
        } catch (err) {
            setMessage('Failed to send report. Please check your connection.');
        } finally {
            setLoading(false);
        }
    }

    return (
        <main>
            <nav className="nav">
                <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    <span style={{ fontSize: '20px' }}>📓</span>
                    <Link href="/dashboard" className="nav-link" style={{ fontWeight: 700, color: 'var(--foreground)', paddingLeft: 0 }}>Notey</Link>
                </div>
                <Link href="/dashboard" className="nav-link">← Dashboard</Link>
            </nav>

            <div style={{ maxWidth: '540px', margin: '40px auto 0' }}>
                <h1 style={{ fontSize: '32px', marginBottom: '12px' }}>Admin Review</h1>
                <p style={{ color: 'var(--gray-dark)', marginBottom: '40px', fontSize: '16px' }}>
                    If you suspect a note contains malicious content or violates policies, report it here. Our automated system will review it shortly.
                </p>

                <div className="card" style={{ padding: '32px', cursor: 'default', boxShadow: 'var(--shadow-md)', border: '1px solid var(--border-strong)' }}>
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label className="form-label">Note Identifier (UUID)</label>
                            <input
                                className="text-input"
                                placeholder="550e8400-e29b-41d4-a716-446655440000"
                                value={noteId}
                                onChange={e => setNoteId(e.target.value)}
                                required
                            />
                        </div>
                        <button
                            type="submit"
                            className="button button-primary"
                            style={{ width: '100%', height: '44px', fontSize: '16px' }}
                            disabled={loading}
                        >
                            {loading ? 'Submitting...' : 'Submit to Admin'}
                        </button>
                    </form>

                    {message && (
                        <div style={{
                            marginTop: '24px',
                            padding: '16px',
                            borderRadius: '8px',
                            background: message.includes('Admin') ? '#f0f7ff' : '#fff2f0',
                            border: `1px solid ${message.includes('Admin') ? '#d0e7ff' : '#ffccc7'}`,
                            color: message.includes('Admin') ? '#0052cc' : '#ff4d4f',
                            fontSize: '14px',
                            display: 'flex',
                            alignItems: 'center',
                            gap: '8px'
                        }}>
                            <span>{message.includes('Admin') ? '📨' : '❌'}</span> {message}
                        </div>
                    )}
                </div>

                <div style={{ marginTop: '32px', padding: '20px', borderRadius: '8px', background: 'var(--gray-light)', fontSize: '13px', color: 'var(--gray-dark)' }}>
                    <strong>Note:</strong> Reports are processed by a Puppeteer-based headless browser. The admin will check the provided UUID from their current session.
                </div>
            </div>
        </main>
    );
}

export default function ReportPage() {
    return (
        <Suspense fallback={<main><nav className="nav" /><div style={{ padding: '80px 40px' }}>Loading...</div></main>}>
            <ReportForm />
        </Suspense>
    );
}
