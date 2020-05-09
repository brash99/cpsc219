public class Card {
	
	private String rank;
	private String suit;
	
	public Card() {
		this.rank = "";
		this.suit = "";
	}
	
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card(Card other) {
		this.rank = other.rank;
		this.suit = other.suit;
	}
	
	public String getRank() {
		return this.rank;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String toString() {
		String result = "Card: rank = " + this.rank + " suit = " + this.suit;
		return result;
	}
	
	
}