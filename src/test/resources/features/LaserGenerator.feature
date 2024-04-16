Feature: Making Laser path

  Scenario: Checking generation of the first LaserFragment by LaserGun
    Given I have the level with ID "5x5_LaseGun@(1,4)_Right"
    When I run generateLaser
    Then 3 LaserFragment must be generated

  Scenario: Checking the reflection of laser by a oneSidedMirror
    Given To test LaserGenerator, I have the level with ID "5x5_LG@(1,2)_Right_OSM@(3,2):UP"
    When I run generateLaser
    Then 4 LaserFragment must be generated

  Scenario: Checking the reflection of laser by a 4 oneSidedMirror and ending at a block
    Given To test LaserGenerator, I have the level with ID "5x5_LG@(0,1)_Right_4xOSM_square_Block@(3.0)"
    When I run generateLaser
    Then 12 LaserFragment must be generated