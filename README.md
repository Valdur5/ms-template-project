TODO - define maven goals to be used by CI tools
TODO - Aspects
TODO - What is with that RAML shit?

# ms-template-project

This project is the base for the Archetype: TODO put in name

It shall not contain too fancy example code and design / programming patterns. The purpose of this project is provide a base for building a Microservice with a well selected set of technologies already configured and ready to run.

## Links

* http://localhost:8080/jsondoc-ui.html for exploring the REST API enter in the search field: http://localhost:8080/jsondoc

## Project Structure

The project structure is the following (find more information in the README files placed in the different folders and modules):

* data - Non build relevant files (like checkstyle configuration).
* ms-business - Business logic only
* ms-commons - Project independent classes (this should be in an extra project if you want to use it for different projects).
* ms-persistence - Handling all the database stuff and place to define the models which have to be persisted.
* ms-tests - Here are the system integration tests. All other tests should be in the corresponding module.
* ms-ws - Module where the REST controller are located as well as the Data Transfer Objects which are sent to the client. Here is also the main entry point to start up the Spring Boot application.

## Technologies

This prototype project is based on the following technologies:

### Persistence

* Spring Data JPA (Java Persistence API) - Spring Data JPA provides us access to the database without having to write all the code our self. It is based on hibernate and JDBC, it conforms to the official java standard JPA which means that we can switch in the future Spring Data JPA against some other implementation which is also JPA conform, for example a pure Hibernate solution.
* H2 Database - In memory database. This is an example, this can be replaced easily with any other database or database driver which supports JPA. Spring takes it automatically from the classpath since we added it to our pom file so that it is not necessary to declare it somewhere that we want to use H2.

### MVC / REST

* Spring Boot - Core of the project, responsible for keeping all the components together. Also for dependency injection, configuration and adding the embedded tomcat to run the project without an extra java application server.
* RAML - For defining the REST web service contract and for testing that we are stick to that contract.
* Dozer - For mapping our database entries to Data Transfer Objects (DTO) which should be used by the REST webservice to keep the API clean and independent from the database representation.
* Spring HATEOAS - TODO

### Testing

* Spring Test - For running Integration tests.
* jUnit - For writing unit tests.
* Cucumber - For writing system integration tests.

### Logging

* slf4j (Simple Logging Facade for Java) - Only an interface for multiple possible implementations of the logging.
* logback - Implementation of the slf4j facade. Standard for Spring Boot and a new reimplementation of the old log4j implementation.

### Events and Microservice Infrastructure.

* TODO Event system -> Micro services need an event system to tell others that we have something new and interesting.
* TODO Registration service to register our mircroservice to be found.

### Security

* TODO implement a very common and easy framework for security for the microservice as well.

### Documentation

* JSONdoc - For creating a interactive documentation for the REST Api which can be used by developer who are interested in consuming our service. The quality should be very high and all error codes should be exposed so that other developers have all information to completely use and understand our service.
* Javadoc - Used to document all classes, methods, interface ... HTML generation is not set up but the project is ready for it, if you decide that you want it.

### Quality

* Checkstyle - Plugin for the IDE, checks the code for styling issues so that all code looks uniform. The config can be found in the root of the project under data/qa.
* Findbugs - Plugin fot the IDE, checks the code for common failure patterns. The config can be found in the root of the project under data/qa.
* PMD - TODO ??? The config can be found in the root of the project under data/qa.
* Sonar - TODO ????