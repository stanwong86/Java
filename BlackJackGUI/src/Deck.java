import java.util.ArrayList;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();
	Suit[] suit = Suit.values();
	Rank[] rank = Rank.values();
	private Random rand = new Random();
	
	// Generate 52 card deck
	public Deck() {
		for(Rank r:rank) {
			for(Suit s:suit) {
				Card card = new Card(s,r);
				deck.add(card);
			}
		}
	}
	
	public Card drawCard() {
		int indexOfDrawnCard = rand.nextInt(deck.size());
		Card drawnCard = deck.get(indexOfDrawnCard);
		deck.remove(drawnCard);
		return drawnCard; 
	}
	
	public void printList() {
		for(Card card : deck) {
			System.out.println(card.getRank().rank() + "" +card.getSuit().suit());
		}
	}
	
}
