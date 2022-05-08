FROM maven:3.8.5-openjdk-18 as carexercise-build
WORKDIR .
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18
COPY --from=carexercise-build /target/carexercise-*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]