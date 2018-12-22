Feature: Collect Statistics for stores or for the franchise.
  The store can look for statistics like number of cookies sold by day.
  Or number of cookies that are personalized cookies.

  Background:
    Given A store with orders on his order register

  Scenario: Show statistics for number of personalised cookies
    Given I have a order register with 5 personalised cookies
    When I choose the stat type as number of personalised cookies
    Then The sum 5 of personalized cookies must be returned

Scenario: Show statistics for number of cookies sold by day
  Given I have a order register with 8 cookies
  When I choose the stat type as cookies sold by day
  Then The sum 8 of  cookies must be returned

  Scenario: Show Statistics for franchise of number of personalized cookies for every store
    Given I have two stores
    And Store one has sold 10 personalized cookies
    And Store two has sold 5 personalized cookies
    And I want to see the number of cookies sold for every store
    Then the sum 15 cookies should be returned