Option 1: `Use java -jar`

```
./mvnw package
```

```
java -jar target\mycoolapp-0.0.1-SNAPSHOT.jar
```

Option 2: `Use Spring Boot Maven plugin`

```
./mvnw spring-boot:run
```

Note: We do not need packaging mycoolapp-0.0.1-SNAPSHOT.jar file in this case.
