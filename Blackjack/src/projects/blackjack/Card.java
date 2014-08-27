package projects.blackjack;

public class Card {
	private String suit;
	private int value;
	
	public Card(int value, String suit) {
		this.suit = suit;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String ace = "ACE";
		String jack = "JACK";
		String queen = "QUEEN";
		String king = "KING";
		
		switch(value) {
		case 1:
			return ace + " " + suit;
		case 11:
			return jack + " " + suit;
		case 12:
			return queen + " " + suit;
		case 13:
			return king + " " + suit;
			default:
				return value + " " + suit;
		}
	}

}
