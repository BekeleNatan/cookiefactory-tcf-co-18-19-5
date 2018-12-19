Feature: Store menu management
  as a  store I want to manage my  menu

 // Scenario: Adding a recipe
   // Given A Franchise named "COD"
    //When the user adds a recipe named "Chocolala"
    //Then "Chocolala" recipe is "COD" franchise menu

  Scenario Outline: Add recipe to menu
    Given a recipe with the name of "<name>" was manufactured
    When the store add this recipe "<name>" in his menu
    Then the  store menu is actualize
    Examples:
          | name |
          | cookie melange |
          | cookie special |