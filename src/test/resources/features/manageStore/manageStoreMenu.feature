Feature: Store menu management
  as a  store I want to manage my  menu


  Scenario Outline: define the month recipe
    Given a recipe with the name of "<name>" was manufactured
    When the store add this recipe "<name>" in his menu
    Then the franchise menu and the store menu are actualize
    Examples:
      | name |
      | cookie au chocolat |
      | cookie au lait     |