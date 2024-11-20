FROM openjdk:17-oracle
WORKDIR /app
COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]