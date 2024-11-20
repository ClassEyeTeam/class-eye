FROM openjdk:17-oracle
WORKDIR /app
COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]