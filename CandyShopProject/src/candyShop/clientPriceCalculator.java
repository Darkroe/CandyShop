package candyShop;

public class clientPriceCalculator extends priceCalculator {
	
	private double totalPrice;
	
	// Constructor
	public clientPriceCalculator() {
		totalPrice = 0.0;
	}
	
	// Calculates the total prices of a certain number of a specific candy
	@Override
	public double totalPrice(int numCandies, String candyName) {
		if (candyName.equals("Sweet Tarts")) {
			totalPrice = numCandies * sweetTartPrice * salesTax;
		}
		else if (candyName.equals("Fizzi Pops")) {
			totalPrice = numCandies * fizziPopPrice * salesTax;
		}
		else if (candyName.equals("Karma Tarts")) {
			totalPrice = numCandies * karmaTartPrice * salesTax;
		}
		else if (candyName.equals("Pixie Rods")) {
			totalPrice = numCandies * pixieRodPrice * salesTax;
		}
		else if (candyName.equals("Mike & Ikes")) {
			totalPrice = numCandies * mikeIkePrice * salesTax;
		}
		else if (candyName.equals("Butterfingers")) {
			totalPrice = numCandies * butterfingerPrice * salesTax;
		}
		else if (candyName.equals("Missionaries")) {
			totalPrice = numCandies * missionariPrice * salesTax;
		}
		else if (candyName.equals("Musketeers")) {
			totalPrice = numCandies * musketeerPrice * salesTax;
		}
		return totalPrice;
	}
	
	// Unused method from parent abstract class
	@Override
	double totalProfit(double clientPurchase) {
		return 0;
	}

}
