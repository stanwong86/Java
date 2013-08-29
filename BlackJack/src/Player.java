import java.util.ArrayList;

public abstract class Player extends Person {
	private ArrayList<Card> cards = new ArrayList();
	
	public Player() {
		
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void clearCards() {
		cards.clear();
	}
	
	public String showCards() {
		String cardsStr = "";
		for(Card card :cards) {
			cardsStr += card.getRank().rank();
			cardsStr += card.getSuit().suit() + " ";
		}
		return cardsStr;
	}
	
	public int sumOfCards() {
		int sum = 0;
		int aceCount = 0;
		Rank rank;
		for(Card card:cards) {
			rank = card.getRank();
			switch(rank) {
				case ACE:
					aceCount++;
					sum += 11;
					break;
				case TEN:
				case JACK:
				case QUEEN:
				case KING:
					sum += 10;
					break;
				default:
					sum += Character.getNumericValue(rank.rank());
					break;
			}			
		}
		while(aceCount > 0 && sum > 21) {
			sum -= 10;
			aceCount--;
		}
		return sum;
	}
}
