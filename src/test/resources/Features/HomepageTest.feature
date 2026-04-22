# Feature: Homepage functionality
#
# As a customer visiting the Myntra homepage I want to be able to
# - search for products (with autosuggestions)
# - handle invalid search inputs (special characters, numbers, gibberish)
# - access wishlist and orders (which should redirect to login when not signed in)
#
Feature:
Homepage functionality


#positive Scenario 1
@SearchFunctionality
Scenario Outline::
test case to verify search functionality with valid products

When user search for the Various "<product>"
Then user should see the result for the valid product

Examples:
  | product      |
  | lipstick     |
  | foundation   |
  | shampoo      |
  | perfume      |
  | eyeliner     |
  | facewash     |
  | moisturizer  |
  | hair oil     |
  | sunscreen    |
  | body lotion  |
  | nail polish  |
  | mascara      |
  | concealer    |
  | blush        |
  | eyeshadow    |

#Negative Scenario 1
@SpecialCharInvalid
Scenario:
test case to verify search functionality with invalid products with special characters

When user enters special character to search product
Then user cannot find the Beauty Products


#Negative Scenario 2
@NumberInvalidProduct
Scenario:
test case to verify search functionality with invalid numbers

When user enters numbers to search products 
Then user cannot find any matches

#Negative Scenario 3
@InvalidKeyWord
Scenario:
test case to verify search functionality with invalid products

When user enters giberish text to search product
Then user cannot see product 

#Negative Scenario 4
@SuggestionDisplay
Scenario:
test case to verify search suggestions are displayed while typing

When user enters some keyword to search
Then user should see the autosuggestions

#Negative Scenario 5
@WishListWithoutLogin
Scenario:
test case to verify that when user tries to see wishlist without login then it should redirect to login page

When user click on wishlist icon without login 
Then user should be on the login page 

#Negative Scenario 6

@OrdersListWithoutLogin
Scenario:
test case to verify that when user tries to see orders list without login then it should redirect to login page

When user click on orders List without login
Then user should redirected on the login page


