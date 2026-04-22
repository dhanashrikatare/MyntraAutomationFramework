Feature: Navigation tests for Myntra website
  This feature contains UI-level navigation checks for the Myntra homepage.
  It verifies that the homepage loads correctly and that primary navigation elements
  (logo, search bar, wishlist icon and the Beauty section) are present and working.
  The scenarios cover visibility of key elements and navigation to the Beauty page
  both by clicking the navigation tab and by visiting the direct URL.

Background:
When user open the url

Scenario:
Verify that the Myntra homepage loads successfully and the logo is visible

 
Then home page should be display


Scenario:
Verify that the search bar is visible on the homepage

Then search bar should be visible 

Scenario:
Verify that the wishlist icon is visible on the homepage

Then WishListIcon should be visible 

Scenario:
Verify that clicking the 'Beauty' navigation link opens the Beauty page

And click on beauty nav tab
Then beauty page should be open 

Scenario:
Verify that directly navigating to the Beauty page URL opens the Beauty page

 
Then beauty page should be open by direct beaauty url

