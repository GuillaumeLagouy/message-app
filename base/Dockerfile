

FROM maven:3.9.9-amazoncorretto-21-alpine AS build
ARG SERVICE_NAME
WORKDIR /app/${SERVICE_NAME}
COPY ./${SERVICE_NAME}/pom.xml /app/${SERVICE_NAME}
COPY ./${SERVICE_NAME}/src /app/${SERVICE_NAME}/src

# Installer les dépendances sans package
RUN mvn install -DskipTests

FROM openjdk:21-jdk-slim

ARG SERVICE_NAME

WORKDIR /app

# Installer Maven
RUN apt-get update && apt-get install -y maven

# Copier les fichiers générés par Maven (les dépendances)
COPY --from=build /app/${SERVICE_NAME}/target /app/${SERVICE_NAME}/target

# Copier le code source pour développement
COPY ./${SERVICE_NAME} /app/${SERVICE_NAME}

# Lancer l'application Spring Boot en mode développement
ARG SERVICE_NAME
ENV MVN_COMMAND="mvn -f /app/${SERVICE_NAME}/pom.xml spring-boot:run"
CMD ${MVN_COMMAND}
