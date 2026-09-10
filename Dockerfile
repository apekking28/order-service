# Stage 1: Build aplikasi menggunakan Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Salin pom.xml dan folder src saja
COPY pom.xml .
COPY src ./src

# Jalankan build menggunakan maven standard
RUN mvn clean package -DskipTests

# Stage 2: Fast-jar Runner Quarkus
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]