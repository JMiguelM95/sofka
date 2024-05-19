Feature: The user endpoint works as intended
  The user endpoint can handle various 
  Add, Get, Update, Delete

  @dev
  Scenario: Search a user by ID
    Given I search for a user with ID 1
    When I call the user API endpoint
    Then the response status code is 200
    And the response body contains the user data:
      | Field     | Value   |
      | first_name| George  |
      | last_name | Bluth   |
      | email     | george.bluth@reqres.in |

  @dev
  Scenario: Update user data and verify successful update
    Given I have a user to update with the following data:
      | Field     | Value   |
      | first_name| test1  |
      | last_name | test2   |
      | email     | test3@reqres.in |
    When I update the user data of ID 1
    And I call the user API endpoint
    Then the response status code is 200
    And the updatedAt has valid date

  @dev
  Scenario: Delete a user and verify deletion
    Given I have to delete user ID 1
    When I send a DELETE request to the user API endpoint for that ID
    Then the response status code is 204
    And the response body is empty