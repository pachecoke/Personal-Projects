package projects.blackjack;

public class GameOptions {
	
	// Option key words //
	public static final String Allowed = "Allowed";
	public static final String notAllowed = "Not Allowed";
	public static final String dealerStands = "Dealer Stands";
	public static final String dealerHits = "Dealer Hits";
	
	// Options //
	private static int numberOfDecks = 3;
	private static String doubleAfterSplit = Allowed;
	private static String surrender = Allowed;
	private static String soft17 = dealerStands;

	private static int money = 1000;
	
	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		GameOptions.money = money;
	}

	public static void setNumberOfDecks(int numberOfDecks) {
		GameOptions.numberOfDecks = numberOfDecks;
	}

	public static int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	public static void toggleDoubleAfterSplit() {
		
		if(doubleAfterSplit.equals(Allowed)) {
			doubleAfterSplit = notAllowed;
			System.out.println("Double after split is not allowed.");
		} else {
			doubleAfterSplit = Allowed;
			System.out.println("Double after split is allowed.");
		}
	}
	
	public static void toggleSurrender() {
		
		if(surrender.equals(Allowed)) {
			surrender = notAllowed;
			System.out.println("Surrender is not allowed.");
		} else {
			surrender = Allowed;
			System.out.println("Surrender is allowed.");
		}
	}
	
	public static void toggleSoft17() {
		
		if(soft17.equals(dealerStands)) {
			soft17 = dealerHits;
			System.out.println("Dealer hits in Soft 17.");
		} else {
			soft17 = dealerStands;
			System.out.println("Dealer stands in Soft 17.");
		}
	}
	
	public static void printOptions() {
		
		System.out.println("== Options ==");
		
		System.out.printf("1. %18s:\t%d\n", "Deck", numberOfDecks);
		System.out.printf("2. %18s:\t%s\n", "Double after split", doubleAfterSplit);
		System.out.printf("3. %18s:\t%s\n", "Surrender", surrender);
		System.out.printf("4. %18s:\t%s\n", "Soft 17", soft17);
		System.out.printf("5. %18s:\t$%d\n", "Money", money);
		System.out.printf("6. %18s\n", "Play");
		
		System.out.println();
	}
	
	public static String getSoft17() {
		return soft17;
	}
}
