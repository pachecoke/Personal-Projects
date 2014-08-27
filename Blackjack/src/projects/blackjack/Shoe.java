package projects.blackjack;

import java.util.LinkedList;
import java.util.List;

public class Shoe {
	private int numberOfDecks;
	private int minNumberOfDecks = 1;
	private int maxNumberOfDecks = 8;
	private List<Card> cardsList = new LinkedList<Card>();
	
	public Shoe (int numberOfDecks) {
		
		if (numberOfDecks < minNumberOfDecks || numberOfDecks > maxNumberOfDecks) {
			System.out.println(this.getClass().getName() + ": Can not accept " + numberOfDecks + " number of decks.");
		}
		
		this.numberOfDecks = numberOfDecks;
		buildShoe(this.numberOfDecks);
	}

	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}
	
	private void buildShoe(int numberOfDecks) {
		
		for(int i = 0; i < numberOfDecks; i++) {
			Deck deck = new Deck();
			cardsList.addAll(deck.getDeck());
		}
	}

	public List<Card> getCardsList() {
		return cardsList;
	}

	public void setCardsList(List<Card> cardsList) {
		this.cardsList = cardsList;
	}
	
}
