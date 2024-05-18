FROM amazoncorretto:22-jdk

MAINTAINER "Arman Iqbal"

WORKDIR /app

COPY target/configserver-0.0.1-SNAPSHOT.jar .

RUN chmod 777 configserver-0.0.1-SNAPSHOT.jar

EXPOSE 8071

ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]