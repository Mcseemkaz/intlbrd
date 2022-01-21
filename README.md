# IntelliBoard Next Automation Testing 

This repository is used for storing of Functional automation test cases.

## Installation
- install Java 11 SDK and add OS Path env
- copy repository local

## Execute tests
 - in console execute command : `./mvnw`

## Maven Wrapper

There is a Maven Wrapper in the project. So there is no need to install Maven locally.
It will install itself when you run the ./mvnw command. All that is needed is a JDK.

## Testing configuration

In order to configure a test execution change options in the file : /src/test/resources/config.properties
* base_url - the URL for the app under testing (typically related to environments changing : dev, stage, prod)
* browser - specify a browser for executing (allowed the following: `chrome`, `firefox`)
* user_login - specify user credential email for login in the app
* user_pass - specify user credential password for login in the app

## Reports [TBD]

## Notes
* There is no need to add external Selenium webdriver - the project uses Selenide Wrapper for the webdrivers 
   and download and set up they automatically.