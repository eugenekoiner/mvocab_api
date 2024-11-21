FROM amazoncorretto:17.0.10-alpine3.19
WORKDIR /app
COPY build/libs/mvocab_api-0.0.1-SNAPSHOT.jar mvocab-api-v1.jar
ENTRYPOINT ["java", "-jar", "mvocab-api-v1.jar"]
