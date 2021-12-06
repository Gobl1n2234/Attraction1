FROM openjdk:11
ADD target/attraction-app.jar attraction-app.jar
ENTRYPOINT ["java", "-jar","attraction-app.jar"]
EXPOSE 8080