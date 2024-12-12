FROM openjdk:17-oracle
WORKDIR /app
COPY target/admin-server-0.0.1-SNAPSHOT.jar admin-server.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "admin-server.jar"]