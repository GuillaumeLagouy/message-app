FROM maven:3.9.9-amazoncorretto-21-alpine AS build
ARG SERVICE_NAME
WORKDIR /app/${SERVICE_NAME}
COPY ./${SERVICE_NAME}/pom.xml /app/${SERVICE_NAME}
COPY ./${SERVICE_NAME}/src /app/${SERVICE_NAME}/src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
ARG SERVICE_NAME
WORKDIR /app
COPY --from=build /app/${SERVICE_NAME}/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
