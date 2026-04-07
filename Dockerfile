FROM eclipse-temurin:25
LABEL authors="sunshine"
COPY ./target/classes/com /tmp/com
WORKDIR /tmp
ENTRYPOINT ["java", "com.bridgingproject.Main"]