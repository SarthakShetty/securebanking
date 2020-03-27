#!/bin/bash
# locate to the pom.xml location
cd /home/ubuntu/securebanking/BankingSystem
# do a maven clean build from the pom.xml
mvn clean install
# remove the previous deployed war if any
rm -rf bankingsystem.war
# remove the previous deployed war if any
rm -rf bankingsystem
# copy the files from the target webapps to the tomcat webapps
sudo scp /home/ubuntu/securebanking/BankingSystem/target/group12-banking-system-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/
# change the war name for the controllers to load the views
mv group12-banking-system-0.0.1-SNAPSHOT.war bankingsystem.war
# locate the bin folder of the system
cd /opt/tomcat/bin/
# do the start up function
sudo ./startup.sh

# run chmod 400 +x migrate.sh
# make the .sh file executable and then run the .sh file migrate.sh
