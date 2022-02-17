#!/bin/sh
PROFILE="${APPLICATION_CONTEXT:-prod}"
exec java "-Dspring.profiles.active=$PROFILE" -Djava.security.egd=file:/dev/./urandom -jar /todo-service.jar