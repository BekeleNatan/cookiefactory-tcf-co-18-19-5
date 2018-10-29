
  Feature: voir les magasins

  Scenario: il n'y a pas de magasin
    Given La franchise "CoD" avec 0 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il trouve 0 magasins

  Scenario: on peut voir les magasins
    Given La franchise "CoD" avec 5 magasins
    When Un client veut voir les magasins ou il peut commander
    Then Il trouve 5 magasins




