#FROM adoptopenjdk:8-alphine
FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=*.jar
COPY giftcards.jar /giftcards.jar
ENTRYPOINT ["java", "-jar", "giftcards.jar"]