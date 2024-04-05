Feature: Checking a solution

  Scenario: Checking a correct solution
    Given I have a level that contains a board with a laser gun and a target
    When I check the solution
    Then The laser should propagate from the laser gun
    And The solution checker should return "true"

  Scenario: Checking an incorrect solution
    Given I have a level that contains a board with a laser gun and a target
    And there is a block in the way of the laser
    When I check the solution
    Then The laser should propagate from the laser gun
    And The solution checker should return "false"