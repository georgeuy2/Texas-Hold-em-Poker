package texasholdem;

public class Hand {
	private int points;
	private Card card[];

	public Hand(Card[] card) {
		this.card = card;
	}

	public Hand() {
		card = new Card[5];
		card[0] = new Card();
		card[1] = new Card();
		card[2] = new Card();
		card[3] = new Card();
		card[4] = new Card();
	}

	public Card[] getCards() {
		return card;
	}

	public Card getCard(int index) {
		return card[index];
	}

	public void setCard(int index, Card c) {
		card[index] = c;
	}

	@Override
	public String toString() {
		return "Card 0: " + card[0].toString() + "\n" + "Card 1: "
				+ card[1].toString() + "\n" + "Card 2: " + card[2].toString()
				+ "\n" + "Card 3: " + card[3].toString() + "\n" + "Card 4: "
				+ card[4].toString() + "\n";
	}

}
