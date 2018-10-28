
  Feature: voir les magasins

  Background:

  Scenario: il n'y a pas de magasin
    Given Une franchise contenant 0 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il en trouve 0

  Scenario: on peut voir les magasins
    Given Une franchise contenant 5 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il en trouve 5




