FROM openjdk:17-jdk-slim
WORKDIR /app
COPY gradlew gradlew.bat build.gradle ./
COPY gradle gradle
RUN ./gradlew clean
RUN ./gradlew dependencies
COPY src src
RUN ./gradlew bootJar
COPY build build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/ms-user-0.0.1-SNAPSHOT.jar"]
