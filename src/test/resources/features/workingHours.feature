Feature: Working hours of a store
  Every store must define its working hours

  Scenario: Creating a working hours for a store, and adding Opening fragments
    Given "CoD" is a franchise
    And The franchise creates a store named "The Cookie Factory Antibes" and its taxe rate is : "1.2"
    When we add a new opening fragment to the store "cod st philippe" on "Monday" from "8":"00" to "12":"00"
    Then the store "cod st philippe" is open on "Monday" at "10":"10"
    And the store "cod st philippe" is not open on "Monday" at "19":"00"
    And the store "cod st philippe" is not open on "Friday" at "10":"10"


  Scenario: Creating a working hours for a store, and adding Opening fragments that are wrong, it must fail
    Given "CoD" is a franchise
    And The franchise creates a store named "The Cookie Factory Antibes" and its taxe rate is : "1.2"
    * a failure is expected
    When we add a new opening fragment to the store "cod st philippe" on "Monday" from "18":"00" to "12":"00"
    Then it fails


  Scenario: Creating a working hours for a store, and adding Opening fragments that overlaps, it must fail
    Given "CoD" is a franchise
    And The franchise creates a store named "The Cookie Factory Antibes" and its taxe rate is : "1.2"
    * a failure is expected
    When we add a new opening fragment to the store "cod st philippe" on "Monday" from "8":"00" to "12":"00"
    And we add a new opening fragment to the store "cod st philippe" on "Monday" from "11":"00" to "15":"00"
    Then it fails

