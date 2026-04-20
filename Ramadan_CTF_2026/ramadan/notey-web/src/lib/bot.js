import puppeteer from 'puppeteer';
import { signToken } from './auth';

export async function visitNote(noteId) {
    const flag = process.env.FLAG || 'VBD{f4k3_fl4g_f0r_t3st1ng}';
    const baseUrl = process.env.BASE_URL || 'http://localhost:3000';
    const url = `${baseUrl}/notes/${noteId}`;

    const adminToken = await signToken({ id: 'admin-uuid', username: 'admin' });

    const browser = await puppeteer.launch({
        headless: "new",
        args: ['--no-sandbox', '--disable-setuid-sandbox']
    });

    try {
        const page = await browser.newPage();

        await page.setCookie({
            name: 'flag',
            value: flag,
            domain: new URL(baseUrl).hostname,
            httpOnly: false,
            path: '/'
        });

        await page.setCookie({
            name: 'session',
            value: adminToken,
            domain: new URL(baseUrl).hostname,
            httpOnly: true,
            path: '/'
        });

        await page.goto(url, { waitUntil: 'load', timeout: 30000 });

        await new Promise(resolve => setTimeout(resolve, 3000));

        await browser.close();
        return true;
    } catch (error) {
        console.error('Bot error:', error);
        await browser.close();
        return false;
    }
}
