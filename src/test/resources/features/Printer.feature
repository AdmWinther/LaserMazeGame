Feature: Having a printer

  Scenario: printing an empty board
    Given I have a board that contains a board 7 by 7
    When level call printer draw and pass the level to it
    Then the Printer prints the board on the screen