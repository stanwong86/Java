import java.util.ArrayList;

public class Dealer extends Player {
	
	public Dealer() {
		
	}
	
	// Dealer has to cover first Card	
	public String hideCards() {
		String cardsStr = "";
		ArrayList<Card> cards = getCards();
		boolean firstCard = true;
		for(Card card :cards) {
			if(firstCard) {
				firstCard = false;
				cardsStr += "Xx ";
			} else {
				cardsStr += card.getRank().rank();
				cardsStr += card.getSuit().suit() + " ";
			}
		}
		return cardsStr;
	}
	
}
