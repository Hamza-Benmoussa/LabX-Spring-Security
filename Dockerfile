FROM maven:3.8.2-jdk-8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar -Dspring.profiles.active=docker", "/app.jar"]