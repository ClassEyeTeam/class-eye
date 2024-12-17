FROM openjdk:17-oracle
WORKDIR /app
COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "auth-service.jar"]