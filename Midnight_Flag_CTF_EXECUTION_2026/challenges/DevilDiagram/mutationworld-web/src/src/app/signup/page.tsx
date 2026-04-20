import SignupForm from '@/components/SignupForm';
import Navbar from '@/components/Navbar';

export default function SignupPage() {
    return (
        <main className="min-h-screen bg-[#0A0A0F] pt-32 pb-20 px-4 relative overflow-hidden">
            {}
            <div className="absolute top-1/4 left-1/4 w-96 h-96 bg-[#FF007A]/10 rounded-full blur-[120px] -z-10 animate-pulse-slow"></div>
            <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-[#7B00FF]/10 rounded-full blur-[120px] -z-10 animate-pulse-slow delay-1000"></div>

            <Navbar />
            <div className="max-w-7xl mx-auto flex flex-col items-center">
                <SignupForm />
            </div>
        </main>
    );
}
