FROM openjdk:8-jre-alpine
COPY target/CodeReviewerOrchestrationService-0.0.1-SNAPSHOT.jar /CodeReviewerOrchestrationService-0.0.1-SNAPSHOT.jar
CMD ["/usr/bin/java", "-jar", "/CodeReviewerOrchestrationService-1.0.jar"]
