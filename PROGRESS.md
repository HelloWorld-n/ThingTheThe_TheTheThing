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
