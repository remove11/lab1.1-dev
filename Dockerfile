# Start with a base image containing Java runtime
#FROM openjdk:17-jdk-slim

# Make port 8080 available to the world outside this container
#EXPOSE 8080

# The application's jar file
#ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
#ADD ${JAR_FILE} app.jar

# Run the jar file
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
USER root
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
