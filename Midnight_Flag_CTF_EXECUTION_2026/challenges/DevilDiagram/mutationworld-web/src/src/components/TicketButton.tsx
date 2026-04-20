'use client';

import { useState, useMemo } from 'react';
import { useRouter } from 'next/navigation';
import { Info, X, ShieldCheck } from 'lucide-react';
import QRCode from "react-qr-code";
import Portal from './Portal';

interface TicketButtonProps {
    attractionId: number;
    name: string;
    username: string;
    restricted?: boolean;
    isAdmin?: boolean;
    initialStatus?: 'idle' | 'generating' | 'success';
    canGenerateMore?: boolean;
}

export default function TicketButton({ attractionId, name, username, restricted, isAdmin, initialStatus = 'idle', canGenerateMore = true }: TicketButtonProps) {
    const [status, setStatus] = useState<'idle' | 'generating' | 'success'>(initialStatus);
    const [showQR, setShowQR] = useState(false);
    const [flagData, setFlagData] = useState<string | null>(null);
    const router = useRouter();


    const ticketId = useMemo(() => {
        return Math.random().toString(36).substring(2, 10).toUpperCase() + '-' + Date.now().toString(36).toUpperCase();
    }, []);


    const qrValue = useMemo(() => {
        const data: any = {
            ticket_id: ticketId,
            user: username,
            attraction: name,
            issued_at: new Date().toISOString()
        };
        if (flagData) data.flag = flagData;
        return JSON.stringify(data);
    }, [ticketId, username, name, flagData]);

    const handleClick = async () => {
        if (restricted && !isAdmin) return;
        if (!canGenerateMore && status === 'idle' && !isAdmin) return;

        setStatus('generating');
        try {
            const res = await fetch('/api/generateTicket', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ attractionId }),
            });

            if (res.ok) {

                const contentType = res.headers.get('content-type');
                if (contentType?.includes('application/json')) {
                    const data = await res.json();
                    if (data.flag) setFlagData(data.flag);
                }
                setStatus('success');
                router.refresh();
            } else {
                setStatus('idle');
                alert('Failed to generate ticket');
            }
        } catch (e) {
            setStatus('idle');
            alert('Connection error');
        }
    };

    if (restricted && !isAdmin) {
        return (
            <button className="w-full py-4 rounded-2xl font-black text-xs uppercase tracking-widest transition-all bg-white/5 text-white/20 cursor-not-allowed">
                LOCKED ACCESS
            </button>
        );
    }

    const isLimitReached = !canGenerateMore && status === 'idle' && !isAdmin;

    return (
        <div className="relative w-full">
            {status === 'success' ? (
                <div className="flex gap-2 w-full">
                    <button className="flex-grow py-4 rounded-2xl font-black text-xs uppercase tracking-widest transition-all bg-green-500 text-white shadow-[0_0_20px_rgba(34,197,94,0.4)]">
                        TICKET READY!
                    </button>
                    <button
                        onClick={() => setShowQR(true)}
                        className="p-4 rounded-2xl bg-white/10 hover:bg-white/20 transition-all text-[#00F5FF]"
                    >
                        <Info size={20} />
                    </button>
                </div>
            ) : (
                <button
                    onClick={handleClick}
                    disabled={status === 'generating' || isLimitReached}
                    className={`w-full py-4 rounded-2xl font-black text-xs uppercase tracking-widest transition-all ${isLimitReached ? 'bg-white/5 text-white/20 cursor-not-allowed' : 'bg-white/10 group-hover:bg-[#FF007A] group-hover:text-white group-hover:shadow-[0_0_30px_rgba(255,0,122,0.3)]'} ${status === 'generating' ? 'opacity-50 cursor-wait' : ''}`}
                >
                    {status === 'generating' ? 'GENERATING...' : isLimitReached ? 'LIMIT REACHED' : 'GENERATE TICKET'}
                </button>
            )}

            {showQR && (
                <Portal>
                    <div className="fixed inset-0 z-[100] flex items-center justify-center p-4 bg-black/90 backdrop-blur-xl animate-in fade-in duration-300">
                        <div className="relative w-full max-w-sm glass p-10 rounded-[3rem] flex flex-col items-center animate-in zoom-in-95 duration-500 border border-white/20 shadow-[0_0_50px_rgba(255,0,122,0.2)]">
                            <button
                                onClick={() => setShowQR(false)}
                                className="absolute top-8 right-8 text-white/40 hover:text-white transition-colors"
                            >
                                <X size={24} />
                            </button>

                            <div className="flex items-center gap-3 mb-8">
                                <ShieldCheck className="text-green-500" size={24} />
                                <span className="text-[10px] font-black uppercase tracking-[0.3em] text-green-500">Verified Ticket</span>
                            </div>

                            <h4 className="text-2xl font-black mb-2 tracking-tight text-center uppercase">
                                {name}
                            </h4>
                            <p className="text-white/40 text-[10px] font-bold uppercase tracking-widest mb-10">Valid for: {username}</p>

                            <div className="bg-white p-8 rounded-[2rem] mb-10 neo-shadow relative group">
                                <div className="absolute inset-0 bg-gradient-to-br from-[#FF007A]/10 to-[#7B00FF]/10 rounded-[2rem] opacity-0 group-hover:opacity-100 transition-opacity"></div>
                                <QRCode
                                    value={qrValue}
                                    size={180}
                                    style={{ height: "auto", maxWidth: "100%", width: "100%" }}
                                    viewBox={`0 0 256 256`}
                                />
                            </div>

                            <div className="w-full pt-8 border-t border-white/10 text-center">
                                <p className="text-[10px] uppercase font-black tracking-widest text-white/30 mb-1">Ticket ID</p>
                                <p className="text-sm font-mono text-white/80 font-bold">{ticketId}</p>
                            </div>
                        </div>
                    </div>
                </Portal>
            )}
        </div>
    );
}
