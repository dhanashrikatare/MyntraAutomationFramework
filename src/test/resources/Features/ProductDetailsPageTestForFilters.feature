Feature:

  Background:
    Given User searches For The beauty Product
    And user applies some filter to select product
    When user click on the product on plp page
    And product details page opens

  @ForPincode
  Scenario:
Verify PDP shows correct message for each tried pincode (valid/invalid)

   
    Then user enters pincodes check for the message for entered pincode

  @ForEmptyPincodeField
  Scenario:
test case to verify that when user tries to add product to cart and check delivery availability with blank pincode field then it should show error message

    And user leaves pincode field as empty and click on check button
    Then user should see error message for that

  