'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function SignIn() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [isSignUp, setIsSignUp] = useState(false);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess('');
        setLoading(true);

        const endpoint = isSignUp ? '/api/auth/signup' : '/api/auth/signin';
        try {
            const res = await fetch(endpoint, {
                method: 'POST',
                body: JSON.stringify({ username, password }),
                headers: { 'Content-Type': 'application/json' }
            });

            const data = await res.json();

            if (res.ok) {
                if (isSignUp) {
                    setIsSignUp(false);
                    setSuccess('Account created successfully! Please sign in.');
                    setUsername('');
                    setPassword('');
                } else {
                    router.push('/dashboard');
                }
            } else {
                setError(data.error || 'Something went wrong');
            }
        } catch (err) {
            setError('Connection refused. Is the server running?');
        } finally {
            setLoading(false);
        }
    }

    const toggleMode = () => {
        setIsSignUp(!isSignUp);
        setError('');
        setSuccess('');
    };

    return (
        <main style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', minHeight: '85vh', padding: '20px' }}>
            <div style={{ width: '100%', maxWidth: '360px' }}>
                <div style={{ textAlign: 'center', marginBottom: '40px' }}>
                    <h1 style={{ fontSize: '28px', marginBottom: '4px' }}>Notey</h1>
                    <p style={{ color: 'var(--gray-dark)', fontSize: '15px' }}>Your personal minimal workspace.</p>
                </div>

                {error && (
                    <div style={{ background: '#fff2f0', border: '1px solid #ffccc7', color: '#ff4d4f', padding: '12px', borderRadius: '6px', fontSize: '14px', marginBottom: '20px', display: 'flex', alignItems: 'center', gap: '8px' }}>
                        {error}
                    </div>
                )}

                {success && (
                    <div style={{ background: '#f6ffed', border: '1px solid #b7eb8f', color: '#52c41a', padding: '12px', borderRadius: '6px', fontSize: '14px', marginBottom: '20px', display: 'flex', alignItems: 'center', gap: '8px' }}>
                        {success}
                    </div>
                )}

                <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
                    <div className="form-group" style={{ marginBottom: 0 }}>
                        <label className="form-label">Username</label>
                        <input
                            className="text-input"
                            placeholder="Enter your username"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group" style={{ marginBottom: 0 }}>
                        <label className="form-label">Password</label>
                        <input
                            type="password"
                            className="text-input"
                            placeholder="••••••••"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button
                        type="submit"
                        className="button button-primary"
                        style={{ height: '40px', fontSize: '15px', marginTop: '10px' }}
                        disabled={loading}
                    >
                        {loading ? 'Processing...' : (isSignUp ? 'Create account' : 'Sign in')}
                    </button>
                </form>

                <div style={{ marginTop: '32px', textAlign: 'center', fontSize: '14px', color: 'var(--gray-dark)', borderTop: '1px solid var(--border)', paddingTop: '24px' }}>
                    {isSignUp ? (
                        <p>Already have an account? <span style={{ color: 'var(--primary)', cursor: 'pointer', fontWeight: 600 }} onClick={toggleMode}>Sign In</span></p>
                    ) : (
                        <p>New to Notey? <span style={{ color: 'var(--primary)', cursor: 'pointer', fontWeight: 600 }} onClick={toggleMode}>Create an account</span></p>
                    )}
                </div>
            </div>
        </main>
    );
}
