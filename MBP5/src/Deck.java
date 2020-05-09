import java.util.Random;

public class Deck {
	
	static final String[] ranks = {"Two", "Three", "Four", "Five", "Six","Seven", 
			"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"}; 
	
	static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	
	private Card[] cardList = new Card[52];
	private int nCards;
	
	public Deck() {
		
		int index = 0;
		for (int i=0; i<4; i++){
			for (int j=0; j<13; j++) {
					cardList[index] = new Card(ranks[j],suits[i]);
					index++;
			}
		}
		
		nCards = 52;
		
	}
	
	public boolean isEmpty() {
		return nCards == 0;
	}
	
	public int size() {
		return nCards;
	}
	
	public Card deal() {
		Random r = new Random();
		
		while (true) {
			int position = r.nextInt(52);
			//System.out.println(position);
			Card dealtCard = new Card(cardList[position]);
			Card chosenCard = new Card(dealtCard);
			
			//System.out.println(dealtCard);
			//System.out.println(chosenCard);
		
			if (dealtCard.getRank() != "Chosen") {
				nCards = nCards - 1;
				cardList[position].setRank("Chosen");
				cardList[position].setSuit("Chosen");
				return chosenCard;
			}
		}
		
	}
	
	public String toString() {
		String result = "";
		for (int i=0;i<cardList.length;i++) {
			result = result + cardList[i].toString() + "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		Deck myDeck = new Deck();
		System.out.println(myDeck.toString());
		
		for (int i=0; i<52; i++) {
			Card thisCard = myDeck.deal();
			System.out.println(thisCard.toString());
		}
		
		System.out.println(myDeck.toString());
		
	}
	
}