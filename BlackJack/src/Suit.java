public enum Suit {
	DIAMOND("d"),
	CLUB("c"),
	HEART("h"),
	SPADE("s");
	
	private String suit = null;
	
	Suit(String suit) {
		this.suit = suit;
	}
	
	public String suit() {
		return suit;
	}
	
}
