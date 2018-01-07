package texasholdem;

import java.util.Random;

/*
 * Trent Robison
 * AI ALGORITHM
 * 
 * Based of Monte Carlo Method see 
 * 
 * http://en.wikipedia.org/wiki/Monte_Carlo_method
 * 
 * for details.
 * 
 * Help from
 * 
 * http://cowboyprogramming.com/2007/01/04/programming-poker-ai/
 * 
 */
/*Personalities
 * 0 = normal
 * 1 = tightplayer-Only plays strong pre-flop hands folds a lot
 * 
 */
public class AI {

	public static final int TIGHT_PLAYER = 1;
	public static final int NORMAL_PLAYER = 0;
	private int playerType;
	private Player aiPlayer;
	private Player[] dummyPlayer = new Player[4];

	public AI() {
		aiPlayer = new Player();
		dummyPlayer[0] = new Player();
		dummyPlayer[1] = new Player();
		dummyPlayer[2] = new Player();
		dummyPlayer[3] = new Player();
	}

	public AI(Player p,int playerType) {
		this.playerType = playerType;
		aiPlayer = p;
		dummyPlayer[0] = new Player();
		dummyPlayer[1] = new Player();
		dummyPlayer[2] = new Player();
		dummyPlayer[3] = new Player();
	}

	public Player getAIPlayer() {
		return aiPlayer;
	}

	public void setAIPlayer(Player p) {
		aiPlayer = p;
	}

	private float getPercent(Card[] c) {
		if (c.length == 2) {
			return getPercentWinTwoCards(c);
		} else if (c.length == 3) {
			return getPercentinTwoCardsPlusOneTable(c);
		} else if (c.length == 4) {
			return getPercentinTwoCardsPlusTwoTable(c);
		} else if (c.length == 5) {
			return getPercentinTwoCardsPlusThreeTable(c);
		} else if (c.length == 6) {
			return getPercentinTwoCardsPlusFourTable(c);
		} else if (c.length == 7) {
			return getPercentinTwoCardsPlusFiveTable(c);
		} else {
			return -1;
		}
	}

	public Card[] getTestCards() {
		Card[] c;
		if (Main.table.getActiveCardsOnTable() == 0) {
			c = new Card[2];
			c[0] = aiPlayer.getCard(0);
			c[1] = aiPlayer.getCard(1);
			return c;
		} else if (Main.table.getActiveCardsOnTable() == 3) {
			c = new Card[5];
			c[0] = aiPlayer.getCard(0);
			c[1] = aiPlayer.getCard(1);
			c[2] = Main.table.cardsOnTable[0];
			c[3] = Main.table.cardsOnTable[1];
			c[4] = Main.table.cardsOnTable[2];
			return c;
		} else if (Main.table.getActiveCardsOnTable() == 4) {
			c = new Card[6];
			c[0] = aiPlayer.getCard(0);
			c[1] = aiPlayer.getCard(1);
			c[2] = Main.table.cardsOnTable[0];
			c[3] = Main.table.cardsOnTable[1];
			c[4] = Main.table.cardsOnTable[2];
			c[5] = Main.table.cardsOnTable[3];
			return c;
		} else {
			c = new Card[7];
			c[0] = aiPlayer.getCard(0);
			c[1] = aiPlayer.getCard(1);
			c[2] = Main.table.cardsOnTable[0];
			c[3] = Main.table.cardsOnTable[1];
			c[4] = Main.table.cardsOnTable[2];
			c[5] = Main.table.cardsOnTable[3];
			c[6] = Main.table.cardsOnTable[4];
			return c;
		}
	}

	private float getPotOdds(int pot, int playersBet) {
		if(pot == 0)pot = 20;
		if( playersBet == 0)playersBet = 0;
		// System.out.println("getPlayersBet: "+aiPlayer.getPlayersBet()+" pot: "+pot);
		float odds = (float) playersBet / ((float) pot + (float) playersBet);
		return odds;
	}

	public float rateOfReturn(Card[] c, int pot, int playersBet) {
		// System.out.println("getPercent: "+getPercent(c)+" getPotOdds: "+getPotOdds(pot));
		float ROR = getPercent(c) / getPotOdds(pot, playersBet);
		//System.out.println(ROR);
		return ROR;
	}

