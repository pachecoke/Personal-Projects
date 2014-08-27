package projects.blackjack;

import java.util.Collections;
import java.util.List;

public class Dealer {
	private Shoe shoe;
	
	public Dealer() {
		shoe = new Shoe(GameOptions.getNumberOfDecks());
	}
	
	public Card deal() {
		return shoe.getCardsList().remove(0);
	}
	
	public void shuffle() {
		Collections.shuffle(shoe.getCardsList());
	}
	
	public void getCads(List<Card> cards) {
		shoe.getCardsList().addAll(shoe.getCardsList().size() - 1, cards);
	}
}
