FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY /target/employee-management-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]