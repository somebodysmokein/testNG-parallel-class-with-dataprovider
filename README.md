![Logo](https://www.browserstack.com/images/static/header-logo.jpg)

# BrowserStack Examples TestNG

## Repository setup

- Clone the repository

- Ensure you have the following dependencies installed on the machine
    - Java >= 8
    - Maven >= 3.1+

  Maven:
    ```sh
     mvn clean test -P bstack-single
    ```

## Description


We have two tests (Test1,Test2). Test 1 and Test 2 are in two separate classes. By default these classes will run sequentially, but with TestNG parallel attribute set to classes, the classes will run in their own thread parallely. 

    ``` <suite name="Single" parallel="classes">
    ```  

Test1 has a DataProvider. By default, the DataProvider will use the same instance of the class to run each record from the data provider. And each record will run sequentially. To make the DataProvider methods to run in parallel, use the method attribute parallel to true.

    ```
        @DataProvider(parallel=true)
        public Object[][] dataMethod() {
            return new Object[][] { { "Browserstack" }, { "Google" } };
          }
    ```

