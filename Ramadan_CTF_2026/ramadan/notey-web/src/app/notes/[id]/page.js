import { getNote } from '@/lib/db';
import { render } from 'slimdown-js';
import DOMPurify from 'isomorphic-dompurify';
import Link from 'next/link';
import { notFound } from 'next/navigation';

export default async function NotePage({ params }) {
    const { id } = await params;
    const note = getNote(id);

    if (!note) {
        notFound();
    }

    const markdown = note.content || '';
    const cleanMarkdown = DOMPurify.sanitize(markdown);
    const html = render(cleanMarkdown);

    return (
        <main>
            <nav className="nav">
                <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    <span style={{ fontSize: '20px' }}>📓</span>
                    <Link href="/dashboard" className="nav-link" style={{ fontWeight: 700, color: 'var(--foreground)', paddingLeft: 0 }}>Notey</Link>
                </div>
                <Link href="/dashboard" className="nav-link">← All Notes</Link>
            </nav>

            <div style={{ marginTop: '20px', marginBottom: '40px' }}>
                <h1 className="note-title" style={{ marginBottom: '16px' }}>{note.title || 'Untitled'}</h1>
                <div className="note-meta" style={{ display: 'flex', alignItems: 'center', gap: '12px', fontSize: '14px', color: 'var(--gray-dark)' }}>
                    <span className="badge" style={{ background: 'var(--gray-light)', color: 'var(--gray-dark)' }}>Draft</span>
                    <span>Created on {new Date(note.createdAt).toLocaleDateString()} at {new Date(note.createdAt).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</span>
                </div>
            </div>

            <div
                className="note-content"
                style={{ minHeight: '300px', paddingBottom: '100px', borderTop: '1px solid var(--border)', paddingTop: '40px' }}
                dangerouslySetInnerHTML={{ __html: html }}
            />

            <footer style={{
                position: 'fixed',
                bottom: '0',
                left: '0',
                right: '0',
                background: 'rgba(255, 255, 255, 0.8)',
                backdropFilter: 'blur(8px)',
                borderTop: '1px solid var(--border)',
                padding: '16px 40px',
                zIndex: 100
            }}>
                <div style={{ maxWidth: '900px', margin: '0 auto', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <div style={{ fontSize: '14px', color: 'var(--gray-dark)' }}>
                        <strong>Admin Zone:</strong> Need a review?
                    </div>
                    <Link href={`/report?noteId=${id}`} className="button button-primary">Request Review</Link>
                </div>
            </footer>
        </main>
    );
}
