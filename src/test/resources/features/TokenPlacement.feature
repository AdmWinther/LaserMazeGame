Feature: Placing a token

  Scenario: Successful placement
    Given I have a level that contains an empty board
    When I try to place a movable token at position (2, 2)
    Then A token should be placed at position (2, 2)

  Scenario: Unsuccessful placement due to occupied position
    Given I have a level that contains an empty board
    And An unmovable token is placed at (2, 2)
    When I try to place a movable token at position (2, 2)
    Then the placement at (2, 2) should be rejected

  Scenario: Unsuccessful placement outside the board
    Given I have a level that contains an empty board
    When I try to place a movable token at position (100, 100)
    Then the placement at (100, 100) should be rejected

  Scenario: Trying to move an unmovable token
    Given I have a level that contains an empty board
    And An unmovable token is placed at (2, 2)
    When I try to move the token from cell (2, 2) to (3, 3)
    Then The index of the token at position (2, 2) should be 0
    And Cell (3, 3) must be empty
    And The action must be declined

  Scenario: Trying to move a movable token
    Given I have a level that contains an empty board
    And A movable token is placed at (2, 2)
    When I try to move the token from cell (2, 2) to (3, 3)
    Then The index of the token at position (3, 3) should be 0
    And Cell (2, 2) must be empty
    And The action must be accepted
