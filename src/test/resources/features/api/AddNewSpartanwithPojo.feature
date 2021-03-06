@api
Feature: Add new Spartan

  @add_spartan
  Scenario Outline: Add new Spartan
    Given authorization credentials are provided for "admin"
    And user accepts content type "application/json"
    When user create Spartan POJO Object with following information
      | name   | gender   | phone   |
      | <name> | <gender> | <phone> |
    Then user sends POST request to "/api/spartans"
    Then user verifies that response status code is 201
    And user verifies that Spartan Born
    Examples:
      | name   | gender | phone      |
      | Furkan | Male   | 1234567890 |
      | Bulent | Male   | 1234567890 |
      | Mehmet | Male   | 1234567890 |
      | Ayse   | Female | 1234567890 |

  @add_spartan_with_list
  Scenario: Add new Spartan
    Given authorization credentials are provided for "admin"
    And user accepts content type "application/json"
    When user create Spartan POJO Object with List information
      | Furkan     |
      | Male       |
      | 1234567890 |
    Then user sends POST request to "/api/spartans"
    Then user verifies that response status code is 201
    And user verifies that Spartan Born