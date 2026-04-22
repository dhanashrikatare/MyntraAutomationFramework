Feature:

  Background:
    Given User search For The beauty Product

  @AddSingleProductToBag
  Scenario:
verify to check the product is added to bag successfully

    When plp page opens and user clickOn the product
    And productDetails page opens
    And user add the product to the bag
    And user go to bag to see the product in the bag
    Then user should see the selected product is added to the bag with correct info

  @AddMultipleProduct
  Scenario:
to Verify Behaviour Of cartPag eAfter Adding MultipleProducts()

    And user should add min five products to the bag
    Then check for the products in the bag
