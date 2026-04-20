'use client';
import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function LoginForm() {
    const [username, setUsername] = useState('');
    const [error, setError] = useState('');
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');

        try {
            const res = await fetch('/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username }),
            });
            if (res.ok) {
                router.push('/dashboard');
            } else {
                const text = await res.text();
                setError(text);
            }
        } catch (err: any) {
            setError('Connection failed');
        }
    };

    return (
        <div className="w-full max-w-md mx-auto p-8 rounded-3xl bg-white/5 border border-white/10 backdrop-blur-xl shadow-2xl">
            <h2 className="text-4xl font-extrabold text-center mb-8 bg-gradient-to-r from-[#00F5FF] to-[#7B00FF] bg-clip-text text-transparent">
                Welcome Back
            </h2>
            <form onSubmit={handleSubmit} className="space-y-6">
                <div>
                    <label className="block text-sm font-medium text-white/50 mb-2 ml-1">Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="w-full bg-white/5 border border-white/10 rounded-2xl px-5 py-4 focus:outline-none focus:ring-2 focus:ring-[#00F5FF]/50 focus:border-[#00F5FF] transition-all text-lg"
                        placeholder="Enter your username"
                        required
                    />
                </div>
                <button
                    type="submit"
                    className="w-full py-4 rounded-2xl bg-gradient-to-r from-[#00F5FF] to-[#7B00FF] text-white font-bold text-lg hover:shadow-[0_0_30px_rgba(0,245,255,0.4)] hover:scale-[1.02] active:scale-[0.98] transition-all"
                >
                    Sign In
                </button>
            </form>
            {error && <p className="mt-6 text-center text-red-500 font-medium">{error}</p>}
            <p className="mt-8 text-center text-white/40 text-sm">
                Don't have an account? <span onClick={() => router.push('/signup')} className="text-white/60 hover:underline cursor-pointer">Register here</span>.
            </p>
        </div>
    );
}
