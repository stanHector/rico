FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} giftcards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "target/giftcards-0.0.1-SNAPSHOT.jar"]
