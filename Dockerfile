#FROM adoptopenjdk:8-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} giftcards-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "giftcards-0.0.1-SNAPSHOT.jar"]


FROM maven:3.8.4-openjdk-1.8 as maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:0-alpine

COPY --from=maven-builder app/target/giftcards-0.0.1-SNAPSHOT.jar /app-service/giftcards-0.0.1-SNAPSHOT.jar
WORKDIR /app-service

EXPOSE 8080
ENTRYPOINT ["java","-jar","giftcards-0.0.1-SNAPSHOT.jar"]