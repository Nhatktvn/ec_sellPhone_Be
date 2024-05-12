# Package stage
#
FROM openjdk:17-jdk-slim
COPY /target/mock-project-0.0.1-SNAPSHOT.jar mock-project.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","mock-project.jar"]