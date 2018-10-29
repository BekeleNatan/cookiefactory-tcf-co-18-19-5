
  Feature: voir les magasins

  Background:

  Scenario: il n'y a pas de magasin
    Given Une franchise avec 0 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il trouve 0 magasins

  Scenario: on peut voir les magasins
    Given Une franchise avec 5 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il trouve 5 magasins




