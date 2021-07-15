FROM maven:3.6.3-openjdk-11 AS build
COPY src /pageRecognition/src
COPY pom.xml /pageRecognition
RUN mvn -f /pageRecognition/pom.xml clean package

FROM openjdk:11
COPY --from=build /pageRecognition/target/pageRecognition-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","pageRecognition-0.0.1-SNAPSHOT.jar"]