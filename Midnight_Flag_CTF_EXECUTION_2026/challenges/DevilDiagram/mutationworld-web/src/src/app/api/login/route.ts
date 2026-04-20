import { NextResponse } from 'next/server';
import { users } from '@/lib/db';

export async function POST(req: Request) {
    try {
        const { username } = await req.json();
        const user = users.find(u => u.username === username);

        if (!user) {
            return new NextResponse('User not found', { status: 404 });
        }

        const response = new NextResponse('LoggedIn', { status: 200 });


        response.cookies.set('user', JSON.stringify({
            username: user.username,
            isAdmin: !!user.isAdmin,
            picture: user.picture
        }), {
            path: '/',
            httpOnly: false,
            secure: false,
            sameSite: 'lax',
            maxAge: 60 * 60 * 24
        });

        return response;
    } catch (e: any) {
        return new NextResponse(e.message, { status: 500 });
    }
}
