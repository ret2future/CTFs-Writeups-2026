import { NextResponse } from 'next/server';
import { getUserNotes } from '@/lib/db';
import { getSessionUser } from '@/lib/auth';

export async function GET() {
    try {
        const user = await getSessionUser();

        if (!user) {
            return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
        }

        const notes = getUserNotes(user.id);
        return NextResponse.json(notes);
    } catch (error) {
        console.error('List notes error:', error);
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}
