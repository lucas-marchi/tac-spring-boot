FROM openjdk:17-jdk-alpine

COPY . /app
WORKDIR /app

RUN chmod +x mvnw

ENTRYPOINT ["java", "-jar", "app.jar"]