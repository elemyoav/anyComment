FROM openjdk:17-jdk-slim

COPY target/anyComment-0.0.1-SNAPSHOT.jar anyComment-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "anyComment-0.0.1-SNAPSHOT.jar"]