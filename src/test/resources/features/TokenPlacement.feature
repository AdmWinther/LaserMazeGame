Feature: Placing a token

  Scenario: Successful placement
    Given I have a level that contains an empty board
    When I try to place a movable token at position (2, 2)
    Then A token should be placed at position (2, 2)
    And The board should reflect the change

  Scenario: Unsuccessful placement due to occupied position
    Given I have a level that contains a board with an unmovable token placed at (2, 2)
    When I try to place a movable token at position (2, 2)
    Then the placement at (2, 2) should be rejected

  Scenario: Unsuccessful placement outside the board
    Given I have a level that contains an empty board
    When I try to place a movable token at position (100, 100)
    Then the placement at (100, 100) should be rejected

  Scenario: Token is not movable
    Given I have a level that contains a board with an unmovable token placed at (2, 2)
    When I select an unmovable token at position (2, 2)
    Then The token at position (2, 2) should still be there
