#
# Maven Build (Java 21)
#
FROM maven:3.9.7-eclipse-temurin-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Jar Package (Java 21 Runtime)
#
FROM eclipse-temurin:21-jre-focal
# StudentListSecureDB-0.0.1-SNAPSHOT.jar  = <artifactId>-<version>.jar (pom.xml)
COPY --from=build /home/app/target/StudentListSecureDB-0.0.1-SNAPSHOT.jar /usr/local/lib/studentlistsecuredb.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/studentlistsecuredb.jar"]
