@tag
Feature: Error Validation

  @ErrorValidations
  Scenario Outline: Testing Error Validation
    Given I landed on Ecommerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | username            |password   |
      |Dupa321@gmail.com    |Dupa12346  |
      |rahulshetty@gmail.com|Iamking@0  |