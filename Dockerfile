FROM openjdk:11 as builder
WORKDIR application
COPY target/attraction-api-docker.jar attraction-api-docker.jar
RUN java -Djarmode=layertools -jar attraction-api-docker.jar extract

FROM openjdk:11
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]