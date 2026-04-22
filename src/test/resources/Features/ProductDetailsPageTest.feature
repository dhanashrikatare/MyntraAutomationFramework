Feature:

  Background:
    Given User searches For The beauty Product
    When user click on the product on plp page
    And product details page opens

@ProductOnPdp
  Scenario:
   test case to verify that when user click on any product on product listing page then product details page opens with correct product information

    Then that product should be display in the product details page and product details page opened.

  Scenario:
  Verify PDP corresponds to the product selected on PLP: checks breadcrumb/brand match, price visibility, wishlist and add-to-bag buttons

    Then validate the BrandName ,BreadCrumb,Product Price ,WishList Icon For Selected product on pdp page

  Scenario:
  Test to verify that a user can add a product to the bag from the Product Details page,
			 selects shade if required, clicks Add to Bag and verifies Go To Bag appears

    Then pdp page opens and validate the GotoBag appears after adding to cart

  Scenario:
  Verify that clicking wishlist on PDP without login prompts for login

    And add selected product to the wishList without login
    Then user on the login page

 # Scenario:
#Verify PDP shows correct message for each tried pincode (valid/invalid)

    #And user enters valid/invalid pincode
    #Then user should see the messages for he valid and invalid pincode
