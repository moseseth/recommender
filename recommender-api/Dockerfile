#stage 1
FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR usr/src/app
COPY . ./
RUN mvn clean package

#stage 2
FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]