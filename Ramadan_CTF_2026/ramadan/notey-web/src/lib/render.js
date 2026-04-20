import { render } from 'slimdown-js';
import DOMPurify from 'isomorphic-dompurify';

export function renderMarkdown(markdown) {
    const cleanMarkdown = DOMPurify.sanitize(markdown || '');
    return render(cleanMarkdown);
}
