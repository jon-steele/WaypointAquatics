FROM ubuntu:20.04

WORKDIR /app
COPY ./target/waypoint-0.0.2.jar /app

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update
RUN apt install -y openjdk-17-jre

EXPOSE 80

CMD ["java", "-jar", "waypoint-0.0.2.jar"]