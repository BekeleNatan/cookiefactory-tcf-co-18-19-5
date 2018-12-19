Feature: Working hours of a store
  Every store can define its opening and closing hours

  Some rules need to be respected :

  1 - We cannot add a opening fragment containing a faulty hours (opening hours after the closing hours for example, or wrong hours input)
  2 - We cannot add opening fragments that overlaps
  3 - We cannot delete an opening fragment while we have an order in the same day / hour

  Background:
    Given a store named "COD Antibes" of the franchise
    And the manager of the store "COD Antibes" adds a new opening hours to the store on "Friday" from "8":"00" to "12":"00"
    And no failure is expected

  Scenario: The manager checks if the store is open on a certain hours
    Then the store "COD Antibes" is open on "Friday" at "10":"10"
    And the store "COD Antibes" is not open on "Friday" at "19":"00"

  Scenario: The manager adds an opening hour respecting the opening/closing hours rules
    When the manager of the store "COD Antibes" adds a new opening hours to the store on "Monday" from "8":"00" to "12":"00"
    Then the store "COD Antibes" is open on "Monday" at "10":"10"
    And the store "COD Antibes" is not open on "Monday" at "19":"00"

  Scenario: The manager adds an opening hour that do not respect the rules 1 (opening hour after closing hour)
    * a failure is expected
    When the manager of the store "COD Antibes" adds a new opening hours to the store on "Monday" from "18":"00" to "12":"00"
    Then it fails

  Scenario: The manager adds an opening hour that do not respect the rules 2 (opening hours that overlaps)
    * a failure is expected
    When the manager of the store "COD Antibes" adds a new opening hours to the store on "Saturday" from "08":"00" to "12":"00"
    And the manager of the store "COD Antibes" adds a new opening hours to the store on "Saturday" from "11":"00" to "15":"00"
    Then it fails

  Scenario: The manager deletes an opening hour respecting the opening/closing hours rules
    When the manager of the store "COD Antibes" deletes an opening hours on "Monday" from "8":"00" to "12":"00"
    Then the store "COD Antibes" is not open on "Monday" at "10":"10"

  Scenario: The manager deletes an opening hour without respecting the rule 3 (existing order in the fragment)
    * a failure is expected
    When the manager of the store "COD Antibes" deletes an opening hours on "Friday" from "8":"00" to "12":"00"
    And we have an order to be collected on "Friday" from "11":"00"
    And it fails