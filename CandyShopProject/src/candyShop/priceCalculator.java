package candyShop;

abstract class priceCalculator {
	
	// Candy prices
	protected double sweetTartPrice = 0.87;
	protected double fizziPopPrice = 1.23;
	protected double karmaTartPrice = 0.64;
	protected double pixieRodPrice = 1.59;
	protected double mikeIkePrice = 1.41;
	protected double butterfingerPrice = 0.72;
	protected double missionariPrice = 0.69;
	protected double musketeerPrice = 1.86;
	
	// Sales tax charged to clients
	protected double salesTax = 1.0635;
	
	// Percent of sales paid by store; used to determine profits
	protected double expensePercent = 0.1536;
	
	// Abstract methods
	abstract double totalPrice(int numCandies, String candyName);
	
	abstract double totalProfit(double clientPurchase);
}
