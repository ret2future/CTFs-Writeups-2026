import { NextResponse } from 'next/server';
import { v4 as uuidv4 } from 'uuid';
import { addNote } from '@/lib/db';
import { getSessionUser } from '@/lib/auth';

export async function POST(req) {
    try {
        const user = await getSessionUser();

        if (!user) {
            return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
        }

        const { title, content } = await req.json();

        const newNote = {
            id: uuidv4(),
            title: title || 'Untitled',
            content: content || '',
            createdAt: new Date().toISOString()
        };

        addNote(user.id, newNote);

        return NextResponse.json(newNote);
    } catch (error) {
        console.error('Create note error:', error);
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}
