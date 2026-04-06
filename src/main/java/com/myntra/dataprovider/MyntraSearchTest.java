package com.myntra.dataprovider;

import org.testng.annotations.DataProvider;

public class MyntraSearchTest {
	
	@DataProvider(name="searchData")
	public static Object[][]getData(){
		return new Object[][] {
			 {"lipstick"},
		        {"foundation"},
		        {"shampoo"}
		    };
	}
	
	
	@DataProvider(name="colourData")
	public static Object[][]provideColours() {
		return new Object[][] {
			{"red"},
			{"pink"},
			{"nude"}
		};
	}
	
	@DataProvider(name="sortBy")
	public static Object[][]sortByData(){
		return new Object[][] {
			{"Recommended"},{"What's New"},{"Popularity"},{"Better Discount"},{"Price: High to Low"},{"Price: Low to High"},{"Customer Rating"}
		};
		
	}

}
