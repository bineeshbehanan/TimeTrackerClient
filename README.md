Time Tracker Client Application
_______________________________

	This application works as a front-end for the time tracking mechanism for the users. This application has two major operations.

1) Adding a user details (email, start time, end time)
2) Search a user with his/her email

I) Creating a docker image of the application.
______________________________________________
	Creating the docker image is integrated as a POM plugin in this application. While running MAVEN INSTALL, the Dockerfile will be read and create the docker image and push into the running docker instance.

II) Configure and run the application.
______________________________________

	Using Command Line
	__________________
	
	i) pull the code from from github - https://github.com/bineeshbehanan/TimeTrackerClient
	ii) run the MAVEN command - mvn install (we can see the new docker image is created in the running docker container)
	iii) run the docker image using the command - docker run -it -p 8082:8082 <IMAGE_ID>
	iv) Type http://DOCKER_CONTAINER_URL:8082 to access the application
	
	OR
	
	Using Eclipse
	_____________
	
	i) pull the code from from github - https://github.com/bineeshbehanan/TimeTrackerClient
	ii) Import the code into eclipse as an 'Existing MAVEN project' option (under import option)
	iii) Right click on the project --> Run as --> Maven Install option (we can see the new docker image is created in the running docker container)
	iv) run the docker image using the command - docker run -it -p 8082:8082 <IMAGE_ID>
	v) Type http://DOCKER_CONTAINER_URL:8082 to access the application
	
	Please Note:
	____________
	
	Right now the application is configured with default docker instance URL (http://192.168.99.100:8080) for accessing the TimeTracker Image. You can modify this with other URL if required at the application.properties file.