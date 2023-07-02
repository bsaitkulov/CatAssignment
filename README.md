# CatAPI Assignment 

Welcome to CatAPI BDD project using Cucumber!

## Tech Stack: 

`Java 11` `Maven 3.9.1` `JUnit 5.9.2` `Cucumber-Java 7.11.2` `Rest Assured 5.3.0` `Logback 1.4.5` `Lombok 1.18.26`

## Installation

1. Clone the repository: https://gitlab.com/bsaitkulov/assignmentcatapi.git


    git clone

2. Navigate (using `cd` command) to the root directory of the project (basically - it's where the `pom.xml` file is located)

3. Install project dependencies:
  
    
    mvn install


## Running Tests

To run the tests and generate the Serenity report, use the following command:

    mvn clean verify

The tests will be executed in parallel and the Serenity HTML report will be generated in the `target/site/serenity` directory.

## Additional Commands
- To generate only the Serenity report, use: 

     
    mvn serenity:aggregate

- To run tests without generating Serenity report, use:

    
    mvn clean test

## Writing new Tests 
To write new tests for AQA Assignment V2 using Cucumber, follow these guidelines:

1. Navigate to `src/test/resources`
2. Create a new directory for features files.
3. Inside the `feature` directory create a new feature file.
4. Write Cucumber scenarios using Gherkin syntax.

Once you've written your scenarios, you can run them using `mvn test` command. Cucumber will generate corresponding step definitions.

To write the step definitions, follow these guidelines:

1. Navigate to `src/test/java`
2. Create a new package for step definitions.
3. Inside the package, create a new Java class for step definitions.

Once you've written your step definitions, Cucumber will match them with the corresponding steps in your feature file and execute them when running the tests.

## Viewing Logs
To view the logs, locate `MyLogs.log` file in the `logs` directory.

## CI/CD
The CI/CD pipeline is configured using GitLab and Maven Wrapper. The pipeline is consist of two stages: build and test.

## Prerequisites 
Before running the pipeline, ensure that the following dependencies are installed:
- JDK
- Git
- Maven Wrapper(included in the project)

## Instructions 
To run the CI/CD pipeline on your local machine, follow these steps:
1. Clone the repository: https://gitlab.com/bsaitkulov/assignmentcatapi.git


    git clone

2. Change to the project directory: 


    cd your-repo

3. Run the build stage using Maven Wrapper:


    ./mvnw install

This command will download the necessary version of Maven specified in the project's configuration and build the project.

4. Run the test stage using Maven Wrapper:


    ./mvnw clean verify

This command will execute the project's tests and generate test reports.

### CI/CD Pipeline

The CI/CD pipeline is automatically triggered on every commit to the repository. The pipeline consists of the following stages:

- Build: Downloads the required version of Maven and builds the project.
- Test: Executes the project's tests and generates test reports.

You can view the pipeline status and details in the GitLab CI/CD interface.

### Viewing Test Reports

After running the GitLab CI/CD pipeline, you can access the HTML test reports to review the test results. Follow these steps:

1. Go to the project's main page on GitLab.
2. Click on the "Build" tab in the navigation menu.
3. Look for the "Artifacts" section.
4. Find the `test` job you want to view the reports for.
5. Click on the "artifacts.zip" link to download the artifacts.
6. Extract the downloaded files to access the HTML reports.
7. Search for `index.html` to view the test results.

Please note that the exact steps and appearance may vary depending on your GitLab version and configuration.


