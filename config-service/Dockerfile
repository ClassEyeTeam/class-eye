FROM openjdk:17-oracle
WORKDIR /app
COPY target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-service.jar"]