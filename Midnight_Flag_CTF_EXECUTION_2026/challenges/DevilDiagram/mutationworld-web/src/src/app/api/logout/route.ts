import { NextResponse } from 'next/server';
import { cookies } from 'next/headers';

export async function POST() {
    try {
        const cookieStore = await cookies();
        cookieStore.delete('user');
        return new NextResponse('Logged out', { status: 200 });
    } catch (e: any) {
        return new NextResponse(e.message, { status: 500 });
    }
}
