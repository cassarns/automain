# AutoMain
Automobile Maintenance Library

# Prerequisites

## Apache Maven
Maven is used as a build tool to install all the required dependencies. It can be downloaded from the following link:
https://maven.apache.org/download.cgi

Once downloaded, make sure that the CLI is installed by adding Maven to your PATH.
Instructions on how to do so can be found from the following link:
https://maven.apache.org/install.html

## PostgreSQL Database
PostgreSQL is required to persist data for AutoMain. Download it from the following link:
https://www.postgresql.org/download/

Once installed, a development database needs to be created and initialized in order to begin saving data.
Scripts have been included for ease of use, however at the moment they are limited to Linux/OS X.
If psql has been added to your PATH, then you can use the commands from the bash file found in src/main/scripts to build the database and it's tables.

## Apache Tomcat
Tomcat 9.x is recommended for a WAR deployment platform, and can be found at the following link:
https://tomcat.apache.org/download-90.cgi


# Tests
Once all the prerequisites have been downloaded and installed, tests can be run by calling 'mvn test' from the command-line within the projects directory.
A number of unit tests have been provided to test database connectivity and other functionality.

# Packaging and Deployment
To package the WAR and deploy it, run the command 'mvn clean package' to build a WAR in the target folder within the project.
Once built, this WAR can be placed within the Tomcat directory (where exactly is platform dependent).
The Tomcat deployement server should be started before proceeding.
Once placed in the directory, you can access it by going to the following URL:
http://localhost:8080/jaxrs/Car

To verify if it works, you should see a JSON representation of the cars currently in the PostgreSQL database.

# Known Issues
Currently there are limitations present concerning the connection pools in the app. If data is refreshed too many times (either from the front-end AutoMain-Web, or the back-end), Tomcat must be restarted to flush the existing connections and allow new connections to connect.
This project uses C3P0 to handle connection pools, however a bug is preventing this from working properly.
