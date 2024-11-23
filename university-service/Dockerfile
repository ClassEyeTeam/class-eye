FROM openjdk:17-oracle
WORKDIR /app
COPY target/university-service-0.0.1-SNAPSHOT.jar university-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "university-service.jar"]