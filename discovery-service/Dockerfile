FROM eclipse-temurin:21-jdk
#FROM openjdk:21-oracle

VOLUME /tmp
COPY target/*.jar app.jar
# Lancer l'application
ENTRYPOINT ["java","-jar","app.jar"]


