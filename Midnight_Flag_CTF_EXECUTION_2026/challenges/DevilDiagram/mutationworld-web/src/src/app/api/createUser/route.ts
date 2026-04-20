import { NextResponse } from 'next/server';
import { createUser, applyDefaults } from '@/lib/db';

export async function POST(req: Request) {


    let baseUser: any = { visitor: true };
    let user: any = null;

    try {
        user = await req.json();

        if (user.isAdmin) {
            return new NextResponse('Nice try! Only admins can create admins.', { status: 403 });
        }
        let newUser = applyDefaults(baseUser, user);
        createUser(newUser);
        const msg = 'Successfully created User';
        return new NextResponse(msg);
    } catch (e: any) {

        return new NextResponse(e.message, { status: 500 });
    }
}

