FROM openjdk:8-jdk-alpine
LABEL maintainer="hulekal.preeti@gmail.com"
VOLUME /tmp
EXPOSE 8080
COPY target/productcomparision*.jar productcomparision.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/relayrdb","-jar","/productcomparision.jar"]