FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/testEDITIONs-0.0.1-SNAPSHOT.jar app.jar  
RUN ls -R /usr/app
EXPOSE 8089

CMD ["java", "-jar", "app.jar"]
