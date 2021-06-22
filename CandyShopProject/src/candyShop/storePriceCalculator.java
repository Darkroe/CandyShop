package candyShop;

public class storePriceCalculator extends priceCalculator{
	private double totalProfit;
	
	// Constructor
	public storePriceCalculator() {
		totalProfit = 0.0;
	}
	
	// Overloaded Constructor (Unused)
	public storePriceCalculator(double profitInitial) {
		totalProfit = profitInitial;
	}
	
	// Unused method from parent abstract class
	@Override
	double totalPrice(int numCandies, String candyName) {
		return 0;
	}
	
	// Calculates the total profits made on a client purchase and adds that number to the stored profits value
	@Override
	double totalProfit(double clientPurchase) {
		totalProfit = totalProfit + clientPurchase * (1.0 - expensePercent);
		return totalProfit;
	}
}
