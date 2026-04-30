Feature:
Cart Page Functionality

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
    to Verify Behaviour Of cartPage After Adding MultipleProducts()

    And user should add min five products to the bag
    Then check for the products in the bag

  @RemoveProduct
  Scenario:
    to verify the product is removed from the cart after click on remove button

    When user clicks On any product on product Listing page
    And productDetailsPageOpens
    And user added the product to the bag
    And cart page opens
    Then user click on remove BUtton to remove product from cart and check if it is removed or not

  @RemoveMultipleProducts
  Scenario:
  to verify the behaviour of cart page after removing mmultiple products

    When user selects multiple products and user add that product to the bag
    And cart page opens and user remove multiple products from bag
    Then check product is removed or not

  @RemoveOnlySelectedProduct
  Scenario:
to verify only selected products should be remove from the cart

    When user added multiple prdocut to bag
    And cart page opens and select only some products
    Then check selective product is remove

  @MoveToWishList
  Scenario:
to verify product is move to wishlist without login

    When user selects multiple product and added to cart
    And products displays on cart page and move to wishlist withoutlogin
    Then product should not be move to wishlist

  @RemoveProductWithoutSelecting
  Scenario:
to verify remove product without selecting any product 

    When user added some products to cart
    Then removes without selecting that

  @ApplyCoupon
  Scenario:
to verify coupon is apllied or not to product 

    When user select product and added to cart and apply coupon on it
    Then user should be able to seee that applied coupon
