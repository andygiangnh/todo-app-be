# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="gianghnguyen84@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9090

# The application's jar file
ARG JAR_FILE=target/todo-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} todo-service.jar

ENV DB_SERVER=todoinstance.cmckrbxesubq.ap-northeast-2.rds.amazonaws.com

# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.actives=prod", "-jar","/todo-service.jar"]

COPY ./scripts/start.sh /
USER root
RUN chmod +x /start.sh

# Run the jar file
ENTRYPOINT ["/start.sh"]