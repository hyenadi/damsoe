FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=build/libs/damsoe-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080