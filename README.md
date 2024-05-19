# API Testing Example with SerenityRest BDD (and Cucumber)
This repo demonstrates how to do API Test Automation using SerenityRest (RestAssured under the hood) with Cucumber BDD format.

<!--ts-->
* [API Testing Example with SerenityRest BDD (and Cucumber)](#api-testing-example-with-serenityrest-bdd-and-cucumber)
   * [Application under Test](#application-under-test)
   * [Pre conditions](#Pre-conditions)
   * [Exactly what APIs are we testing?](#exactly-what-apis-are-we-testing)
      * [Get User](#Get-User)
      * [Update User](#Update-user)
      * [Delete User](#Delete-user)
   * [Repository Structure](#repository-structure)
   * [Run the tests](#run-the-tests)
   * [Run reports](#run-reports)

<!--te-->

## Application under Test

We will be testing the [ReqRes API](https://reqres.in/api-docs/#/)

Specifically, the [API](https://reqres.in/api/users/)

## Pre conditions

You will need to have MAVEN_HOME in the environment variables [Guide](https://phoenixnap.com/kb/install-maven-windows)

## Exactly what APIs are we testing?

In case Thinking App goes down or the API is updated, here is the gist of the current APIs tested at the time of writing.

We will specifically cover the workflow of get a user, updating a user, and deleting a user.

### Get User

```
GET Request ->
https://reqres.in/api/users/{id}

Response Status ->
200

Response Body ->
{
    "data": {
        "id": 1,
        "email": "george.bluth@reqres.in",
        "first_name": "George",
        "last_name": "Bluth",
        "avatar": "https://reqres.in/img/faces/1-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
```

### Update User

```
PUT Request ->
https://reqres.in/api/users/{id}

Request Body ->
{
    "email": "test2@fake.com",
    "first_name": "Miguel"
}

Response Status ->
200

Response Body ->
{
    "email": "test2@fake.com",
    "first_name": "Miguel",
    "updatedAt": "2024-05-19T16:42:23.281Z"
}
```

### Delete User

```
DELETE Request ->
https://reqres.in/api/users/{id}

Response Status ->
200
```

## Repository Structure
```.
src/test/resources/features
└── User.feature #the test scenario
```

```
src/test/java/starter
├── apis #resquest specifications for various APIs
│   └── UserAPI.java
├── CucumberTestSuite.java #useful if running tests from an IDE
└── steps #step definitions for our feature files
    └── UserSteps.java
```

## Run the tests

```
mvn clean verify
```

## Run reports

```
mvn serenity:aggregate
```

Author: Miguel Miranda
