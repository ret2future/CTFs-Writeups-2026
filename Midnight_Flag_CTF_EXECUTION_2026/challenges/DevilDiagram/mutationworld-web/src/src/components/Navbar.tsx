import Link from 'next/link';
import { cookies } from 'next/headers';
import LogoutButton from './LogoutButton';

export default async function Navbar() {
    const cookieStore = await cookies();
    const userCookie = cookieStore.get('user');
    let user = null;
    try {
        user = userCookie ? JSON.parse(userCookie.value) : null;
    } catch (e) {
        user = null;
    }

    return (
        <nav className="fixed top-0 left-0 right-0 z-50 glass neo-shadow px-6 md:px-12 py-5 flex justify-between items-center bg-[#0A0A0F]/90 backdrop-blur-xl border-b border-white/10 transition-all">
            <Link href="/" className="text-xl md:text-3xl font-black bg-gradient-to-r from-[#FF007A] to-[#7B00FF] bg-clip-text text-transparent drop-shadow-[0_0_15px_rgba(255,0,122,0.4)] tracking-tighter">
                MUTATION WORLD
            </Link>
            <div className="flex items-center gap-6 md:gap-10 font-bold uppercase text-[10px] tracking-[0.2em]">
                <Link href="/rides" className="text-white/50 hover:text-white transition-all">Rides</Link>
                <Link href="/shows" className="text-white/50 hover:text-white transition-all">Shows</Link>
                {user ? (
                    <div className="flex items-center gap-6 md:gap-10">
                        <Link href="/dashboard" className="relative group overflow-hidden px-8 py-3 rounded-full bg-white text-black font-black hover:scale-105 transition-all">
                            Dashboard
                        </Link>
                        <LogoutButton />
                    </div>
                ) : (
                    <div className="flex items-center gap-8">
                        <Link href="/login" className="text-white/50 hover:text-white transition-all">Login</Link>
                        <Link href="/signup" className="px-8 py-3 rounded-full border-2 border-[#FF007A] text-[#FF007A] font-black hover:bg-[#FF007A] hover:text-white transition-all shadow-[0_0_20px_rgba(255,0,122,0.3)]">
                            Join Us
                        </Link>
                    </div>
                )}
            </div>
        </nav>
    );
}
