Feature: Verify Brand Filter Functionality on PLP Page

  Background:
    When User searches for "Lipstick"
@colorFilter
  Scenario Outline: Verify colour filter functionality on PLP page for Lipsticks
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

    And hit the enter key
    Then user should see the plp page with multiple assertion

@BrandFilter
Scenario: test case to verify brand filter functionality on PLP page

And user applies brand filter "<brand>"
Then user should see brand filter in url
