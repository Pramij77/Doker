FROM openjdk:8
LABEL maintainer="com.csi"
ADD target/employeeservice-0.0.1-SNAPSHOT.jar employeeservice.jar
ENTRYPOINT ["java", "-jar", "employeeservice.jar"]