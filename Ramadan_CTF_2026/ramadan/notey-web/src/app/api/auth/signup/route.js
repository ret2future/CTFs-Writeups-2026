import { NextResponse } from 'next/server';
import { v4 as uuidv4 } from 'uuid';
import { addUser, findUser } from '@/lib/db';

export async function POST(req) {
    try {
        const { username, password } = await req.json();

        if (!username || !password) {
            return NextResponse.json({ error: 'Username and password required' }, { status: 400 });
        }

        if (findUser(username)) {
            return NextResponse.json({ error: 'User already exists' }, { status: 400 });
        }

        const newUser = {
            id: uuidv4(),
            username,
            password,
            notes: []
        };

        addUser(newUser);

        return NextResponse.json({ message: 'User created' });
    } catch (error) {
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}
