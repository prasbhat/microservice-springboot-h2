# microservice-springboot-h2
I have created a simple ***microservice application* using Java Spring Boot framework**, which will act as backend and expose the REST APIs, mentioned in the table below:
| CRUD Operation | HTTP Method | REST API Endpoint |
|----------------|-------------|-------------------|
| CREATE | POST	| `/create` |
| READ	| GET	| `/findAll` & `/find/{id}` |
| UPDATE | PUT	| `/update` |
| DELETE | DELETE | `/deleteById/{id}` |

I have used relational database called ***Embeddedd H2***, to store the data. 
Database related settings are placed in _/src/main/resources/application.properties_ file. When we need to change to another relational database, we just have to change the database settings in here.

More detailed documentation regarding this project can be found [here](https://myzonesoft.com/2020/10/28/java-full-stack-application-create-a-backend-application-using-spring-boot-with-hardcoded-data/).

## Requirements
- [JDK 1.8+](https://www.oracle.com/java/technologies/javase-downloads.html) - Java™ Platform, Standard Edition Development Kit
- [Springboot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
- [Maven](https://maven.apache.org/) - Dependency Management
- [Tomcat](http://tomcat.apache.org/) - The Apache Tomcat® is a Java Servlet container used as web server for running the application

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. 

### Using Main method
One way is to execute the `main` method in the `com.myzonesoft.microservice.todo.BackendSpringbootApplication` class from your IDE.
```shell
Right Click on the file and Run as Java Application
```

### Running the application with Maven
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
git clone https://github.com/prasbhat/full-stack-applications/springboot-microservice-hardcoded.git
cd springboot-microservice-hardcoded
mvn spring-boot:run
```

### Running the application with Executable JAR
The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command: 
```shell
git clone https://github.com/prasbhat/full-stack-applications/springboot-microservice-hardcoded.git
cd springboot-microservice-hardcoded
mvn package -DskipTests
java -jar target/springboot-microservice-hardcoded-0.0.1-SNAPSHOT.jar
```

URL to access the Microservice REST APIs: [http://localhost:8080/Todo/](http://localhost:8080/Todo/)