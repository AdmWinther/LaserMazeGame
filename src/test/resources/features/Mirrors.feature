Feature: Mirrors

  Scenario Outline: Mirror reflects laser
    Given I have a single sided mirror token with a "<mirrorOrientation>" orientation
    When the lasergun has a  "<laserOrientation>" orientation
    Then the Laser is propagated "<laserPropagation>"
    Examples:
       | laserOrientation        |mirrorOrientation | laserPropagation
       | UP                      |RIGHT             |RIGHT
       | UP                      |DOWN              |LEFT
       | RIGHT                   |DOWN              |DOWN
       | RIGHT                   |LEFT              |UP
       | LEFT                    |UP                |UP
       | LEFT                    |RIGHT             |DOWN
       | DOWN                    |UP                |RIGHT
       | DOWN                    |LEFT              |LEFT

  Scenario Outline: Mirror doesn't reflect laser
    Given I have a single sided mirror token with a "<mirrorOrientation>" orientation
    When the lasergun has a  "<laserOrientation>" orientation
    Then the laser is not reflected
    Examples:
      | laserOrientation  |mirrorOrientation
      | UP                |LEFT
      | UP                |UP
      | RIGHT             |UP
      | RIGHT             |RIGHT
      | LEFT              |DOWN
      | LEFT              |LEFT
      | DOWN              |DOWN
      | DOWN              |RIGHT



  Scenario Outline: Double mirror can always reflect the laser
    Given I have a double sided mirror token with a "<mirrorOrientation>" orientation
    When the lasergun has a  "<laserOrientation>" orientation
    Then the Laser is propagated "<laserPropagation>"
    Examples:
      | laserOrientation        |mirrorOrientation|laserPropagation
      | UP                      |RIGHT             |RIGHT
      | UP                      |LEFT              |RIGHT
      | UP                      |DOWN              |LEFT
      | UP                      |UP                |LEFT
      | RIGHT                   |DOWN              |DOWN
      | RIGHT                   |UP                |DOWN
      | RIGHT                   |LEFT              |UP
      | RIGHT                   |RIGHT             |UP
      | LEFT                    |UP                |UP
      | LEFT                    |DOWN              |UP
      | LEFT                    |RIGHT             |DOWN
      | LEFT                    |LEFT              |DOWN
      | DOWN                    |UP                |RIGHT
      | DOWN                    |DOWN              |RIGHT
      | DOWN                    |LEFT              |LEFT
      | DOWN                    |RIGHT             |LEFT

