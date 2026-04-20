import { NextResponse } from 'next/server';
import { jwtVerify } from 'jose';

const secret = new TextEncoder().encode(
    process.env.JWT_SECRET || 'notes_ar3_s0_much_s3cure_vbd_ctf'
);

export async function middleware(request) {
    const token = request.cookies.get('session')?.value;
    const { pathname } = request.nextUrl;

    let user = null;
    if (token) {
        try {
            const { payload } = await jwtVerify(token, secret);
            user = payload;
        } catch (err) { }
    }

    const protectedPaths = ['/dashboard', '/report', '/notes'];
    const isProtectedPath = protectedPaths.some(path => pathname.startsWith(path));

    const isAuthPath = pathname === '/';

    if (user) {
        if (isAuthPath) {
            return NextResponse.redirect(new URL('/dashboard', request.url));
        }
    } else {
        if (isProtectedPath) {
            const response = NextResponse.redirect(new URL('/', request.url));
            if (token) {
                response.cookies.delete('session');
            }
            return response;
        }
    }

    return NextResponse.next();
}

export const config = {
    matcher: ['/', '/dashboard/:path*', '/report/:path*', '/notes/:path*'],
};
