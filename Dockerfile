FROM openjdk:8-jre-alpine
COPY target/CodeReviewerOrchestrationService-1.0.jar /CodeReviewerOrchestrationService-1.0.jar
CMD ["/usr/bin/java", "-jar", "/CodeReviewerOrchestrationService-1.0.jar"]
