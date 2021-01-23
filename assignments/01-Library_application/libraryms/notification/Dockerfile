FROM maven:3.5.2-jdk-8-slim as builder
ARG MVN_ARGS
ADD ./src/ /app/src/
ADD ./pom.xml /app/pom.xml
WORKDIR /app
RUN mvn --batch-mode package ${MVN_ARGS}

FROM openjdk:8
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8105
ENTRYPOINT ["java","-jar","app.jar"]