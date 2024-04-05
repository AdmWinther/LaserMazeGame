Feature: Having a printer

  Scenario: printing an empty board
    Given I have an empty board that contains a board 7 by 7
    When level call printer draw and pass the level to it
    Then the Printer prints the empty board

  Scenario: printing a board and a block token
    Given I have an empty board that contains a board 7 by 7
    And a block token is placed on the cell 3 and 3
    When level call printer draw and pass the level to it
    Then the Printer prints the board with a block token on the cell 3 and 3