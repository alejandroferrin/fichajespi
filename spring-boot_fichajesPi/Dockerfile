FROM maven:3.8.4-eclipse-temurin-11 AS build 
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:11-jdk-focal
COPY --from=build "home/app/target/fichajesPi.backend-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java","-jar","app.jar"]
