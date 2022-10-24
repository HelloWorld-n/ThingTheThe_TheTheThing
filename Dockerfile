FROM thing
WORKDIR /app


RUN apt-get update 
RUN apt-get install -y openjdk-11-jdk 
RUN apt-get clean

COPY . .
RUN chmod +x ./mvnw
RUN ls -a

RUN POSTGRES_HOST_AUTH_METHOD=trust #!important

##RUN ./mvnw exec:java@someID
