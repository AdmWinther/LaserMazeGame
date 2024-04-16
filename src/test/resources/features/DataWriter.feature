Feature: Data Writing

  Scenario: Write an empty level of size 5x5
    Given an empty level of size 5x5
    When I write the level to a file
    Then I should retrieve the initial level using DataReader

  Scenario: Write a complex level
    Given a level of size 5x5 with a UP LaserGun in (0 0) and a DOWN Target in (4 4), with a DoubleSidedMirror and a SingleSidedMirror to place
    When I write the level to a file
    Then I should retrieve the initial level using DataReader