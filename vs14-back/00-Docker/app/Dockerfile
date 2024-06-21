FROM node:12-alpine
RUN apk add --no-cache g++ make
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/index.js"]