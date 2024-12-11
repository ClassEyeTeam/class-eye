FROM openjdk:17-oracle
WORKDIR /app
COPY target/student-service-0.0.1-SNAPSHOT.jar student-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "student-service.jar"]