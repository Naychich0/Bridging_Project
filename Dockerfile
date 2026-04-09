FROM eclipse-temurin:25
LABEL authors="sunshine"
COPY ./target/bridgingproject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "bridgingproject.jar"]