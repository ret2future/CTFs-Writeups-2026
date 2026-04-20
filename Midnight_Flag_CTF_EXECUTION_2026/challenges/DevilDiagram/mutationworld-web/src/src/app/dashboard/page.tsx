import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';
import Navbar from '@/components/Navbar';
import TicketButton from '@/components/TicketButton';
import { tickets, ATTRACTIONS } from '@/lib/db';

export default async function DashboardPage() {
    const cookieStore = await cookies();
    const userCookie = cookieStore.get('user');

    if (!userCookie) {
        redirect('/login');
    }

    let user: any;
    try {
        user = JSON.parse(userCookie.value);
    } catch (e) {
        redirect('/login');
    }

    const userTickets = tickets.filter(t => t.username === user.username);
    const freeRidesLimit = 2;
    const ridesUsed = userTickets.length;
    const ridesRemaining = Math.max(0, freeRidesLimit - ridesUsed);
    const canGenerateMore = user.isAdmin || ridesRemaining > 0;

    const attractions = ATTRACTIONS;

    return (
        <main className="min-h-screen bg-[#0A0A0F] pt-32 pb-20 px-8 lg:px-16">
            <Navbar />
            <div className="max-w-7xl mx-auto">
                <header className="mb-16 flex flex-col md:flex-row justify-between items-start md:items-end gap-6">
                    <div>
                        <h1 className="text-6xl font-black mb-4 tracking-tight">
                            Welcome, <span className="bg-gradient-to-r from-[#FF007A] to-[#7B00FF] bg-clip-text text-transparent">{user.username}</span>
                        </h1>
                        <p className="text-white/40 text-xl max-w-lg">Your journey through the neon universe begins here. What will you experience today?</p>
                    </div>
                    <div className="flex flex-col items-end gap-4">
                        <div className="flex flex-col items-end gap-2">
                            {!user.isAdmin && (
                                <>
                                    <span className="text-[10px] font-black text-[#FF007A] uppercase tracking-[0.3em] mb-1 animate-pulse">
                                        Limited Time Free Offer
                                    </span>
                                    <span className={`px-6 py-2 rounded-full text-xs font-black tracking-widest border-2 transition-all ${ridesRemaining === 0 ? 'border-red-500 text-red-500 bg-red-500/5' : 'border-green-500 text-green-500 bg-green-500/5'}`}>
                                        {ridesRemaining} FREE RIDES REMAINING
                                    </span>
                                </>
                            )}
                            <span className={`px-6 py-2 rounded-full text-xs font-black tracking-widest border-2 transition-all ${user.isAdmin ? 'border-red-500 text-red-500 animate-pulse bg-red-500/5' : 'border-[#00F5FF] text-[#00F5FF] bg-[#00F5FF]/5'}`}>
                                {user.isAdmin ? 'ADMIN' : 'IDENTITY: VISITOR'}
                            </span>
                        </div>
                    </div>
                </header>

                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                    {attractions.map((item) => (
                        <div key={item.name} className={`relative group overflow-hidden bg-white/5 border border-white/10 p-10 rounded-[2.5rem] transition-all hover:-translate-y-2 ${item.restricted && !user.isAdmin ? 'opacity-40 grayscale pointer-events-none' : 'hover:border-[#FF007A]/50 hover:bg-white/[0.08]'}`}>
                            {item.restricted && user.isAdmin && (
                                <div className="absolute top-0 right-0 w-32 h-32 bg-red-600/20 blur-[60px] -z-10 group-hover:bg-red-600/40 transition-all"></div>
                            )}

                            <div className="flex justify-between items-start mb-10">
                                <div className="flex flex-col gap-1">
                                    <span className="text-[10px] font-black uppercase tracking-[0.2em] text-white/30">{item.type}</span>
                                    {!item.restricted && (
                                        <span className="text-[9px] font-black text-[#FF007A] uppercase tracking-widest animate-pulse">Limited Time Free</span>
                                    )}
                                </div>
                                <span className={`text-[10px] font-black px-3 py-1 rounded-full border ${item.category === 'Admin Only' ? 'border-red-500/50 text-red-500 bg-red-500/10' : 'border-[#00F5FF]/50 text-[#00F5FF] bg-[#00F5FF]/10'}`}>
                                    {item.category}
                                </span>
                            </div>

                            <h3 className="text-3xl font-bold mb-8 group-hover:text-white transition-colors">{item.name}</h3>

                            <div className="mt-4">
                                <TicketButton
                                    attractionId={item.id}
                                    name={item.name}
                                    username={user.username}
                                    restricted={item.restricted}
                                    isAdmin={user.isAdmin}
                                    initialStatus={userTickets.some(t => t.attractionId === item.id) ? 'success' : 'idle'}
                                    canGenerateMore={canGenerateMore}
                                />
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </main>
    );
}
