#FROM adoptopenjdk:8-alphine
FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=*.jar
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
#COPY ${JAR_FILE} giftcards-0.0.1-SNAPSHOT.jar
#COPY . /app
#ENTRYPOINT ["java", "-jar", "./target/giftcards-0.0.1-SNAPSHOT.jar"]
COPY --from=maven-builder target/giftcards-0.0.1-SNAPSHOT.jar /app-service/giftcards-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","giftcards-0.0.1-SNAPSHOT.jar" ]