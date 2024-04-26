Feature: Splitter

Scenario Outline: Splitter in front of Laser
  Given I have a splitter token with a "<splitterOrientation>" orientation
  When the lasergun has a  "<laserOrientation>" orientation
  Then the Laser is propagated "<firstLaserPropagation>" and "<secondLaserPropagation>"
  Examples:
    |laserOrientation         |splitterOrientation|firstLaserPropagation|secondLaserPropagation|
    | UP                      |RIGHT              |RIGHT                |UP                    |
    | UP                      |LEFT               |RIGHT                |UP                    |
    | UP                      |DOWN               |LEFT                 |UP                    |
    | UP                      |UP                 |LEFT                 |UP                    |
    | RIGHT                   |DOWN               |DOWN                 |RIGHT                 |
    | RIGHT                   |UP                 |DOWN                 |RIGHT                 |
    | RIGHT                   |LEFT               |UP                   |RIGHT                 |
    | RIGHT                   |RIGHT              |UP                   |RIGHT                 |
    | LEFT                    |UP                 |UP                   |LEFT                  |
    | LEFT                    |DOWN               |UP                   |LEFT                  |
    | LEFT                    |RIGHT              |DOWN                 |LEFT                  |
    | LEFT                    |LEFT               |DOWN                 |LEFT                  |
    | DOWN                    |UP                 |RIGHT                |DOWN                  |
    | DOWN                    |DOWN               |RIGHT                |DOWN                  |
    | DOWN                    |LEFT               |LEFT                 |DOWN                  |
    | DOWN                    |RIGHT              |LEFT                 |DOWN                  |

