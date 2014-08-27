package projects.blackjack;

import java.util.List;
import java.util.LinkedList;

public class Deck {
	private int numberOfCards = 52;
	private int numberOfSuits = 4;
	private List<Card> deck = new LinkedList<Card>();
	private Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES};
	
	public Deck() {
		generateDeck();
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	private void generateDeck() {
		
		for(Suit suit: suits) {
			for(int i = 0; i < numberOfCards/numberOfSuits; i++) {
				Card card = new Card(i+1, suit.toString());
				deck.add(card);
			}
		}
	}
	
}
