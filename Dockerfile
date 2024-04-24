FROM amazoncorretto:21-alpine3.18-jdk

COPY target/*.jar /employee.jar

CMD java -jar employee.jar

