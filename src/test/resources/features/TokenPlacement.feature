Feature: Placing a token

  Scenario Outline: Successful placement
    Given I have a <width> by <height> level
    And I add a unplaced mirror
    And The level is built
    When I try to place the unplaced mirror at position (<tokenX>, <tokenY>)
    Then Cell (<tokenX>, <tokenY>) must be occupied by a token
    Examples:
      | width | height | tokenX | tokenY |
      | 5     | 5      | 1      | 1      |
      | 1     | 2      | 0      | 1      |
      | 10    | 10     | 5      | 5      |
      | 1     | 1      | 0      | 0      |
      | 2     | 2      | 1      | 1      |
      | 2     | 2      | 0      | 0      |
      | 2     | 2      | 0      | 1      |

  Scenario Outline: Unsuccessful placement due to occupied position
    Given I have a <width> by <height> level
    And I add a placed mirror at position (<tokenX>, <tokenY>)
    And I add a unplaced mirror
    And The level is built
    When I try to place the unplaced mirror at position (<tokenX>, <tokenY>)
    Then Cell (<tokenX>, <tokenY>) must be occupied by a token
    And The action should fail
    Examples:
      | width | height | tokenX | tokenY |
      | 5     | 5      | 1      | 1      |
      | 1     | 2      | 0      | 1      |
      | 10    | 10     | 5      | 5      |
      | 1     | 1      | 0      | 0      |
      | 2     | 2      | 1      | 1      |
      | 2     | 2      | 0      | 0      |
      | 2     | 2      | 0      | 1      |

  Scenario Outline: Unsuccessful placement outside the board
    Given I have a <width> by <height> level
    And I add a unplaced mirror
    And The level is built
    When I try to place the unplaced mirror at position (<tokenX>, <tokenY>)
    Then Cell (<tokenX>, <tokenY>) must be empty
    And The action should fail
    Examples:
      | width | height | tokenX | tokenY |
      | 5     | 5      | 5      | 5      |
      | 1     | 2      | 100    | 100    |
      | 10    | 10     | 10     | 9      |
      | 1     | 1      | 0      | 1      |
      | 2     | 2      | 1      | 2      |
      | 2     | 2      | 3      | 0      |
      | 2     | 2      | 1      | 2      |

  Scenario Outline: Trying to move an unmovable token
    Given I have a <width> by <height> level
    And I add a placed unmovable mirror at position (<placedTokenX>, <placedTokenY>)
    And The level is built
    When I try to move the token from cell (<placedTokenX>, <placedTokenY>) to (<destX>, <destY>)
    Then Cell (<placedTokenX>, <placedTokenX>) must be occupied by a token
    And Cell (<destX>, <destY>) must be empty
    And The action should fail
    Examples:
      | width | height | placedTokenX | placedTokenY | destX | destY |
      | 5     | 5      | 1            | 1            | 2     | 2     |
      | 1     | 2      | 0            | 1            | 0     | 0     |
      | 10    | 10     | 5            | 5            | 4     | 5     |
      | 2     | 2      | 1            | 1            | 0     | 1     |
      | 2     | 2      | 0            | 0            | 0     | 1     |
      | 2     | 2      | 0            | 1            | 0     | 0     |

  Scenario Outline: Trying to move a movable token
    Given I have a <width> by <height> level
    And I add a placed mirror at position (<placedTokenX>, <placedTokenY>)
    And The level is built
    When I try to move the token from cell (<placedTokenX>, <placedTokenY>) to (<destX>, <destY>)
    Then Cell (<destX>, <destY>) must be occupied by a token
    And Cell (<placedTokenX>, <placedTokenX>) must be empty
    Examples:
      | width | height | placedTokenX | placedTokenY | destX | destY |
      | 5     | 5      | 1            | 1            | 2     | 2     |
      | 1     | 2      | 0            | 1            | 0     | 0     |
      | 10    | 10     | 5            | 5            | 4     | 5     |
      | 2     | 2      | 1            | 1            | 0     | 1     |
      | 2     | 2      | 0            | 0            | 0     | 1     |
      | 2     | 2      | 0            | 1            | 0     | 0     |
