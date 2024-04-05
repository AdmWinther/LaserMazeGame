Feature: Placing a token

  Scenario: Successful placement
    Given I have a level that contains an empty board
    When I try to place a token at position (2, 2)
    Then A token should be placed at position (2, 2)
    And The board should reflect the change


#  Scenario: Token is not movable
#    Given I have a level that contains a board and tokens
#    When I select a token
#    And the token is not movable
#    Then I should not be able to select the token
#
#  Scenario: Position is not on the board
#    Given I have a level that contains a board and tokens
#    When I select a token
#    And the token is movable
#    And I select a position
#    And the position is not on the board
#    Then I should not be able to place the token at the selected position
#
#  Scenario: Position is occupied
#    Given I have a level that contains a board and tokens
#    And there is a token placed at a position (2, 3)
#    When I select a token
#    And the token is movable
#    And I select a position
#    And the position is on the board
#    And the position is occupied
#    Then I should not be able to place the token at the selected position
