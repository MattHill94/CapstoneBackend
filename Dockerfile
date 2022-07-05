# we will use openjdk 8 with alpine as it is a very small linux distro
FROM openjdk:18-jdk-alpine
# copy the packaged jar file into our docker image
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar
COPY target/classes/data.xml /data.xml
# set the startup command to execute the jar
CMD ["java", "-jar", "/demo.jar"]