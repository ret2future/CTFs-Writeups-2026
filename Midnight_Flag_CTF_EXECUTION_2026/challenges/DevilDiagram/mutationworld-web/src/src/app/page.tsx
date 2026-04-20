import Navbar from '@/components/Navbar';
import Link from 'next/link';

export default function Home() {
  return (
    <main className="min-h-screen bg-[#0A0A0F] text-white selection:bg-[#FF007A] selection:text-white relative overflow-hidden">
      <Navbar />

      {}
      <div className="absolute top-[-10%] left-[-10%] w-[50%] h-[50%] bg-[#FF007A]/10 rounded-full blur-[120px] -z-10"></div>
      <div className="absolute bottom-[-10%] right-[-10%] w-[50%] h-[50%] bg-[#7B00FF]/10 rounded-full blur-[120px] -z-10"></div>

      {}
      <section className="pt-48 pb-32 px-8">
        <div className="max-w-7xl mx-auto flex flex-col items-center text-center">
          <div className="inline-block px-4 py-1.5 mb-8 rounded-full bg-white/5 border border-white/10 text-xs font-black tracking-[0.3em] uppercase opacity-70">
            The Future of Theme Parks
          </div>
          <h1 className="text-7xl md:text-9xl font-black mb-12 leading-[0.9] tracking-tighter">
            MUTATE YOUR <br />
            <span className="bg-gradient-to-r from-[#FF007A] via-[#7B00FF] to-[#00F5FF] bg-clip-text text-transparent">
              REALITY
            </span>
          </h1>
          <p className="max-w-2xl text-xl text-white/40 mb-16 font-medium leading-relaxed">
            Welcome to Mutation World. A sanctuary where genetic wonder meets neon-soaked adrenaline. Step into the rift and experience rides that defy the laws of nature.
          </p>
          <div className="flex flex-col sm:flex-row gap-8">
            <Link href="/signup" className="px-12 py-5 rounded-3xl bg-white text-black font-black text-lg hover:bg-[#FF007A] hover:text-white transition-all shadow-xl">
              GET STARTED
            </Link>
            <Link href="/signup" className="px-12 py-5 rounded-3xl bg-white/5 border border-white/10 font-black text-lg hover:bg-white/10 transition-all">
              EXPLORE RIDES
            </Link>
          </div>
        </div>
      </section>

      {}
      <section className="py-24 border-y border-white/5 bg-white/[0.02]">
        <div className="max-w-7xl mx-auto px-8 grid grid-cols-1 md:grid-cols-3 gap-16">
          <div className="text-center group">
            <div className="text-5xl font-black text-[#FF007A] mb-4">50+</div>
            <div className="text-white/40 font-black tracking-widest uppercase text-xs">Neon Rides</div>
          </div>
          <div className="text-center group">
            <div className="text-5xl font-black text-[#7B00FF] mb-4">12</div>
            <div className="text-white/40 font-black tracking-widest uppercase text-xs">Vivid Shows</div>
          </div>
          <div className="text-center group">
            <div className="text-5xl font-black text-[#00F5FF] mb-4">∞</div>
            <div className="text-white/40 font-black tracking-widest uppercase text-xs">Pure Adrenaline</div>
          </div>
        </div>
      </section>
    </main>
  );
}
