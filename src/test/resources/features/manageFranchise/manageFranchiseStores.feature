Feature: Managing the stores of the franchise
  a franchise responsible can add new stores, and delete them from the system

  Background:
    Given a franchise named "COD"

  Scenario: a responsible of the franchise wants to add a new store into the system
    When the manager adds a store named "COD Cannes" to the franchise "COD"
    Then The store exists in the system and we can get it by searching "COD Cannes"

  Scenario: a responsible of the franchise wants to delete a store
    Given the franchise store named "COD Marseille"
    When the manager deletes the store named "COD Marseille" from the system
    Then the store "COD Marseille" do not exist on the system