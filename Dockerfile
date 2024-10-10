FROM openjdk:17-jdk-alpine
LABEL authors="Victus"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]