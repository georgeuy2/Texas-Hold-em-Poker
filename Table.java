package texasholdem;

import java.awt.Image;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Table {
	private BoardGame UIGame;
	private int tablesPot = 0;
	private int activePlayers = 5;
	private int whoIsDealing = 0;
	private int currentBet;
    Player[] player;
	static Card[] cardsOnTable;

	// private Player [] tablePlayers;
	// default table sets tablesPot, active players, dealer, current bet to zero
	public Table() {
		this.tablesPot = 0;
		this.activePlayers = 5;
		this.whoIsDealing = 0;
		whoIsDealing = 0;
		currentBet = 0;
		player = new Player[5];
		cardsOnTable = new Card[5];

		cardsOnTable[0] = new Card();
		cardsOnTable[1] = new Card();
		cardsOnTable[2] = new Card();
		cardsOnTable[3] = new Card();
		cardsOnTable[4] = new Card();

	}
	public void setPlayer(Player[] p){
		player=p;
	}
	public boolean getFoldCountBool() {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (player[i].isFold() == true)
				count++;
		}
		if(count == 4){
			System.out.println("Everyone Folded");
			return true;
		}else{
			return false;
		}
	}
	public int getFoldCount() {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (player[i].isFold() == true)
				count++;
		}
		return count;
	}
	// Setting amount of current bet
	public void setCurrentBet(int currentBet) {
		this.currentBet = currentBet;
	}

	// returns the amount of current bet
	public int getCurrentBet() {
		return this.currentBet;
	}

	// returns a players number
	public int getWhoIsDealing() {
		return this.whoIsDealing;
	}
	
	public void dealCards() {
		Main.d = new Deck();
		Main.d.cut();
		Main.d.shuffleDeck();

		for (int i = 0; i < Main.AMOUNT_OF_PLAYERS; i++) {
			Card[] c = new Card[] { Main.d.dealOffTheTopOfDeck(),
					Main.d.dealOffTheTopOfDeck() };
			player[i].setCards(c);
		}
	}

	public int getIndexOfWinner(Hand[] h) {

		int index = 0;
		int bestRank;
		HandEvaluation hv = new HandEvaluation(h[0]);
		if(player[0].isFold() == true){
			bestRank = 0;
		}else{
			bestRank = hv.giveHandIntegerRanking();
		}
		for (int i = 0; i < h.length; i++) {
			hv = new HandEvaluation(h[i]);
			int temp = 0;
			if(player[i].isFold()==true){
				temp = 0;
			}else{
				temp = hv.giveHandIntegerRanking();	
			}
			if (temp > bestRank) {
				bestRank = temp;
				index = i;
			}
		}
		return index;
	}

	public static void initiateFlop() {
		Main.d.dealOffTheTopOfDeck();// discard
		cardsOnTable[0] = Main.d.dealOffTheTopOfDeck();// the flop
		cardsOnTable[1] = Main.d.dealOffTheTopOfDeck();
		cardsOnTable[2] = Main.d.dealOffTheTopOfDeck();

	}

	// good
	public int getPreviousPlayersBet(int relativeTo) {
		if (relativeTo == 4) {
			if (player[3].isFold() == false) {
				return player[3].getPlayersBet();
			} else if (player[2].isFold() == false) {
				return player[2].getPlayersBet();
			} else if (player[1].isFold() == false) {
				return player[1].getPlayersBet();
			} else if (player[0].isFold() == false) {
				return player[0].getPlayersBet();
			}
		} else if (relativeTo == 3) {
			if (player[2].isFold() == false) {
				return player[2].getPlayersBet();
			} else if (player[1].isFold() == false) {
				return player[1].getPlayersBet();
			} else if (player[0].isFold() == false) {
				return player[0].getPlayersBet();
			} else if (player[4].isFold() == false) {
				return player[4].getPlayersBet();
			}
		} else if (relativeTo == 2) {
			if (player[1].isFold() == false) {
				return player[1].getPlayersBet();
			} else if (player[0].isFold() == false) {
				return player[0].getPlayersBet();
			} else if (player[4].isFold() == false) {
				return player[4].getPlayersBet();
			} else if (player[3].isFold() == false) {
				return player[3].getPlayersBet();
			}
		} else if (relativeTo == 1) {
			if (player[0].isFold() == false) {
				return player[0].getPlayersBet();
			} else if (player[4].isFold() == false) {
				return player[4].getPlayersBet();
			} else if (player[3].isFold() == false) {
				return player[3].getPlayersBet();
			} else if (player[2].isFold() == false) {
				return player[2].getPlayersBet();
			}
		} else if (relativeTo == 0) {
			if (player[4].isFold() == false) {
				return player[4].getPlayersBet();
			} else if (player[3].isFold() == false) {
				return player[3].getPlayersBet();
			} else if (player[2].isFold() == false) {
				return player[2].getPlayersBet();
			} else if (player[1].isFold() == false) {
				return player[1].getPlayersBet();
			}
		}
		return -1;
	}

	public String getWinner() {
		UIGame = new BoardGame();
		Hand[] h = new Hand[5];
		for (int i = 0; i < 5; i++) {
			h[i] = player[i].getBestHand();
		}
		int index = getIndexOfWinner(h);
		Card c = new Card();
						
		player[index].setChips(player[index].getChips() + getPot());
		return "Player: " + index + " WON\nRank: " + "\n"
				+ player[index].getBestHand().toString();
	}

	public boolean checkIfBetsAreTheSame() {
		// betsAreTheSameCheck = true;
		int sum = 0;
		int activeP = 0;
		for (int i = 0; i < 5; i++) {
			if (player[i].isFold() == false) {
				sum += player[i].getPlayersBet();
				activeP = i;
			}
		}
		if ((sum / (5 - getFoldCount())) == player[activeP].getPlayersBet()) {
			//System.out.println("Bets are the same");
			return true;
		}
		return false;
	}

	public static void initiateTurn() {

		Main.d.dealOffTheTopOfDeck();// discard
		cardsOnTable[3] = Main.d.dealOffTheTopOfDeck();
	}

	public static void initiateRiver() {

		Main.d.dealOffTheTopOfDeck();// discard
		cardsOnTable[4] = Main.d.dealOffTheTopOfDeck();
	}
	public Player[] newGame() {
		
		dealCards();
		//Main.table = new Table();
		//Main.table.setPot(0);
		
		Main.EveryOneFolded = false;
		player[0].setHuman(true);
		player[1].setHuman(false);
		player[2].setHuman(false);
		player[3].setHuman(false);
		player[4].setHuman(false);
		
		for (int j = 0; j < 5; j++) {
			player[j].setFold(false);
			player[j].setBestHand(null);
		}
		
		return player;
	}

	public void initialize(){
		//player = new Player[MAX_OF_PLAYERS];

	// List of Names
		 String[] listOfNames={ "Michael", "Bob" , "Trent", "Hanna", 
				 				"Panjoong", "George", "Jack", "Tom", 
				 				"Lisa","Eliza", "Charlie", "Sarah", 
				 				"Shindy", "Monette", "Raymond", "Cris",
				 				"Danny", "Gem", "Kelsey", "Bryan", "Daryl"};
		 
	// Shuffle the List of names (no repeats)
		 Collections.shuffle(Arrays.asList(listOfNames));
		  
		for(int i=0; i < player.length; i++){
			String name = listOfNames[i];
			player[i]= new Player();
			player[i].setPlayersName(name); // assign a random of names with no duplicates
		}
	//	 return players;
		
	}

	public int getActiveCardsOnTable() {
		if (cardsOnTable[0].getSuit() == 0 && cardsOnTable[0].getRank() == 0) {
			return 0;
		} else if (cardsOnTable[3].getRank() == 0
				&& cardsOnTable[3].getSuit() == 0) {
			return 3;
		} else if (cardsOnTable[4].getRank() == 0
				&& cardsOnTable[4].getSuit() == 0) {
			return 4;
		} else {
			return 5;
		}
	}

	// Returns tablesPot
	public int getPot() {
		return tablesPot;
	}

	// setting pot to a value without adding
	public void setPot(int setPotValue) {
		this.tablesPot = setPotValue;

	}

	// adding players chips to pot
	public void addTablesPot(int addingPlayersChips) {
		this.tablesPot += addingPlayersChips;
	}

	// returns the amount of active players
	public int getActivePlayers() {
		return activePlayers;
	}

	public Player getPlayer(int i) {
		return player[i];
	}

	public void setPlayer(int i, Player p) {
		player[i] = p;
	}

	public void printTable(int howManyTableCards) {
		if (howManyTableCards == 0) {// no cards
			System.out.println("               P3: "
					+ player[3].getCard(0).toReadableString() + " , "
					+ player[3].getCard(1).toReadableString());
			System.out.println("              _____________");
			System.out.println("P2: " + player[2].getCard(0).toReadableString()
					+ " , " + player[2].getCard(1).toReadableString()
					+ "                              P4: "
					+ player[4].getCard(0).toReadableString() + " , "
					+ player[4].getCard(1).toReadableString());
			System.out.println("              -------------\n");
			System.out.println("         P1: "
					+ player[1].getCard(0).toReadableString() + " , "
					+ player[1].getCard(1).toReadableString()
					+ "            P0: "
					+ player[0].getCard(0).toReadableString() + " , "
					+ player[0].getCard(1).toReadableString());

		} else if (howManyTableCards == 1) {// three cards

			System.out.println("               P3: "
					+ player[3].getCard(0).toReadableString() + " , "
					+ player[3].getCard(1).toReadableString());
			System.out.println("              _____________");
			System.out.println("P2: " + player[2].getCard(0).toReadableString()
					+ " , " + player[2].getCard(1).toReadableString()
					+ "      " + cardsOnTable[0].toReadableString() + ", "
					+ cardsOnTable[1].toReadableString() + ", "
					+ cardsOnTable[2].toReadableString()
					+ "                  P4: "
					+ player[4].getCard(0).toReadableString() + " , "
					+ player[4].getCard(1).toReadableString());
			System.out.println("              -------------\n");
			System.out.println("         P1: "
					+ player[1].getCard(0).toReadableString() + " , "
					+ player[1].getCard(1).toReadableString()
					+ "           P0: "
					+ player[0].getCard(0).toReadableString() + " , "
					+ player[0].getCard(1).toReadableString());

		} else if (howManyTableCards == 2) {// four cards
			System.out.println("                P3: "
					+ player[3].getCard(0).toReadableString() + " , "
					+ player[3].getCard(1).toReadableString());
			System.out.println("              _____________");
			System.out.println("P2: " + player[2].getCard(0).toReadableString()
					+ " , " + player[2].getCard(1).toReadableString()
					+ "      " + cardsOnTable[0].toReadableString() + ", "
					+ cardsOnTable[1].toReadableString() + ", "
					+ cardsOnTable[2].toReadableString() + ", "
					+ cardsOnTable[3].toReadableString()
					+ "                  P4: "
					+ player[4].getCard(0).toReadableString() + " , "
					+ player[4].getCard(1).toReadableString());
			System.out.println("              -------------\n");
			System.out.println("         P1: "
					+ player[1].getCard(0).toReadableString() + " , "
					+ player[1].getCard(1).toReadableString()
					+ "            P0: "
					+ player[0].getCard(0).toReadableString() + " , "
					+ player[0].getCard(1).toReadableString());
		} else if (howManyTableCards == 3) {// five cards
			System.out.println("                P3: "
					+ player[3].getCard(0).toReadableString() + " , "
					+ player[3].getCard(1).toReadableString());
			System.out.println("              _____________");
			System.out.println("P2: " + player[2].getCard(0).toReadableString()
					+ " , " + player[2].getCard(1).toReadableString()
					+ "      " + cardsOnTable[0].toReadableString() + ", "
					+ cardsOnTable[1].toReadableString() + ", "
					+ cardsOnTable[2].toReadableString() + ", "
					+ cardsOnTable[3].toReadableString() + ", "
					+ cardsOnTable[4].toReadableString()
					+ "                 P4: "
					+ player[4].getCard(0).toReadableString() + " , "
					+ player[4].getCard(1).toReadableString());
			System.out.println("              -------------\n");
			System.out.println("         P1: "
					+ player[1].getCard(0).toReadableString() + " , "
					+ player[1].getCard(1).toReadableString()
					+ "            P0: "
					+ player[0].getCard(0).toReadableString() + " , "
					+ player[0].getCard(1).toReadableString());
		}
		System.out.println("Pot: " + this.getPot() + "\nPLayer 0: "
				+ player[0].getPlayersBet() + getFoldedString(0)
				+ "\nPLayer 1: " + player[1].getPlayersBet()
				+ getFoldedString(1)+" "+player[1].getCurrentRR()+" " + "\nPLayer 2: "
				+ player[2].getPlayersBet() + getFoldedString(2)+" "+player[2].getCurrentRR()+" "
				+ "\nPLayer 3: " + player[3].getPlayersBet()
				+ getFoldedString(3) + " "+player[3].getCurrentRR()+" "+ "\nPLayer 4: "
				+ player[4].getPlayersBet() + getFoldedString(4)+" "+player[4].getCurrentRR()+" ");
	}

	public String getFoldedString(int i) {
		if (player[i].isFold() == true) {
			return " Folded ";
		}
		return "";
	}

	public void setSmallAndBigBlind(int turn) {
		this.setPot(15);
		switch (turn) {
		case 0:
			player[1].setChips(player[1].getChips() - 5);
			player[2].setChips(player[2].getChips() - 10);
			player[1].setPlayersBet(5);
			player[2].setPlayersBet(10);
			// endingBetter = 2;
			break;
		case 1:
			player[2].setChips(player[2].getChips() - 5);
			player[3].setChips(player[3].getChips() - 10);
			player[2].setPlayersBet(5);
			player[3].setPlayersBet(10);
			// endingBetter = 3;
			break;
		case 2:
			player[3].setChips(player[3].getChips() - 5);
			player[4].setChips(player[4].getChips() - 10);
			player[3].setPlayersBet(5);
			player[4].setPlayersBet(10);
			// endingBetter = 4;
			break;
		case 3:
			player[4].setChips(player[4].getChips() - 5);
			player[0].setChips(player[1].getChips() - 10);
			player[4].setPlayersBet(5);
			player[0].setPlayersBet(10);
			// endingBetter = 0;
			break;
		case 4:
			player[0].setChips(player[1].getChips() - 5);
			player[1].setChips(player[2].getChips() - 10);
			player[0].setPlayersBet(5);
			player[1].setPlayersBet(10);
			// endingBetter = 1;
			break;
		}
		this.setCurrentBet(10);
	}

}
