FROM gradle:8.14.3-jdk21 AS builder

WORKDIR /workspace

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /workspace/build/libs/*.jar /app/student-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/student-api.jar"]
