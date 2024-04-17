
Feature: Checkpoint
  Scenario: finish the level and hitting the checkpoint
    Given I have a level with a laser gun a target and a checkpoint
    And I hit the checkpoint before the target
    When I check the solution
    Then The laser should propagate from the laser gun
    And The solution checker should return "true"


  Scenario: finish the level without hitting the checkpoint
    Given I have a level with a laser gun a target and a checkpoint
    And I don't hit the checkpoint before hitting the target
    When I check the solution
    Then The laser should propagate from the laser gun
    And The solution checker should return "false"