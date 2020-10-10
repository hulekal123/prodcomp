FROM openjdk:8-jdk-alpine
LABEL maintainer="hulekal.preeti@gmail.com"
VOLUME /tmp
EXPOSE 8080
COPY target/productcomparision*.jar productcomparision.jar
COPY src/main/resources/application.properties .
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/productcomparision.jar"]