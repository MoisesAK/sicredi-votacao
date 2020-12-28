FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1-alpine
ADD build/libs/voting*.jar /opt/api.jar
ENTRYPOINT exec java -jar /opt/api.jar
