# Stage 1: Build (тут мы добавим скачивание драйвера)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Скачиваем драйвер Postgres прямо через Maven, чтобы не искать его вручную
RUN mvn dependency:get -Dartifact=org.postgresql:postgresql:42.7.3 -Ddest=./postgresql-driver.jar -q

COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn package -DskipTests -q

# Stage 2: Run
FROM quay.io/wildfly/wildfly:latest-jdk21

# 1. Копируем твой скомпилированный проект
COPY --from=build /app/target/demo-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/demo.war

# 2. Копируем скачанный драйвер в ту же папку!
# WildFly увидит его и поймет, как работать с jdbc:postgresql://...
COPY --from=build /app/postgresql-driver.jar /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080
EXPOSE 9990