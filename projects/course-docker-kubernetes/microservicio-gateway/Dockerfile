FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
#
# Run stage
#
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar /usr/local/lib/app.jar
ENV EXPOSE 8090
USER 10014
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
