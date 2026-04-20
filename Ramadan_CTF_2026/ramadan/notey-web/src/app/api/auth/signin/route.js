import { NextResponse } from 'next/server';
import { findUser } from '@/lib/db';
import { signToken } from '@/lib/auth';
import { serialize } from 'cookie';

export async function POST(req) {
    try {
        const { username, password } = await req.json();

        const user = findUser(username);
        if (!user || user.password !== password) {
            return NextResponse.json({ error: 'Invalid credentials' }, { status: 401 });
        }

        const token = await signToken({ id: user.id, username: user.username });

        const response = NextResponse.json({ message: 'Signed in' });

        response.headers.append('Set-Cookie', serialize('session', token, {
            path: '/',
            httpOnly: false,
            maxAge: 60 * 60 * 24,
            sameSite: 'lax',
            secure: false
        }));

        return response;
    } catch (error) {
        console.error('Signin error:', error);
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}
