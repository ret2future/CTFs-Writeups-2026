import { NextResponse } from 'next/server';
import { serialize } from 'cookie';

export async function POST() {
    const response = NextResponse.json({ message: 'Signed out' });

    response.headers.append('Set-Cookie', serialize('session', '', {
        path: '/',
        expires: new Date(0),
        httpOnly: true
    }));

    return response;
}
