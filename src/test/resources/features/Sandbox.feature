Feature: Creating a level in sandbox mode

  Scenario: Placing a token on the grid
    Given I have an empty sandbox level with all the possible tokens in my inventory
    When I place a mirror token on position (0 0)
    Then the deck should be empty
    And my board should contain a mirror token at the (0 0) position

  Scenario: Placing a token in the deck
    Given I have an empty sandbox level with all the possible tokens in my inventory
    When I place a mirror token in the deck
    Then the deck should contain the mirror
    And the board should be empty

  Scenario: Move token from board to deck
    Given I have a sandbox level and a mirror token placed at (0 0)
    When I move the same mirror token in the deck
    Then the board should be empty
    And the deck should contain the mirror

  Scenario: Move token from deck to board
    Given I have a sandbox level and a mirror token in my deck
    When I move the mirror from the deck to the board at position (0 0)
    Then the deck should be empty
    And my board should contain a mirror token at the (0 0) position

  Scenario: Deleting token from deck
    Given I have a sandbox level and a mirror token in my deck
    When I delete the mirror
    Then the deck should be empty

  Scenario: Deleting token from the board
    Given I have a sandbox level and a mirror token placed at (0 0)
    When I delete the mirror at (0 0)
    Then the board should be empty

  Scenario: Not being able to place more than 1 laser gun
    Given a sandbox level with a laser gun place at (0 0) and an empty deck
    When I try to place another laser gun at (1 1)
    Then the board should still contain one laser gun
    And the deck should be empty

  Scenario: Move token from board to deck
    Given I have a sandbox level and a mirror token placed at (0 0)
    When I move the mirror token in the deck
    Then the board should be empty
    And the deck should contain the mirror

  Scenario: Removing token from the board
    Given I have a sandbox level and a mirror token placed at (0 0)
    When I remove the mirror at (0 0)
    Then the board should be empty

    Scenario: Reseting the level:
      Given I have a sandbox level and a mirror token placed at (0 0)
      When I reset the level
      Then the board should be empty
      And the deck should contain the mirror



