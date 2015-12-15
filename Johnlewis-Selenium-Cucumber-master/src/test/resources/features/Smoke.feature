
Feature: Smoke test pack for john lewis


  Scenario: check the home page is displayed with options

    Given user open a browser
    When open the ham burger menu
    Then I should see the following option
      | link | Home                 |
      | link | Browse by Department |
      | link | Partnership Card     |
      | link | Download our App     |
      | link | Contact Us           |
    When close the ham burger menu
    Then I should see the following option
    |button|Sign up               |


    Scenario Outline: User can find store

      Given user open a browser
      When he open an all the shops
      Then the branch "<Branch>" should be shown
      And he opens the branch
      Then the details of the branch should be shown

      Examples:
      |Branch|
      |Glasgow|



  Scenario: Check item can be added to the basket
    Given user is in home page
    When I search for "Computers"
    And added an item to the basket with title "Buy HP Envy 15-AE002NA Laptop PC, Intel Core i7, 12GB RAM, 256GB,15.6"
    Then an item should be available in basket



@Smoke
  Scenario: verify user can navigate to Foreign currency converter page
    Given user is in the home page
    When  he click on the foreign currency link
    And  it should navigate to the Foreign currency page
    Then user should see the currency converter

@Temp
  Scenario: check whether the user can goto men's section in Department

    Given user should be on the home page
    When  user clicks the men's section image link
    And he should see the men's shopping section page
    Then he should see the information is appeared on the page










