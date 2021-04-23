# Alpine Linux with OpenJDK JRE
FROM openjdk:12-alpine
# copy jar into image
ADD ./target/*.jar /service.jar
# run application with this command line
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/service.jar"]