Feature: This is to test login feature
  Scenario: User logs into home page
    Given Test starts
    Given User lands on login page
    When User enters "admin" and "admin"
    Then User should login to home page
    Then Test Record is saved with fileName "User_logs_into_home_page_test"