FROM openjdk:17-jdk-slim

WORKDIR /usr/app


COPY target/testEDITIONs-0.0.1-SNAPSHOT.jar /usr/app  
RUN ls -R /usr/app
EXPOSE 8089

CMD ["java", "-jar", "testEDITIONs-0.0.1-SNAPSHOT.jar"]
