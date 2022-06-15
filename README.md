# microservice-springboot-h2

We will build a **Spring Boot Rest CRUD API** for a **Todo Task Application** in that:

- Each **Todo Task** has *id, title, description, creation date, due date, status and comments*.
- APIs help to *Create, Read, Update, Delete* **Todo Tasks**.

Below mentioned are the ***REST APIs*** for CRUD Operations using **Spring Boot**.

| Description | CRUD Operation  | HTTP Method | REST API Endpoint |
|:-----------:|:--------------:|:-----------:|:-----------------:|
| Create New Todo Task | CREATE | POST | `/tasks` |
| Fetch All Todo Tasks | READ | GET | `/tasks` |
| Fetch One Todo Task | READ | GET | `/tasks/{id}` |
| Update One Specific Todo Task | UPDATE | PUT | `/tasks` |
| Delete One Specific Todo Task | DELETE | DELETE | `/tasks/{id}` |

**Spring Boot Framework** will serve as back-end server and I will be using ***Relational Database*** known as 
**H2 Database**, *it is also known as an Embedded Java Database*, for persisting(storing) the data.

## Requirements

- [JDK 1.8+](https://www.oracle.com/java/technologies/javase-downloads.html) - Java™ Platform, Standard Edition
  Development Kit
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new
  Spring Applications
- [Maven](https://maven.apache.org/) - Dependency Management (This comes in-built in Spring Boot package)
- [Tomcat](http://tomcat.apache.org/) - The Apache Tomcat® is a Java Servlet container used as web server for running
  the application (This comes in-built in Spring Boot package)
- [Intellij IDEA IDE](https://www.jetbrains.com/idea/download/#section=windows) - An IDE for developing the code. You
  can use any IDE, I have used Intellij IDEA IDE.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine.

### Using Main method

Clone the repository to your local drive.

```shell
git clone https://github.com/prasbhat/microservice-springboot-h2.git
```

Import the project as "Maven Project" into your favourite IDE and execute the `main` method in
the  `MicroserviceSpringbootH2Application` class from your IDE.

`Right Click on the file and Run as Java Application`

### Running the application with Maven

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like:

```shell
git clone https://github.com/prasbhat/microservice-springboot-h2.git
cd microservice-springboot-h2
mvn spring-boot:run
```

### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, double-click on the jar and run or by using the command:

```shell
git clone https://github.com/prasbhat/microservice-springboot-h2.git
cd microservice-springboot-h2
mvn package -DskipTests
java -jar target/microservice-springboot-h2-0.0.1-SNAPSHOT.jar
```

More detailed documentation regarding this project can be 
found [here](https://myzonesoft.com/post/microservice-springboot-h2/).