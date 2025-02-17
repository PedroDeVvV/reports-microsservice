FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/report-service-0.0.2.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
