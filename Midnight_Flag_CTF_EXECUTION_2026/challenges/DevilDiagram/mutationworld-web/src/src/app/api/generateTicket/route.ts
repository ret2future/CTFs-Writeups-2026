import { NextResponse } from 'next/server';
import { cookies } from 'next/headers';
import { generateTicket, tickets, ATTRACTIONS } from '@/lib/db';

const FLAG = process.env.FLAG || 'VBD{f4k3_fl4g_f0r_t3st1ng}';

export async function POST(req: Request) {
    try {
        const { attractionId } = await req.json();
        const cookieStore = await cookies();
        const userCookie = cookieStore.get('user');

        if (!userCookie) {
            return new NextResponse('Unauthorized', { status: 401 });
        }

        const user = JSON.parse(userCookie.value);
        const attraction = ATTRACTIONS.find(a => a.id === attractionId);

        if (!attraction) {
            return new NextResponse('Invalid attraction', { status: 400 });
        }


        if (attraction.restricted && !user.isAdmin) {
            return new NextResponse('Access Denied: Admin clearance required', { status: 403 });
        }


        if (!user.isAdmin) {
            const userTickets = tickets.filter(t => t.username === user.username);
            if (userTickets.length >= 2) {
                return new NextResponse('Free Ride Limit Reached', { status: 403 });
            }
        }

        generateTicket(user.username, attractionId);


        if (attraction.name === 'Capture The Flag') {
            return NextResponse.json({ message: 'Ticket Generated', flag: FLAG });
        }

        return new NextResponse('Ticket Generated', { status: 200 });
    } catch (e: any) {
        return new NextResponse(e.message, { status: 500 });
    }
}
