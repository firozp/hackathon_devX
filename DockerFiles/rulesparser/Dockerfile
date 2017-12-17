FROM openjdk:8-alpine

ADD RulesParser-0.0.1-SNAPSHOT.jar /RulesParser.jar

EXPOSE 8080

ENTRYPOINT [ "java","-Dspring.data.mongodb.uri=mongodb://mongo/rules","-jar", "/RulesParser.jar"]
