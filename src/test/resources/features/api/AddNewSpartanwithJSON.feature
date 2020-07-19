@api
Feature: Add new Spartan

  @add_spartan_with_JSON
  Scenario: Add new Spartan
    Given authorization credentials are provided for "admin"
    And user accepts content type "application/json"
    Then user sends POST request to "/api/spartans" with following JSON Object
      | Furkan     |
      | Male       |
      | 1234567890 |
    Then user verifies that response status code is 201
    And user verifies that Spartan Born in Json Response

  @add_spartan_with_JSONFile
  Scenario: Add new Spartan
    Given authorization credentials are provided for "admin"
    And user accepts content type "application/json"
    Then user sends POST request to "/api/spartans" from JSON file
    Then user verifies that response status code is 201
    And user verifies that Spartan Born in Json Response