# Prepare

Installation of:  
* java  v`11.0.16` maybe?  
* spring-boot  v`2.7.4`  
* gradle v`4.4.1`
* [&#x5B;postgreSql&#x5D;](https://www.postgresql.org/download)

(optional)|=>
```
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
```

# Compile

```shell
./mvnw clean install compile package generate-sources
```

# Run

```shell
./mvnw exec:java@someID
```

