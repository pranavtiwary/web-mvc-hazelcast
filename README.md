# README #
Spring boot 1.5.8 and Jsoup

### What is this repository for? ###

* This is a webapp, which takes url as an input and parse the html at that url and display the metadata.

### How do I get set up? ###

* Must have jdk 8 installed
* Must have maven installed and configured.
* Dependencies : Maven will download dependencies
* Project setup In eclipse : run command mvn eclipse:eclipse from project directory. Open eclipse and import the maven project

### How do I compile and build? ###
* run command
mvn clean install

### How do I run? ###

* How to run  : From command line execute the command from project root directory "./execute"

* Once Server is up and running, open browser and open url : http://localhost:8080/

curl -v -X POST -H "Content-Type:application/json" -d '{"name":"ayush","department":"IT-singapore"}' http://localhost:8080/emp/add
