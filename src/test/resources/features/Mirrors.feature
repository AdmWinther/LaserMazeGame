Feature: Mirrors

  Scenario Outline: Mirror reflects laser
    Given I have a level that contains a laser gun at (2,2) with a "<laserOrientation>" orientation
    When a mirror token is placed  with a "<mirrorOrientation>" orientation at the <mirrorX> <mirrorY> position
    Then the Laser is propagated "<laserPropagation>"
    Examples:
       | laserOrientation |mirrorX |mirrorY | mirrorOrientation| laserPropagation
       | UP               | 2.0      |1       |RIGHT             |RIGHT
       | UP               | 2       |1       |DOWN              |LEFT
       | RIGHT            | 3       |2       |DOWN              |DOWN
       | RIGHT            | 3       |2       |LEFT              |UP
       | LEFT             | 1       |2       |UP                |UP
       | LEFT             | 1       |2       |RIGHT             |DOWN
       | DOWN             | 2       |3       |UP                |RIGHT
       | DOWN             | 2       |3       |LEFT              |LEFT

  Scenario Outline: Mirror doesn't reflect laser
    Given I have a level that contains a laser gun at (2,2) with a "<laserOrientation>" orientation
    When a mirror token is placed  with a "<mirrorOrientation>" orientation at the <mirrorX> <mirrorY> position
    Then the laser is not reflected
    Examples:
      | laserOrientation |mirrorX |mirrorY |mirrorOrientation
      | UP               |2       |1       |LEFT
      | UP               |2       |1       |UP
      | RIGHT            |3       |2       |UP
      | RIGHT            |3       |2       |RIGHT
      | LEFT             |1       |2       |DOWN
      | LEFT             |1       |2       |LEFT
      | DOWN             |2       |3       |DOWN
      | DOWN             |2       |3       |RIGHT



  Scenario Outline: Double mirror can always reflect the laser
    Given I have a level that contains a laser gun at (2,2) with a "<laserOrientation>" orientation
    When a double mirror token is placed  with a "<mirrorOrientation>" orientation at the <mirrorX> <mirrorY> position
    Then the Laser is propagated "<laserPropagation>"
    Examples:
      | laserOrientation |mirrorX |mirrorY | mirrorOrientation|laserPropagation
      | UP               |2       |1       |RIGHT             |RIGHT
      | UP               |2       |1       |LEFT              |RIGHT
      | UP               |2       |1       |DOWN              |LEFT
      | UP               |2       |1       |UP                |LEFT
      | RIGHT            |3       |2       |DOWN              |DOWN
      | RIGHT            |3       |2       |UP                |DOWN
      | RIGHT            |3       |2       |LEFT              |UP
      | RIGHT            |3       |2       |RIGHT             |UP
      | LEFT             |1       |2       |UP                |UP
      | LEFT             |1       |2       |DOWN              |UP
      | LEFT             |1       |2       |RIGHT             |DOWN
      | LEFT             |1       |2       |LEFT              |DOWN
      | DOWN             |2       |3       |UP                |RIGHT
      | DOWN             |2       |3       |DOWN              |RIGHT
      | DOWN             |2       |3       |LEFT              |LEFT
      | DOWN             |2       |3       |RIGHT             |LEFT

