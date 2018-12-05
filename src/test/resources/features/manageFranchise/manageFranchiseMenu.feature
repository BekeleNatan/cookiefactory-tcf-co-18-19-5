Feature: Managing franchise menu

  Scenario: Adding a recipe
    Given A Franchise named "COD"
    When the user adds a recipe named "Chocolala"
    Then "Chocolala" recipe is "COD" franchise menu