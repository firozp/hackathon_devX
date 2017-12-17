FROM openjdk:8-alpine

ADD RulesService.jar /RulesService.jar

EXPOSE 8080

ENTRYPOINT [ "java","-Dspring.data.mongodb.uri=mongodb://mongo/rules","-jar", "/RulesService.jar"]