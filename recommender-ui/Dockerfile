FROM node:alpine AS build-stage

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package*.json /app/package*.json

COPY . /app

RUN npm install -g @angular/cli@latest
RUN npm install
RUN npm run build -- --output-path=./dist

FROM nginx:alpine
COPY --from=build-stage /app/dist/ /usr/share/nginx/html
COPY --from=build-stage /app/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
