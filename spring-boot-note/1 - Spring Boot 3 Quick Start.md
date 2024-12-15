# Spring Boot Revise

## 1 - Spring Boot 3 Quick Start

## Table of Contents
1. [Overview](#overview)
2. [Spring Boot Solution](#spring-boot-solution)
3. [Maven Solution](#maven-solution)
4. [Dependency(Project) Coordinates](#dependencyproject-coordinates)
5. [Spring Boot Starter Parent](#spring-boot-starter-parent)
6. [Java version](#java-version)
7. [Application Properties](#application-properties)
8. [Spring Boot Dev Tools](#spring-boot-dev-tools)
9. [Spring Boot Actuator](#spring-boot-actuator)
10. [Spring Security](#spring-security)
11. [application.properties configurations](#applicationproperties-configurations)

# Overview

```
Why Spring?

• Simplify Java Enterprise Development
```

```
Spring Boot 3 requires JDK 17 or higher
```

# Spring Boot Solution

```
1- Make it easier to get started with Spring development (starters)
2- auto-configuration
3- resolve dependency conflicts (Maven or Gradle)
4- Provide an embedded HTTP server
```

# Maven Solution

```
1- Maven will go out and download the JAR files for those projects for you
2- Maven will make those JAR files available during compile/run
3- Standard Directory Structure
```

# Dependency(Project) Coordinates

```xml
Project Coordinates:

<groupId>com.luv2code</groupId>
<artifactId>mycoolapp</artifactId>
<version>1.0.FINAL</version> 

Group ID: Name of company, group, or organization
Artifact ID: Name for project
Version: A specific release version like: 1.0, 1.6, 2.0 
         If project is under active development then: 1.0-SNAPSHOT
```

# Spring Boot Starter Parent

```xml
• This is a special starter that provides Maven defaults

• spring-boot-starters Inherit version from Starter Parent If you don't specify version.

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.6</version> <!-- Spring Boot Version -->
		<relativePath/> <!-- lookup parent from repository -->
</parent>
```

# Java version

```xml
Specify your Java version

<properties>
		<java.version>17</java.version>
</properties>
```

# Application Properties

```
server.port=8484
coach.name=Mickey Mouse ====> @Value("${coach.name}")
                              private String coachName;

```

# Spring Boot Dev Tools

```
• Automatically restarts your application when code is updated

• Simply add the dependency to your POM file
• No need to write additional code :-)
• For IntelliJ, need to set additional configurations
```

# Spring Boot Actuator

```
• monitoring

• Simply add the dependency to your POM file
• REST endpoints are automatically added to your application
• No need to write additional code! You get new REST endpoints for FREE!


# Use wildcard "*" to expose all endpoints (initially,we used health,info for learning)
# Can also expose individual endpoints with a comma-delimited list
# By default, only /health is exposed

management.endpoints.web.exposure.include=health,info
management.endpoints.web.exposure.include= *

management.endpoints.web.exposure.include= *


# /info gives information about your application
# Default is empty

# we need to make true to show info metrics!
management.info.env.enabled=true

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0
info.app.company=Unfaithful
info.app.company.t1=t1
info.app.phone.mobile=05383799917
info.app.phone.home=03123870218
info.address=SSK-APARTMENTS-95-13


management.endpoints.web.exposure.exclude=health,info

# management.endpoints.web.base-path=/actuator
# new link: http://localhost:8080/actuator/health

# management.endpoints.web.base-path=/actuator
# server.servlet.context-path=/mycoolapp
# new link: http://localhost:8080/mycoolapp/actuator/health

# server.servlet.context-path=/mycoolapp
# new link: http://localhost:8080/mycoolapp/actuator/health

# without any configuration for path
# link: http://localhost:8080/actuator/health
```

# Spring Security

```
• Credentials
Default user name: user
Check console logs for password


• Override Credentials
spring.security.user.name=scott
spring.security.user.password=tiger
```

# application.properties configurations

```
• Actuator Properties
management.endpoints.web.exposure.include=*
management.info.env.enabled=true

info.app.name=My Super Cool App
info.app.description=A crazy fun app, yoohoo!
info.app.version=1.0.0

management.endpoints.web.exposure.exclude=health,info



coach.name=Mickey Mouse
team.name=The Mouse Club


• Web Properties
server.port=7070
server.servlet.context-path=/mycoolapp
server.servlet.session.timeout=15m # HTTP session time out (Default: 30m)


• Core Properties
# Logging Levels
#
# TRACE
# DEBUG
# INFO
# WARN
# ERROR
# FATAL
# OFF

# Log levels severity mapping
logging.level.org.springframework=DEBUG #Default: INFO
logging.level.org.hibernate=TRACE
logging.level.com.luv2code=INFO

# Log file name

# save pom.xml directory (my-crazy-stuff.log)
logging.file.name=my-crazy-stuff.log
logging.file.path=E:/my-log

# save pom.xml directory (my-crazy-stuff.log)
# logging.file.name=my-crazy-stuff.log

# save E:\my-log directory (spring.log)
# logging.file.path=E:/my-log

# save E:\my-log directory (my-crazy-stuff.log)
# logging.file.name=E:/my-log/my-crazy-stuff.log

# save E:\my-log\my-crazy-stuff.log directory (spring.log)
# logging.file.path=E:/my-log/my-crazy-stuff.log


• Security Properties
spring.security.user.name=admin
spring.security.user.password=topsecret

• Data Properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=scott
spring.datasource.password=tiger

```