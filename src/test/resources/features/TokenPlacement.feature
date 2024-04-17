Feature: Placing a token

  Scenario: Successful placement
    Given I have a level that contains an empty board
    When I try to place a movable token at position (2, 2)
    Then A token should be placed at position (2, 2)
    And The board should reflect the change

  Scenario: Unsuccessful placement due to occupied position
    Given I have a level that contains a board with an unmovable token placed at (2, 2)
    When I try to place a movable token at position (2, 2)
    Then the cell (2, 2) should be occupied by the unmovable token

  Scenario: Unsuccessful placement outside the board
    Given I have a level that contains an empty board
    When I try to place a movable token at position (100, 100)
    Then the placement at (100, 100) should be rejected

  Scenario: Trying to move an unmovable token
    Given I have a level that contains a board with an unmovable token placed at (2, 2)
    When I try to move the token from cell (2, 2) to (3, 3)
    Then Cell (2, 2) must be occupied by the unmovable token
    And Cell (3, 3) must be empty

  Scenario: Trying to move a movable token
    Given I have a level that contains a board with a movable token placed at (2, 2)
    When I try to move the token from cell (2, 2) to (3, 3)
    Then Cell (3, 3) must be occupied by the movable token
    And Cell (2, 2) must be empty
