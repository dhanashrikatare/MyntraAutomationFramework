Feature: Place Order Functionality

  Scenario: Verify user can place order successfully

    When user enters "Lipsticks" in the search bar
    And user presses Enter on the search bar
    And user filters products by brand "Lakme"
    And user filters products by colour "Red"
    And user stores details of product at index 2
    And user clicks on product at index 2
    And user switches to child window
    Then product brand on PDP should match selected product brand
    And breadcrumb should contains "lipstick"
    And Add to Bag button should be displayed
    When user clicks on Add to Bag button
    Then Go to Bag option should be visible
    When user clicks on Go to Bag
    Then product price in cart should match selected product price
    And product brand in cart should match selected product brand
    When user clicks on Place Order button
    Then user should not see "order is placed" in cart URL
