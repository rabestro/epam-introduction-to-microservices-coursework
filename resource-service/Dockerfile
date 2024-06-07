FROM openjdk:21-jdk-slim
EXPOSE 8080

ARG JAR_FILE=target/resource-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} resource-service.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/resource-service.jar"]
