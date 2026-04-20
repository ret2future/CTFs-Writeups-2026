FROM node:20-slim

RUN apt-get update && apt-get install -y \
    chromium \
    fonts-ipafont-gothic fonts-wqy-zenhei fonts-thai-tlwg fonts-kacst fonts-freefont-ttf libxss1 \
    --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

ENV PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true \
    PUPPETEER_EXECUTABLE_PATH=/usr/bin/chromium \
    FLAG=VBD{f4k3_fl4g_f0r_t3st1ng} \
    BASE_URL=http://localhost:3000 \
    JWT_SECRET=notes_ar3_s0_much_s3cure_vbd_ctf

WORKDIR /app

COPY ./src ./
RUN npm install

RUN npm run build

EXPOSE 3000

CMD ["npm", "start"]
