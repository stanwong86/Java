
public enum Rank {
	ACE('A'),
	DEUCE('2'),
	THREE('3'),
	FOUR('4'),
	FIVE('5'),
	SIX('6'),
	SEVEN('7'),
	EIGHT('8'),
	NINE('9'),
	TEN('T'),
	JACK('J'),
	QUEEN('Q'),
	KING('K');
	
	private char rank;
	
	Rank(char rank) {
		this.rank = rank;
	}
	
	public char rank() {
		return rank;
	}
}
