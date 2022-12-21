#FROM adoptopenjdk:8-alphine
FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=*.jar
COPY giftcards-0.0.1-SNAPSHOT.jar /giftcards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "giftcards-0.0.1-SNAPSHOT.jar"]