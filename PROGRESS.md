# Just app

## Compile  

Result of `./mvnw clean install compile package generate-sources` is `BUILD SUCCESS`。  

## Run  

Result of `./mvnw exec:java` is `BUILD FAILURE` at all times。  

Result of `./mvnw exec:java@someID` is `BUILD SUCCESS` under following circumstances {  
* Changes in `pom.xml` in section with id="someID"。  
* Class `com.onboarding.onboarding.Onboarding` is public。  
* Command `SpringApplication.run(Onboarding.class, args)` is not run。  

}。  

## Conclusion

Program runs successfully so task is considered successful; however problems might arise for following reasons {  
* Command `SpringApplication.run(Onboarding.class, args)` should not be run。  

}。  
