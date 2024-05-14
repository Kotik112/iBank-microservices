FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal

MAINTAINER "Arman Iqbal"

WORKDIR /app

COPY target/loans-0.0.1-SNAPSHOT.jar .

RUN chmod 777 loans-0.0.1-SNAPSHOT.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]