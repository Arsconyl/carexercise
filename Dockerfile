FROM openjdk:18
ARG JAR_FILE=target/carexercise-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]