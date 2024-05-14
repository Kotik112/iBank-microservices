FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal

MAINTAINER "Arman Iqbal"

WORKDIR /app

COPY target/cards-0.0.1-SNAPSHOT.jar .

RUN chmod 777 cards-0.0.1-SNAPSHOT.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]