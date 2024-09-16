FROM openjdk:21


WORKDIR /app

COPY target/projetAdmin-0.0.1-SNAPSHOT.jar ./app.jar


EXPOSE 8080


CMD ["java", "-jar", "app.jar"]


