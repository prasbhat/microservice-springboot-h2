# microservice-springboot-h2

This is a simple **To-do Tracker Application** which includes columns like _Title, Description, Due Date, Status_.

I have created this application through a ***microservice
application* using Java Spring Boot Framework**, which will act as backend and expose all ***CRUD Operations*** through _REST APIs_,
mentioned in the table below:
| CRUD Operation | HTTP Method | REST API Endpoint |
|----------------|-------------|-------------------|
| CREATE | POST | `/create` |
| READ | GET | `/findAll` & `/find/{id}` |
| UPDATE | PUT | `/update` |
| DELETE | DELETE | `/deleteById/{id}` |

I will be using the relational database known as ***H2 Database***, to persist the data. It is called the Java SQL
database.

The main features of H2 Database are:

- Very fast, open source, JDBC API
- Embedded and server modes; in-memory databases
- Browser based Console application
- Small footprint: around 2 MB jar file size

I will be using the _Embedded version of the H2 Database_. We will use Hibernate to interact with the database.

Database related settings are placed in _/src/main/resources/application.properties_ file. When we need to change to
another relational database, we just have to change the database settings in this file.

## Requirements

- [JDK 1.16+](https://www.oracle.com/java/technologies/javase-downloads.html) - Java™ Platform, Standard Edition
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
the  `com.myzonesoft.microservice.todo.MicroserviceSpringbootH2Application` class from your IDE.

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
found [here](https://myzonesoft.com/2021/02/21/create-a-microservice-application-using-spring-boot-and-h2-database/).