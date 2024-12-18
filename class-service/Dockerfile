FROM openjdk:17-oracle
WORKDIR /app
COPY target/class-service-0.0.1-SNAPSHOT.jar class-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "class-service.jar"]