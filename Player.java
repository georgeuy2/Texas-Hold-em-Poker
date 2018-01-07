package texasholdem;

public class Player {
	private int playerChips = 0;
	private int playersID = 0;
	private int playersBet = 0;
	private boolean fold = false;
	private boolean isHuman;
	private Hand bestHand;
	private String name;
	private float currentRR;
	public static float percentWin;
	private Card[] cards = new Card[2];

	// Players Constructor
	public Player(int playersID) {
		this.playersID = playersID;
	}
	public void setPlayersName(String name){
		this.name=name;
	}
	
	public String getPlayersName(){
		return name;
	}


	public Player() {
		bestHand = null;
	}

	// returns player's ID
	public int getPlayersID() {
		return this.playersID;
	}

	// set a player's ID
	public void setPlayersID(int playersID) {
		this.playersID = playersID;
	}

	public Card getCard(int index) {
		return cards[index];
	}

	// return total player's chips
	public int getChips() {
		return this.playerChips;
	}

	// set player's chips
	public void setChips(int playerChips) {
		this.playerChips = playerChips;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCard(int i, Card c) {
		this.cards[i] = c;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public int getPlayersBet() {
		return playersBet;
	}

	public void setPlayersBet(int playersBet) {
		this.playersBet = playersBet;
	}

	public boolean isFold() {
		return fold;
	}

	public void setFold(boolean fold) {
		this.fold = fold;
	}

	public Hand getBestHand() {
		return bestHand;
	}

	public void setBestHand(Hand bestHand) {
		this.bestHand = bestHand;
	}

	public static Hand[] getAllCombinationsOfFiveCards(Card[] c) {
		Hand h[] = new Hand[21];

		h[0] = new Hand(new Card[] { c[0], c[1], c[2], c[3], c[4] });//
		h[1] = new Hand(new Card[] { c[0], c[1], c[2], c[3], c[5] });//
		h[2] = new Hand(new Card[] { c[0], c[1], c[2], c[3], c[6] });//
		h[3] = new Hand(new Card[] { c[0], c[1], c[2], c[4], c[5] });//
		h[4] = new Hand(new Card[] { c[0], c[1], c[2], c[4], c[6] });//
		h[5] = new Hand(new Card[] { c[0], c[1], c[2], c[5], c[6] });//
		h[6] = new Hand(new Card[] { c[0], c[1], c[3], c[4], c[5] });//
		h[7] = new Hand(new Card[] { c[0], c[1], c[3], c[4], c[6] });//
		h[8] = new Hand(new Card[] { c[0], c[1], c[3], c[5], c[6] });//
		h[9] = new Hand(new Card[] { c[0], c[1], c[4], c[5], c[6] });//
		h[10] = new Hand(new Card[] { c[0], c[2], c[3], c[4], c[5] });//
		h[11] = new Hand(new Card[] { c[0], c[2], c[3], c[4], c[6] });//
		h[12] = new Hand(new Card[] { c[0], c[2], c[3], c[5], c[6] });//
		h[13] = new Hand(new Card[] { c[0], c[2], c[4], c[5], c[6] });//
		h[14] = new Hand(new Card[] { c[0], c[3], c[4], c[5], c[6] });//
		h[15] = new Hand(new Card[] { c[1], c[2], c[3], c[4], c[5] });//
		h[16] = new Hand(new Card[] { c[1], c[2], c[3], c[4], c[6] });//
		h[17] = new Hand(new Card[] { c[1], c[2], c[3], c[5], c[6] });//
		h[18] = new Hand(new Card[] { c[1], c[2], c[4], c[5], c[6] });
		h[19] = new Hand(new Card[] { c[1], c[3], c[4], c[5], c[6] });
		h[20] = new Hand(new Card[] { c[2], c[3], c[4], c[5], c[6] });
		return h;
	}

	public void getGoodHand(Card[] c) {
		Hand[] h = new Hand[21];
		h = getAllCombinationsOfFiveCards(c);
		Hand bestHand = h[0];
		int bestRank;
		HandEvaluation hv = new HandEvaluation(h[0]);
		bestRank = hv.giveHandIntegerRanking();
		for (int i = 1; i < h.length; i++) {
			hv = new HandEvaluation(h[i]);
			int temp = hv.giveHandIntegerRanking();
			if (temp > bestRank) {
				bestRank = temp;
				bestHand = h[i];
			}
		}
		this.bestHand = bestHand;
	}

	@Override
	public String toString() {
		return cards[0].toReadableString() + "  " + cards[1].toReadableString();
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public float getCurrentRR() {
		return currentRR;
	}

	public void setCurrentRR(float currentRR) {
		this.currentRR = currentRR;
	}

}
