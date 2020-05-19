FROM openjdk:12-alpine

WORKDIR /
ADD challenge.jar chllange.jar
COPY input.json input.json
COPY config.json config.json
EXPOSE 8080
CMD ["java", "-jar", "challenge.jar"]
