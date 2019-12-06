
How to run this web application?
docker-compose up

NOTE: Kindly stop mysql in case it is already running on your computer (i.e, make sure port 3306 is free). Also, make sure port 9200, 9300, 27017, 9000, 9001, 9002 are free since we will be running elasticsearch, mongodb and our microservices on these ports.

Package the following microservices in case their jar gets corrupted otherwise not required:
Commands -
CodeReviewerOrchestrationService: mvn clean compile package
RevieweeService: mvn clean compile package
RevieweeCodeService: mvn clean compile package

