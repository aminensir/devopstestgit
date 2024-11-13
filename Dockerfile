FROM openjdk:17-jdk-slim

WORKDIR /app
EXPOSE 8089
ADD target/testEDITIONs-0.0.1-SNAPSHOT.jar testEDITIONs-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar", "testEDITIONs-0.0.1-SNAPSHOT.jar"]
