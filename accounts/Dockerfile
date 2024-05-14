FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal

MAINTAINER "Arman Iqbal"

WORKDIR /app

COPY target/accounts-0.0.1-SNAPSHOT.jar .

RUN chmod 777 accounts-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

