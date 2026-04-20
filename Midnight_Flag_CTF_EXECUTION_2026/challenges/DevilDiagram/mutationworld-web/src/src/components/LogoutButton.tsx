'use client';

import { useRouter } from 'next/navigation';
import { LogOut } from 'lucide-react';

export default function LogoutButton() {
    const router = useRouter();

    const handleLogout = async () => {
        try {
            const res = await fetch('/api/logout', { method: 'POST' });
            if (res.ok) {
                router.push('/login');
                router.refresh();
            }
        } catch (e) {
            console.error('Logout failed', e);
        }
    };

    return (
        <button
            onClick={handleLogout}
            className="flex items-center gap-2 text-white/50 hover:text-red-500 transition-all font-black uppercase text-[10px] tracking-[0.2em]"
        >
            <LogOut size={16} />
            <span>Logout</span>
        </button>
    );
}
