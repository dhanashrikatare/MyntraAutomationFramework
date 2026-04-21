package com.myntra.dataprovider;

import org.testng.annotations.DataProvider;

public class MyntraSearchTest {

	@DataProvider(name = "searchData")
	public static Object[][] getData() {
		return new Object[][] { { "lipstick" }, { "foundation" }, { "shampoo" }, { "perfume" }, { "eyeliner" },
				{ "facewash" }, { "moisturizer" }, { "hair oil" }, { "sunscreen" }, { "body lotion" },
				{ "nail polish" }, { "mascara" }, { "concealer" }, { "blush" }, { "eyeshadow" } };
	}

	@DataProvider(name = "colourDataForLipstick")
	public Object[][] beautyColors() {
		return new Object[][] { { "White" }, { "Black" }, { "Transparent" }, { "Pink" }, { "Brown" }, { "Red" },
				{ "Green" }, { "Multi" }, { "Yellow" }, { "Blue" }, { "Beige" }, { "Purple" }, { "Orange" }, { "Nude" },
				{ "Gold" }, { "Maroon" }, { "Silver" }, { "Peach" }, { "Grey" }, { "Cream" }, { "Off White" },
<<<<<<< Updated upstream
				{ "Burgundy" }, { "Magenta" }, { "Lavender" }, { "Coffee Brown" }, { "Mauve" }, { "Navy Blue" },
				{ "Rose" }, { "Assorted" }, { "Rose Gold" }, { "Turquoise Blue" }, { "Camel Brown" }, { "Coral" },
				{ "Teal" }, { "Olive" }, { "Tan" }, { "Rust" }, { "Sea Green" }, { "Charcoal" }, { "Bronze" },
				{ "Copper" }, { "Violet" }, { "Lime Green" }, { "Steel" }, { "Metallic" }, { "Mustard" },
				{ "Champagne" }, { "Taupe" }, { "Fluorescent Green" }, { "Khaki" }, { "Grey Melange" }, { "Skin" } };
=======
				{ "Burgundy" }, { "Magenta" }, { "Lavender" }, { "Coffee Brown" }, { "Mauve" },
				{ "Rose" }, { "Assorted" }, { "Rose Gold" }, { "Camel Brown" }, { "Coral" },
				{ "Olive" }, { "Tan" }, { "Rust" },  { "Bronze" },
				{ "Copper" }, { "Violet" }, { "Metallic" }, 
				{ "Champagne" }, { "Taupe" }, { "Khaki" } };
>>>>>>> Stashed changes
	}

	@DataProvider(name = "sortBy")
	public static Object[][] sortByData() {
		return new Object[][] { { "Recommended" }, { "What's New" }, { "Popularity" }, { "Better Discount" },
				{ "Price: High to Low" }, { "Price: Low to High" }, { "Customer Rating" } };

	}

	
	
	@DataProvider(name = "discountFilterForLipstick")
	public Object[][] discountFilterData() {
	    return new Object[][] {
	        {"10% and above"},
	        {"20% and above"},
	        {"30% and above"},
	        {"40% and above"},
	        {"50% and above"},
	        {"60% and above"},
	        {"70% and above"},
	        {"80% and above"}
	    };
	}
	@DataProvider(name = "genderData")
	public Object[][] genderData() {
	    return new Object[][] {
	        {"Men"},
	        {"Women"},
	        {"Boys"},
	        {"Girls"}
	    };
	}
	
}
