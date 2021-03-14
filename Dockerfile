FROM maven:3.5-jdk-8 AS build
COPY pom.xml /
RUN mvn -f pom.xml clean install

FROM openjdk:8-jdk-alpine
COPY --from=build /target/assignment-0.0.1-SNAPSHOT.jar assignment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","assignment-0.0.1-SNAPSHOT.jar"]

