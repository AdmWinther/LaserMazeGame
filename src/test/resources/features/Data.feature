Feature: Data Retrieval

  Scenario: Retrieve an empty level
    Given I have a level stored with a five-by-five grid, empty, with no token
    When I retrieve the level
    Then The grid should be of size 5 by 5
    And The grid should be empty
    And The list of placeable tokens should be empty

  Scenario: Retrieve a level with a LaserGun
    Given I have a level stored with a ten-by-ten grid, with a LEFT LaserGun at (two, three) and a UP Receiver at (five, six), with a RIGHT DoubleSidedMirror to place.
    When I retrieve the level
    Then The grid should be of size 10 by 10
    And The grid should be empty except for a LEFT LaserGun at (2, 3) and a UP Receiver at (5, 6)
    And The list of placeable tokens should contain a RIGHT DoubleSidedMirror.

  Scenario: Retrieve a level with a LaserGun and oneSidedMirror
    Given I have the level with ID "5x5_LG@(1,2)_Right_OSM@(3,2):UP"
    When I retrieve the level
    Then The grid should be of size 5 by 5
    And The grid should be empty except for a RIGHT LaserGun at (1, 2) and a UP OneSidedMirror at (3, 2)
    And The list of placeable tokens should be empty