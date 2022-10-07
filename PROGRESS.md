# Just app

## Compile  

Result of command `./mvnw clean install compile package generate-sources` is `BUILD SUCCESS`。  

## Run  

Result of command `./mvnw exec:java` is `BUILD FAILURE` at all times。  

Result of command `./mvnw exec:java@someID` is `BUILD SUCCESS` under following circumstances {  
* Changes in file `pom.xml`; added section with id="someID"。  
* Class `com.onboarding.onboarding.Onboarding` is public。  
* Command `SpringApplication.run(Onboarding.class, args)` is not run。  

}。  

## Conclusion

Program runs successfully so task is considered successful; however problems might arise for following reasons {  
* Command `SpringApplication.run(Onboarding.class, args)` should not be run。  

}。  

# Installing postgreSQL

Software `postgreSQL` is installed。  
Command `psql --dbname="postgres"` runs successfully。  

## Conclusion

This task is so far fully successful。  

# Running postgreSQL

Basic sql command in code is executed successfully。

## Conclusion

This task is so far fully successful。  

# Build RestApi

Selecting `com.onboarding.onboarding.WebSite`'s methods that are annotated with `com.onboarding.onboarding.WebPage` and no other method is successful。  
Selecting `com.onboarding.onboarding.WebSite`'s method with specified annotation's `address` is successful。  

ToDo: This program is to listen to incoming requests and is to respond to those requests。  

## Conclusion

In progress...
