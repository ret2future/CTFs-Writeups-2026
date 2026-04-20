# Dockerfile
FROM node:20-alpine
WORKDIR /app
COPY ./src .
RUN npm install
RUN npm run build
ENV FLAG=VBD{f4k3_fl4g_f0r_t3st1ng}
EXPOSE 3000
CMD ["npm", "run", "start"]
