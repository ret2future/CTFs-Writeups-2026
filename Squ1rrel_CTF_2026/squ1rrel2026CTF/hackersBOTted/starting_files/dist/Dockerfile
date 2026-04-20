FROM node:22-slim

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm ci --ignore-scripts
COPY . .

ENV PORT=3000
EXPOSE 3000

CMD ["node", "index.js"]
