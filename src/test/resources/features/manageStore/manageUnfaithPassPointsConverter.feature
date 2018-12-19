Feature: UnfaithPassPointsConverter table
  Every store contains a conversion table between the points and their money value
  we have
          - "pointsToMoneyRate" that is used to pay with points
          - "moneyToPointsRate" that is used to give points to clients who paid

  by default they're at :
          - pointsToMoneyRate = 0.1;
          - moneyToPointsRate= 0.01;

  Background:
    Given a store "COD Antibes" of the franchise

  Scenario: The manager wants to change the table of conversion of : points to money
    When the manager of the store "COD Antibes" set the points to money rate at 2.0
    Then the points to money rate of the store "COD Antibes" is set to 2.0

  Scenario: The manager wants to change the table of conversion of : money to points
    When the manager of the store "COD Antibes" set the money to points rate at 2.0
    Then the money to points rate of the store "COD Antibes" is set to 2.0

