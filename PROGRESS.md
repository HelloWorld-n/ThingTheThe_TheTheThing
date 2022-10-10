# Make `SpringApplication.run(Onboarding.class, args);` run successfully  

Command is split into `SpringApplication springApplication = new SpringApplication(Onboarding.class);`, `springApplication.run();`。  
Error is located at `springApplication.run();`。  
Error has been fixed。  
All known problems are gone。  

## Conclusion  

Success。  

# Make class as postgres sql table  

Each access to database via SpringApplication results in error code sent to client。  

## Conclusion

Failure。  
Temporary class `com.onboarding.onboarding.company.EmployeeUtil` is added until problem is fixed。  
