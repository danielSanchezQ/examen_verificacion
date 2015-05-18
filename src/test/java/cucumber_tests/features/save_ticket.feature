Feature: Save ticket
    To allow a user save a ticket for later retrieval

    Scenario: User save a ticket
        Given a ticket with name 'cucumber test', data 'cucumber data', password 'cucumber'
        When the user save
        And the user retrieves with name 'cucumber test', password 'cucumber'
        Then the user gets the data stored
