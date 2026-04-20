import './globals.css';

export const metadata = {
    title: 'Notey - Your Simple Notes',
    description: 'A simple notes app inspired by Notion',
};

export default function RootLayout({ children }) {
    return (
        <html lang="en">
            <body>
                {children}
            </body>
        </html>
    );
}
