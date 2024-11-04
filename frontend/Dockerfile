# Étape 1 : Construire l'application
FROM node:22-alpine AS build

WORKDIR /app
COPY package*json ./
RUN npm install
COPY . .
RUN npm run build

# Étape 2 : Servir l'application
FROM nginx:stable

COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
