Feature: Making Laser path

  Scenario: Checking generation of the first LaserFragment by LaserGun
    Given To test LaserGenerator, I have the level with ID "laserGeneratorTest1"
    When I run generateLaser
    Then 3 LaserFragment must be generated

  Scenario: Checking the reflection of laser by a DoubleSidedMirror
    Given To test LaserGenerator, I have the level with ID "laserGeneratorTest2"
    When I run generateLaser
    Then 4 LaserFragment must be generated

  Scenario: Checking the reflection of laser by a Splitter and a DoubleSidedMirror
    Given To test LaserGenerator, I have the level with ID "laserGeneratorTest3"
    When I run generateLaser
    Then 18 LaserFragment must be generated

  Scenario: Checking a laser block immediately after the LaserGun
    Given To test LaserGenerator, I have the level with ID "laserGeneratorTest4"
    When I run generateLaser
    Then 1 LaserFragment must be generated