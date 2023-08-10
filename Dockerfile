FROM openjdk:17
VOLUME /tmp
EXPOSE 8088
ARG JAR_FILE=build/libs/Spring-Demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} spring-DemoApp.jar
ENTRYPOINT ["java","-jar","/spring-DemoApp.jar"]