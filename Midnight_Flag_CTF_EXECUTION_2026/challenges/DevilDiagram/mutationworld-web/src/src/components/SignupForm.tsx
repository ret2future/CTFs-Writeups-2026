'use client';
import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function SignupForm() {
    const [username, setUsername] = useState('');
    const [msg, setMsg] = useState('');
    const [error, setError] = useState('');
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setMsg('');
        setError('');

        try {
            const res = await fetch('/api/createUser', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username }),
            });
            const text = await res.text();
            if (res.ok) {
                setMsg(text);
                setTimeout(() => router.push('/login'), 2000);
            } else {
                setError(text);
            }
        } catch (err: any) {
            setError('Something went wrong');
        }
    };

    return (
        <div className="w-full max-w-md mx-auto p-8 rounded-3xl bg-white/5 border border-white/10 backdrop-blur-xl shadow-2xl">
            <h2 className="text-4xl font-extrabold text-center mb-8 bg-gradient-to-r from-[#FF007A] to-[#7B00FF] bg-clip-text text-transparent">
                Join the Mutation
            </h2>
            <form onSubmit={handleSubmit} className="space-y-6">
                <div>
                    <label className="block text-sm font-medium text-white/50 mb-2 ml-1">Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="w-full bg-white/5 border border-white/10 rounded-2xl px-5 py-4 focus:outline-none focus:ring-2 focus:ring-[#FF007A]/50 focus:border-[#FF007A] transition-all text-lg"
                        placeholder="Choose your handle"
                        required
                    />
                </div>
                <button
                    type="submit"
                    className="w-full py-4 rounded-2xl bg-gradient-to-r from-[#FF007A] to-[#7B00FF] text-white font-bold text-lg hover:shadow-[0_0_30px_rgba(255,0,122,0.4)] hover:scale-[1.02] active:scale-[0.98] transition-all"
                >
                    Create Account
                </button>
            </form>
            {msg && <p className="mt-6 text-center text-green-400 font-medium animate-bounce">{msg}</p>}
            {error && <p className="mt-6 text-center text-red-500 font-medium">{error}</p>}
            <p className="mt-8 text-center text-white/40 text-sm">
                By joining, you agree to our <span className="text-white/60 hover:underline cursor-pointer">Mutation Terms</span>.
            </p>
        </div>
    );
}
