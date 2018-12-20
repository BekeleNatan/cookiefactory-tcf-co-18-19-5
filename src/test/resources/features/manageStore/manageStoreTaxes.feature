Feature: Setting the taxe rate for the store
  Every store contains a taxe rate which is applied on the final price exclusive of tax (accounting rule)

  by default the taxe rate of a store is set to 1

  Background:
    Given "COD Antibes" store of the franchise

  Scenario: The manager wants to see the default taxe rate of his store
    Then the taxe rate of the store "COD Antibes" is set to 1.0

  Scenario: The manager wants to set new taxe rate for the store
    When the manager of the store "COD Antibes" set the taxe rate to 2.0
    Then the taxe rate of the store "COD Antibes" is set to 2.0
