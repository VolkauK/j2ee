# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

RUN curl -L https://jdbc.postgresql.org/download/postgresql-42.7.3.jar -o postgresql-driver.jar

COPY pom.xml .
RUN mvn dependency:go-offline -q

COPY src ./src
RUN mvn package -DskipTests -q

# Stage 2: Run
FROM quay.io/wildfly/wildfly:latest-jdk21

COPY --from=build /app/target/demo-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/demo.war
COPY --from=build /app/postgresql-driver.jar /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080
EXPOSE 9990