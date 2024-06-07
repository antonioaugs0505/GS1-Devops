FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gs-1-0.0.1-SNAPSHOT.jar /app/app.jar

RUN groupadd -r appgroup && useradd -r -g appgroup appusser

ENV APP_ENV production

EXPOSE 8080

ARG API_VERSION=1.0

CMD ["java", "-jar", "app.jar"]