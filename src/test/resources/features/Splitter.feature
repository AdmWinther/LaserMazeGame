Feature: Splitter

Scenario Outline: Splitter in front of Laser
  Given I have a level that contains a laser gun at (2,2) with a "<laserOrientation>" orientation
  When a splitter token is placed at position <splitterX> and  <splitterY> with a <splitterOrientation> orientation
  Then the Laser is propagated <firstLaserPropagation> and <secondLaserPropagation>
  Examples:
    |laserOrientation  |splitterX|splitterY|splitterOrientation|firstLaserPropagation|secondLaserPropagation
    | UP               |2        |1        |RIGHT              |RIGHT                |UP
    | UP               |2        |1        |LEFT               |RIGHT                |UP
    | UP               |2        |1        |DOWN               |LEFT                 |UP
    | UP               |2        |1        |UP                 |LEFT                 |UP
    | RIGHT            |3        |2        |DOWN               |DOWN                 |RIGHT
    | RIGHT            |3        |2        |UP                 |DOWN                 |RIGHT
    | RIGHT            |3        |2        |LEFT               |UP                   |RIGHT
    | RIGHT            |3        |2        |RIGHT              |UP                   |RIGHT
    | LEFT             |1        |2        |UP                 |UP                   |LEFT
    | LEFT             |1        |2        |DOWN               |UP                   |LEFT
    | LEFT             |1        |2        |RIGHT              |DOWN                 |LEFT
    | LEFT             |1        |2        |LEFT               |DOWN                 |LEFT
    | DOWN             |2        |3        |UP                 |RIGHT                |DOWN
    | DOWN             |2        |3        |DOWN               |RIGHT                |DOWN
    | DOWN             |2        |3        |LEFT               |LEFT                 |DOWN
    | DOWN             |2        |3        |RIGHT              |LEFT                 |DOWN

