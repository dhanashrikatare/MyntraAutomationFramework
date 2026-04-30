Feature: Verify Filters Functionality on PLP Page

  @colorFilter
  Scenario Outline: Verify colour filter functionality on PLP page for Lipsticks
    When User searches for "Lipstick"
    And User applies colour filter "<colour>"
    Then User should see colour filter applied in URL
  # Colour DataProvider for Cucumber Scenario Outline Examples

    Examples:
      | colour       |
      | White        |
      | Black        |
      | Transparent  |
      | Pink         |
      | Brown        |
      | Red          |
      | Green        |
      | Multi        |
      | Yellow       |
      | Blue         |
      | Beige        |
      | Purple       |
      | Orange       |
      | Nude         |
      | Gold         |
      | Maroon       |
      | Silver       |
      | Peach        |
      | Grey         |
      | Cream        |
      | Off White    |
      | Burgundy     |
      | Magenta      |
      | Lavender     |
      | Coffee Brown |
      | Mauve        |
      | Rose         |
      | Assorted     |
      | Rose Gold    |
      | Camel Brown  |
      | Coral        |
      | Olive        |
      | Tan          |
      | Rust         |
      | Bronze       |
      | Copper       |
      | Violet       |
      | Metallic     |
      | Champagne    |
      | Taupe        |
      | Khaki        |

  @plpValidation
  Scenario:
       to verify Product Listing Page Displays After Valid Product Search

    When user searches for "Lipstick"
    And hit the enter key
    Then user should see the plp page with multiple assertion

  @BrandFilter
  Scenario Outline: Verify user can filter products by brand
    When user searches for products "<product>"
    And user applies brand filters "<brand>"
    Then user should see products related to "<brand>"

    Examples:
      | product     | brand        |
      | lipstick    | Lakme        |
      | lipstick    | Maybelline   |
      | foundation  | LOreal       |
      | shampoo     | Dove         |
      | shampoo     | Tresemme     |
      | perfume     | Fogg         |
      | eyeliner    | Lakme        |
      | facewash    | Himalaya     |
      | moisturizer | Nivea        |
      | hair oil    | Parachute    |
      | sunscreen   | Lotus        |
      | body lotion | Vaseline     |
      | nail polish | Colorbar     |
      | mascara     | Maybelline   |
      | concealer   | Swiss Beauty |
      | blush       | Faces Canada |

  @SortOption
  Scenario Outline:
:
test case to verify sort by filter functionality on PLP page

    When user search for a beauty "lipsticks"
    And user selects sort option "<sortOption>"
    Then products should be sorted as per sortOption

    Examples:
      | sortOption         |
      | Price: Low to High |
      | Price: High to Low |
      | Customer Rating    |
      | Recommended        |
      | What s New         |
      | Popularity         |

  @DiscountFilter
  Scenario Outline: Verify lipstick products with discount filter on Myntra
    When user search for the products "<product>"
    And user applies discount filter "<discount>"
    Then user should see all listed products having minimum discount

    Examples:
      | product  | discount      |
      | Lipstick | 10% and above |
      | Lipstick | 20% and above |
      | Lipstick | 30% and above |
      | Lipstick | 40% and above |
      | Lipstick | 50% and above |
      | Lipstick | 60% and above |
      | Lipstick | 70% and above |
      | Lipstick | 80% and above |

  @GenderFilter
  Scenario Outline: Verify filter by gender for lipsticks
    When user search for the product lipsticks
    And user applies gender filter "<gender>"
    Then user should be redirected to gender specific page for gender

    Examples:
      | gender |
      | Women  |
      | Men    |
      | Girls  |
      | Boys   |

  @ProductCountFilter
  Scenario: Verify product count changes after applying filters
    When user search for thee product Lipsticks
    And user gets product count before filter
    And user applies brands filter "Lakme"
    And user applies colour filter pink
    And user gets product count after filter
    Then product count should decrease or remain same after applying filters

  @ProductSelection
  Scenario: Verify user can click on any lipstick product
    When users search for the product "Lipsticks"
    And user applies brand filter "Lakme"
    And user applies colour filter "pink"
    And user clicks on product number 2
    And user switches to new tab
    Then user should be redirected to product details page
    
    
    @ClearFilters
Scenario: Verify clear all functionality of filters for lipsticks

When user search forr the product "lipsticks"
And user applie brand filter "Maybelline"
And user applie product colour filter "red"
And user gets product count before clearing filters
And user clears all filters
And user gets product count after clearing filters
Then filter query should be removed from URL
And product count should increase or remain same after clearing filters



@BeautyPLP
Scenario: Verify beauty PLP page for Lipsticks search

When user search for the prooduct "Lipsticks"
Then breadcrumb should contain "Lipsticks"
And product count should be greater than 0
And page URL should contain "Lipsticks"
And page title should contain "Lipsticks"
