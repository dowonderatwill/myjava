20220914
To compile:
mvn clean compile assembly:single

To run:
change the config.properties for connection parameters and DB Schema.
in the same folder save both of these two files and give below command:

java -jar DBChosakun-0.1-jar-with-dependencies.jar

it will generate one html file in the same folder.

20221018: While executing ensure the properties file is present as path-> ./config/config.properties
 