	/*
	 * 2 Cards 4 Table
	 */
	private float getPercentinTwoCardsPlusFiveTable(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			// First table card set
			tableCards[0] = c[2];
			tableCards[1] = c[3];
			tableCards[2] = c[4];
			tableCards[3] = c[5];
			tableCards[4] = c[6];

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}
		
		Player.percentWin = win[4]/5000;
		return (float) ((float) win[4] / 5000);
	}

	/*
	 * 2 Cards 4 Table
	 */
	private float getPercentinTwoCardsPlusFourTable(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			// First table card set
			tableCards[0] = c[2];
			tableCards[1] = c[3];
			tableCards[2] = c[4];
			tableCards[3] = c[5];

			for (int i = 4; i < 5; i++) {
				tableCards[i] = d.dealOffTheTopOfDeck();
			}

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}

		Player.percentWin = win[4]/5000;

		return (float) ((float) win[4] / 5000);
	}

	/*
	 * 2 Cards 3 Table
	 */
	private float getPercentinTwoCardsPlusThreeTable(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			// First table card set
			tableCards[0] = c[2];
			tableCards[1] = c[3];
			tableCards[2] = c[4];

			for (int i = 3; i < 5; i++) {
				tableCards[i] = d.dealOffTheTopOfDeck();
			}

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}

		Player.percentWin = win[4]/5000;
		return (float) ((float) win[4] / 5000);
	}

	/*
	 * 2 Cards 2 Table
	 */

	private float getPercentinTwoCardsPlusTwoTable(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			// First table card set
			tableCards[0] = c[2];
			tableCards[1] = c[3];

			for (int i = 2; i < 5; i++) {
				tableCards[i] = d.dealOffTheTopOfDeck();
			}

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}

		Player.percentWin = win[4]/5000;
		return (float) ((float) win[4] / 5000);

	}

	/*
	 * 2 Cards 1 Table
	 */
	private float getPercentinTwoCardsPlusOneTable(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			// First table card set
			tableCards[0] = c[2];
			for (int i = 1; i < 5; i++) {
				tableCards[i] = d.dealOffTheTopOfDeck();
			}

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}

		Player.percentWin = win[4]/5000;
		return (float) ((float) win[4] / 5000);
	}

	/*
	 * 2 Cards 0 Table
	 */
	private float getPercentWinTwoCards(Card[] c) {
		Hand[] h = new Hand[5];
		Card[] tableCards = new Card[5];
		int[] win = new int[5];
		int amountOfHands = 0;

		aiPlayer = new Player();
		while (amountOfHands < 5000) {
			Deck d = new Deck();
			d.findAndDiscard(c);
			d.shuffleDeck();

			// Create Table Cards
			for (int i = 0; i < 5; i++) {
				tableCards[i] = d.dealOffTheTopOfDeck();
			}

			aiPlayer.setCard(0, c[0]);
			aiPlayer.setCard(1, c[1]);

			// Deal dummy cards
			for (int i = 0; i < 4; i++) {
				dummyPlayer[i].setCard(0, d.dealOffTheTopOfDeck());
				dummyPlayer[i].setCard(1, d.dealOffTheTopOfDeck());
			}

			Card[] aiPlayerSevenCards = new Card[7];

			aiPlayerSevenCards[0] = dummyPlayer[0].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[0].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[0].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[1].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[1].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[1].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[2].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[2].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[2].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = dummyPlayer[3].getCard(0);
			aiPlayerSevenCards[1] = dummyPlayer[3].getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			dummyPlayer[3].getGoodHand(aiPlayerSevenCards);

			aiPlayerSevenCards[0] = aiPlayer.getCard(0);
			aiPlayerSevenCards[1] = aiPlayer.getCard(1);
			aiPlayerSevenCards[2] = tableCards[0];
			aiPlayerSevenCards[3] = tableCards[1];
			aiPlayerSevenCards[4] = tableCards[2];
			aiPlayerSevenCards[5] = tableCards[3];
			aiPlayerSevenCards[6] = tableCards[4];
			aiPlayer.getGoodHand(aiPlayerSevenCards);

			for (int i = 0; i < 4; i++) {
				h[i] = dummyPlayer[i].getBestHand();
			}
			h[4] = aiPlayer.getBestHand();

			win[getIndexOfWinner(h)]++;
			amountOfHands++;
		}

		Player.percentWin = win[4]/5000;
		return (float) ((float) win[4] / 5000);
	}

	private int getIndexOfWinner(Hand[] h) {

		int index = 0;
		int bestRank;
		HandEvaluation hv = new HandEvaluation(h[0]);
		bestRank = hv.giveHandIntegerRanking();

		for (int i = 0; i < h.length; i++) {
			hv = new HandEvaluation(h[i]);
			int temp = hv.giveHandIntegerRanking();
			if (temp > bestRank) {
				bestRank = temp;
				index = i;
			}
		}
		return index;
	}

	private int getRandomNum() {
		Random rn = new Random();
		int n = 100 + 1;
		int i = rn.nextInt() % n;
		return i;
	}
	private int randomNumBetween(int low, int high){
		Random r = new Random();
		int num = r.nextInt(high+1-low)+low;
		return num;
	}
	/*
	 * error = -2 fold = -1 call = 0 bet = >0
	 */

	public int aiBetCallFold(Card[] c, int pot, int playersBet) {
		float RR = rateOfReturn(c, pot, playersBet);
		System.out.println("Percent: " + (RR*100) + "%");
		int randomNum = getRandomNum();

		if (playersBet == 0) {
			return 1;
		}

		/*
		 * Early in game less emphasis on cards
		 */
	if(playerType == TIGHT_PLAYER){
		if (c.length == 2) {
			if (RR < .3) {
				if (randomNum < 90) {
					return -1;
				} else {// bluff 10% of time
					return 0;
				}
			} else if (RR < .4) {
				if (randomNum < 85) {
					return -1;
				} else {// call 15% of time
					return 0;
				}
			} else if (RR < .5) {
				if (randomNum < 80) {
					return -1;
				} else {//call 20%
					return 0;
				}
			} else if (RR < .6) {
				if (randomNum < 50) {
					return -1;
				} else {//call 50%
					return 0;
				}
			} else if (RR < .7) {
				if (randomNum < 50) {
					return 0;
				} else {//call 50%
					return randomNumBetween(playersBet,playersBet + 10);
				}
			}else if (RR < .8){
				return 0;
			} else if( RR < 1.0){
				return randomNumBetween(playersBet,playersBet + 20);
			}else if(  RR < 2.0){
				return randomNumBetween(playersBet,playersBet + 60);
			}else{
				return randomNumBetween(playersBet,playersBet + 100);
			}
		}
	}else if(playerType == NORMAL_PLAYER){
		if (c.length == 2) {
			if (RR < .3) {
				return -1;
			} else if (RR < .4) {
				if (randomNum < 85) {
					return -1;
				} else {// call 15% of time
					return 0;
				}
			} else if (RR < .5) {
				if (randomNum < 50) {
					return -1;
				} else {//call 50%
					return 0;
				}
			} else if (RR < .6) {
				if (randomNum < 20) {
					return -1;
				} else {//call 80%
					return 0;
				}
			} else if (RR < .7) {
				return 0;
			}else if (RR < .8){
				return randomNumBetween(playersBet,playersBet + 10);
			} else if( RR < 1.0){
				return randomNumBetween(playersBet,playersBet + 30);
			}else if(  RR < 2.0){
				return randomNumBetween(playersBet,playersBet + 60);
			}else{
				return randomNumBetween(playersBet,playersBet + 100);
			}
		}
	}
	
	if (c.length == 4 || c.length == 5 || c.length == 6 || c.length == 7 ) {
		if (RR < .8) {
			if (randomNum < 90) {
				return -1;
			} else if( randomNum < 95){
				return 0;
			}else{
				return playersBet;//bluff
			}
		} else if (RR < 1.0) {
			if (randomNum < 80) {
				return -1;
			}else if(RR < 85){
				return 0;
			} else {
				return randomNumBetween(playersBet,playersBet + 10);
			}
		} else if (RR < 1.3) {
			if (randomNum < 60) {
				return 0;
			} else if (randomNum < 100) {
				return randomNumBetween(playersBet,playersBet + 20);
			}
		}else if (RR < 1.5) {
			if (randomNum < 30) {
				return 0;
			} else if (randomNum < 70) {
				return randomNumBetween(playersBet,playersBet + 20);
			}
		}else{
			return randomNumBetween(playersBet,playersBet + 200);
		}
	}
		return -2;	
	}
}
