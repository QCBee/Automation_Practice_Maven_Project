# Automation_Practice_Maven_Project
**Goal:** This repo is used for e2e ui tests for jira project https://jira.hillel.it/secure/Dashboard.jspa

**Following flows are covered with tests:**
- login into the system:
   - with valid credintials
   - with invalid credentials
   - forgot login flow redirection
- create new issue:
   - successful flow with only required fields
   - successful flow with all fields
   - cancel flow without filling any fields
   - cancel flow after filling needed fields with confirmation allert
   - cancel flow after filling all needed fields with dismiss allert
- view ticket
- adding comment to ticket:
   - successful flow for adding new comment to selected ticket
- delete comment from ticket:
   - delete comment successful flow
   - cancel deleting flow
   
 **Project structure**
 - .circleci folder - is used for integration the project with Circle CI system;
 - src/main/java folder - is used for realization Page Object and all needed setting for WebDriver Selenium;
 - src/main/test folder - is used for realization all mentioned tests;
 - pom.xml file - is used for all needed dependencies for project (Maven, Selenium, Allure, TestNG);
 - testng.xml file - is used for creating and running regression suite with needed set of tests
 
 **Minimal pre-conditions before running the project**
 1. Java 
   - java version 10.0.2 or higher
   - jdk version 1.8.0 or higher
 2. Apache Maven 3.6.3
 
 **How to run project locally**
 1. Clone the repo to needed folder on local machine using following command in console:
 - cd absolute_path_to_needed_folder_on_local_machine (e.g. C:/Folder1/TestProject)
 - git clone https://github.com/QCBee/Automation_Practice_Maven_Project.git
 2. Run test using Maven using following command in console:
 - cd absolute_path_to_needed_folder_on_local_machine if it is not selected
 - mvn clean test
 If all tests were passed you will get successful build with following logs and information:
 - what browser was used;
 - which test was started;
 - which test was passed;
 
 [![Successful-build.png](https://i.postimg.cc/Wz9Qh7Pd/Successful-build.png)](https://postimg.cc/N2HNnmFt)
 
 If some tests were NOT passed you will get failed build with following logs and information:
 - what browser was used;
 - which test was started;
 - which test was failed;
 - added screenshot for failed test to folder /screenshot in project (if folder does NOT exist - it will be created and added):
 
 [![Failed-build.png](https://i.postimg.cc/Bv497bNQ/Failed-build.png)](https://postimg.cc/ZW7MCT11)
 
 **How to run project in IEDA**
 1. Clone the repo to needed folder on local machine using following command in console:
 - cd absolute_path_to_needed_folder_on_local_machine (e.g. C:/Folder1/TestProject)
 - git clone https://github.com/QCBee/Automation_Practice_Maven_Project.git
 2. Open project in IDEA as New Project
 3. Run testng.xml file
 
 [![Run-testng.png](https://i.postimg.cc/NFSLRm60/Run-testng.png)](https://postimg.cc/5Q5xVHkc)
 
 The build result will be following according to passed/failed tests
 
 [![Result-testng-run.png](https://i.postimg.cc/Jzc4z920/Result-testng-run.png)](https://postimg.cc/rDsLhYc2)
 
 **How to create own test suite for tests**
 
 1. Open testng.xml file
 2. Select needed tests for test suite
 
 [![Test-Suite-Generating.png](https://i.postimg.cc/G2j5Bj1k/Test-Suite-Generating.png)](https://postimg.cc/ygkPtcxd)
 
 **How to select browser for running the tests**
 
 1. Open testng.xml file
 2. Select browser for test run
 Following browsers are supported:
 - Firefox - please use "Firefox" or "firefox";
 - Chrome - please use "Chrome" or "chrome";
 - Edge - please use "Edge" or "edge";
 - IE - please use "Internet" or "interet"
 If some other browser was set up all test would be running using Chrome browser
 
 [![Browser-set-up.png](https://i.postimg.cc/mgJhP03G/Browser-set-up.png)](https://postimg.cc/4YvXM2F8)
 
