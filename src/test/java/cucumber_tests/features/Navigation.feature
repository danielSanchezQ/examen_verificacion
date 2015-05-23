Feature: Navigate over web page
  Let the user navigate properly on our website

  Scenario: User enter webpage
    Given any user navigating the webpage
    When the user try to save a ticket with name 'selenium test', data 'selenium data', password 'selenium'
    Then the user sees a confirmation webpage.