FROM python:3.10-slim
RUN apt-get update && apt-get install -y \
    curl \
    chromium \
    && curl -sL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs \
    && rm -rf /var/lib/apt/lists/*
ENV PUPPETEER_SKIP_DOWNLOAD=true
ENV PUPPETEER_EXECUTABLE_PATH=/usr/bin/chromium
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY package.json package-lock.json* ./
RUN npm install
COPY . .
RUN if [ -f package.json ]; then npm install; fi
ENV PORT="${PORT}"
ENV HOST="${HOST}"
ENV REDIS_HOST=redis
CMD ["python", "app.py"]
