import { NextResponse } from 'next/server';
import { visitNote } from '@/lib/bot';

export async function POST(req) {
    try {
        const formData = await req.formData();
        const noteId = formData.get('noteId');

        if (!noteId) {
            return NextResponse.json({ error: 'Note ID required' }, { status: 400 });
        }

        const uuidRegex = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
        if (!uuidRegex.test(noteId)) {
            return NextResponse.json({ error: 'Invalid Note ID format. Please provide a UUID, not a URL.' }, { status: 400 });
        }

        visitNote(noteId).catch(err => console.error('Bot background error:', err));

        return new NextResponse('Admin is on the way! They will check your note shortly.', {
            status: 200,
            headers: { 'Content-Type': 'text/plain' }
        });
    } catch (error) {
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}
