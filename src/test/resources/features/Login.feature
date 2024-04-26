Feature: User authentication

  Scenario: User logs in with correct credentials
    Given There is a user with username "user" and password "password" hashed
    Then The login should be successful with username "user" and password "password"

  Scenario: User logs in with empty credentials
    Given There is not a user with username "user" and password "password" hashed
    Then The login should fail with username "user" and password "password"

  Scenario: User logs with the wrong password
    Given There is a user with username "user" and password "password" hashed
    Then The login should fail with username "user" and password anything but "password"

  Scenario: User registers with correct credentials
    Given There is not a user with username "user" and password "password" hashed
    Then The registration should be successful with username "user" and password "password"

  Scenario: User registers with an existing username
    Given There is a user with username "user" and password "password" hashed
    Then The user should not be created with username "user" and any password