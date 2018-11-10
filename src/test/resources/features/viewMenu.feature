
  Feature: voir le menu

  Background:
  Given La franchise "CoD" avec 20 magasins
  And La franchise contient une recette "hardChocolate" avec Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
  And La franchise contient une recette "whiteChocolate" avec Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4

  Scenario: La recette du mois est vide
    When Un client veut voir le menu des cookies proposes dans la store 1
    Then Il trouve 2 cookies

  Scenario: La recette du mois n'est pas vide
    Given Le store 1 contient une recette "monthChocolate" avec Dough : "Plain", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    When Un client veut voir le menu des cookies proposes dans la store 1
    Then Il trouve 3 cookies

  Scenario: La recette du mois est vide dans notre magasin mais pas dans tous
      Given Le store 2 contient une recette "monthChocolate" avec Dough : "Plain", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.2
      When Un client veut voir le menu des cookies proposes dans la store 1
      Then Il trouve 2 cookies

    