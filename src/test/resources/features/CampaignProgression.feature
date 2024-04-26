Feature: Campaign progression

  Scenario Outline: We are able to save the campaign's progression of a user
    Given I have a username "<username>"
    And I have completed the <level> of the campaign and I save the campaign progression
    Then The campaign progression should be saved to the level <level>
    Examples:
      | username | level |
      | test1    | 1     |
      | test1    | 2     |
      | test1    | 7     |
      | test2    | 3     |
      | test2    | 4     |
      | test2    | 5     |
      | test3    | 6     |

  Scenario Outline: We are able to load the campaign's progression of a user
    Given I have a username "<username>"
    And My campaign progression is saved to the level <level>
    When I load my campaign progression
    Then I should be at the level <level> of the campaign
    Examples:
      | username | level |
      | test1    | 1     |
      | test1    | 9     |
      | test1    | 1     |
      | test2    | 4     |
      | test2    | 4     |
      | test2    | 3     |
      | test3    | 69    |

  Scenario Outline: We are able to reset the campaign's progression of a user
    Given I have a username "<username>"
    And My campaign progression is saved to the level <level>
    When We reset the campaign progression for the user "<username>"
    Then No progression should be saved for the user "<username>"
    Examples:
      | username | level |
      | test1    | 1     |
      | test1    | 9     |
      | test1    | 1     |
      | test2    | 4     |
      | test2    | 4     |
      | test2    | 3     |
      | test3    | 4     |
      | test     | 69    |

  Scenario Outline: We are able to reset the campaign's progression of all users
    Given I have a username "<username>"
    And My campaign progression is saved to the level <level>
    And I have a username "<username2>"
    And My campaign progression is saved to the level <level2>
    When We reset the campaign progression for all users
    Then No progression should be saved for the user "<username>"
    And No progression should be saved for the user "<username2>"
    Examples:
      | username | level | username2 | level2 |
      | test1    | 1     | test2     | 4      |
      | test1    | 9     | test2     | 4      |
      | test1    | 1     | test2     | 3      |
      | test2    | 4     | test3     | 4      |
      | test2    | 4     | test3     | 4      |
      | test2    | 3     | test3     | 4      |
      | test3    | 4     | test      | 69     |
      | test     | 69    | test      | 2      |



