Feature: Checkpoint

  Scenario Outline: Shooting the laser at the checkpoint
    Given a level with a laser gun and a checkpoint and target on its "<relativePos>"
    And the laser gun is facing "<laserDirection>"
    And the checkpoint is facing "<checkpointDirection>"
    And the target is facing "<targetDirection>"
    When the player shoots the laser
    And the level is checked for completion
    Then the level checker returns "<result>"
    Examples:
      | relativePos | laserDirection | checkpointDirection | targetDirection | result |
      | LEFT        | LEFT           | RIGHT               | RIGHT           | true   |
      | LEFT        | LEFT           | LEFT                | RIGHT           | true   |
      | RIGHT       | RIGHT          | LEFT                | RIGHT           | false  |
      | LEFT        | LEFT           | DOWN                | RIGHT           | false  |
      | LEFT        | LEFT           | UP                  | RIGHT           | false  |
      | UP          | DOWN           | UP                  | RIGHT           | false  |
      | UP          | UP             | DOWN                | DOWN            | true   |
      | UP          | UP             | UP                  | DOWN            | true   |
