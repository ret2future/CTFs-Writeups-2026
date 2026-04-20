
export type User = {
    username: string;
    isAdmin?: boolean;
    picture?: string;
    [key: string]: any;
};

export type Ticket = {
    username: string;
    attractionId: number;
    timestamp: number;
};

export const ATTRACTIONS = [
    { id: 1, name: 'Rollercoaster Ride', type: 'Ride', category: 'General' },
    { id: 2, name: 'Battledom', type: 'Ride', category: 'General' },
    { id: 3, name: 'Neon Circus', type: 'Show', category: 'VIP' },
    { id: 4, name: 'Cyber Realm', type: 'Show', category: 'Standard' },
    { id: 5, name: 'Capture The Flag', type: 'Show', category: 'Admin Only', restricted: true },
];


const globalForDb = global as unknown as {
    users: User[];
    tickets: Ticket[];
};

export const users = globalForDb.users || [];

if (!globalForDb.tickets) {
    globalForDb.tickets = [];
}
export const tickets = globalForDb.tickets;

if (process.env.NODE_ENV !== 'production') {
    globalForDb.users = users;
    globalForDb.tickets = tickets;
}


export function applyDefaults(target: any, source: any): any {
    for (const key of Object.keys(source)) {
        target[key] = source[key];
    }
    return target;
}

export function createUser(user: User) {
    users.push(user);
}

export function generateTicket(username: string, attractionId: number) {
    tickets.push({ username, attractionId, timestamp: Date.now() });
}
