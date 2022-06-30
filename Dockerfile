FROM openjdk:12-alpine
COPY target/demo-*.jar /demo.jar
CMD ["java", "-jar", "/demo.jar"]