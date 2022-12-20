FROM adoptopenjdk:8-alphine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} giftcards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "giftcards-0.0.1-SNAPSHOT.jar"]
