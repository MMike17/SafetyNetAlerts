# Parking System
An API providing usefull informations to emergency services.
This app uses Java to run and stores data in a H2 SQL database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.6.2

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

### Running App

The H2 database will be setup by SpringBoot at application startup.

From the command line, you can position yourself in the directory that contains the pom.xml file and run the command below.

`mvn spring-boot:run`

You can also import the code into an IDE and run the app through it's GUI (if supported by the IDE).

### Testing

The app has unit tests and integration tests written.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`
