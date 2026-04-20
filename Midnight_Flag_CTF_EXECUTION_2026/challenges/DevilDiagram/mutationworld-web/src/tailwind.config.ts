import type { Config } from "tailwindcss";

export default {
    content: [
        "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    theme: {
        extend: {
            colors: {
                background: "var(--background)",
                foreground: "var(--foreground)",
                primary: {
                    DEFAULT: "#FF007A",
                    glow: "#FF00C8",
                },
                secondary: {
                    DEFAULT: "#7B00FF",
                    glow: "#A044FF",
                },
                accent: {
                    DEFAULT: "#00F5FF",
                    glow: "#00D1FF",
                },
                card: {
                    DEFAULT: "rgba(20, 20, 30, 0.8)",
                    hover: "rgba(30, 30, 45, 0.9)",
                }
            },
            backgroundImage: {
                "mutation-gradient": "linear-gradient(135deg, #FF007A 0%, #7B00FF 100%)",
                "neon-mesh": "radial-gradient(circle at 50% 50%, rgba(255, 0, 122, 0.1) 0%, transparent 50%)",
            },
            animation: {
                "pulse-slow": "pulse 4s cubic-bezier(0.4, 0, 0.6, 1) infinite",
                "float": "float 6s ease-in-out infinite",
            },
            keyframes: {
                float: {
                    "0%, 100%": { transform: "translateY(0)" },
                    "50%": { transform: "translateY(-10px)" },
                }
            }
        },
    },
    plugins: [],
} satisfies Config;
