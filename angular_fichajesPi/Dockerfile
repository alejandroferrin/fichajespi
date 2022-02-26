#Primera Etapa
FROM node:16-alpine3.15 as build-step
ARG apiURL
ENV apiURL=$apiURL
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install --legacy-peer-deps
COPY . /app
RUN npm run build --prod

#Segunda Etapa
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/angular-fichajesPi /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/conf.d/default.conf
