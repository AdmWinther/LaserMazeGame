Feature: Checking a solution

  Scenario Outline: Checking a solution without mirrors
    Given I have a <width> by <height> level
    And the level contains a laser gun at <laser_x>, <laser_y> with orientation "<laserOrientation>"
    And the level contains a target at <target_x>, <target_y> with orientation "<targetOrientation>"
    When I start the level and check the solution
    And The solution checker should return "<result>"
    Examples:
      | width | height | laser_x | laser_y | laserOrientation | targetOrientation | target_x | target_y | result |
      | 3     | 3      | 1       | 0       | DOWN             | UP                | 1        | 2        | true   |
      | 10    | 10     | 0       | 9       | RIGHT            | LEFT              | 9        | 9        | true   |
      | 2     | 1      | 1       | 0       | LEFT             | RIGHT             | 0        | 0        | true   |
      | 3     | 3      | 1       | 0       | DOWN             | UP                | 2        | 2        | false  |
      | 10    | 10     | 0       | 9       | RIGHT            | RIGHT             | 9        | 9        | false  |
      | 3     | 3      | 0       | 0       | RIGHT            | LEFT              | 2        | 2        | false  |

  Scenario Outline: Checking a correct solution with mirrors
    Given I have a <width> by <height> level
    And the level contains a laser gun at <laser_x>, <laser_y> with orientation "<laserOrientation>"
    And the level contains a target at <target_x>, <target_y> with orientation "<targetOrientation>"
    And the level contains a mirror at <mirror_x>, <mirror_y> with orientation "<mirrorOrientation>"
    When I start the level and check the solution
    And The solution checker should return "<result>"
    Examples:
      | width | height | laser_x | laser_y | laserOrientation | targetOrientation | target_x | target_y | mirror_x | mirror_y | mirrorOrientation | result |
      | 3     | 3      | 1       | 1       | RIGHT            | DOWN              | 2        | 0        | 2        | 1        | LEFT              | true   |
      | 10    | 10     | 0       | 0       | RIGHT            | UP                | 9        | 9        | 9        | 0        | UP                | true   |
      | 3     | 3      | 0       | 0       | DOWN             | UP                | 0        | 2        | 1        | 1        | UP                | true   |
      | 3     | 1      | 0       | 0       | RIGHT            | LEFT              | 2        | 0        | 1        | 0        | UP                | false  |
      | 10    | 10     | 0       | 0       | RIGHT            | UP                | 9        | 9        | 0        | 9        | UP                | false  |
      | 3     | 3      | 1       | 1       | RIGHT            | UP                | 2        | 2        | 0        | 2        | UP                | false  |

  Scenario: Checking a solution with no target
    Given I have a 3 by 3 level
    And the level contains a laser gun at 1, 1 with orientation "UP"
    When I start the level and check the solution
    Then The solution checker should return "false"

  Scenario: Checking a solution with no laser gun
    Given I have a 3 by 3 level
    And the level contains a target at 1, 1 with orientation "UP"
    When I start the level and check the solution
    Then The solution checker should return "false"

  Scenario: Checking an empty level
    Given I have a 3 by 3 level
    When I start the level and check the solution
    Then The solution checker should return "false"