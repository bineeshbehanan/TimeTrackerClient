# Start with a base image containing Java runtime
FROM java:8

# Add Maintainer Info
LABEL maintainer="mail2behanan@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8082 available to the world outside this container
EXPOSE 8082

# The application's jar file
ARG JAR_FILE=target/spring-boot-userdatamanagement.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-boot-userdatamanagement.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-userdatamanagement.jar"]