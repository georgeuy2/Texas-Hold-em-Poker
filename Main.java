package texasholdem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Trent Robison
/*
 * -Things to add
 * -split the pot on a tie
 * -tie doesnt work
 */
public class Main {


	/**
	 * @param args
	 * 
	 */

	private static boolean gameInProgress = true;
	final static int AMOUNT_OF_PLAYERS = 5;
	static Table table;
	static Deck d;
	static  BoardGame UIGame;
	static int count = 0;
	static boolean EveryOneFolded;
	private static boolean playerCanRaise = true;
	private static int startMoney=0;
	private static JButton callButton;
	private static JButton raiseButton;
	private static JButton allInButton;
	private static JButton foldButton;
	
	private static JButton sliderButton;
	private static JButton fiveTimesRaiseButton;
	private static JButton threeTimesRaiseButton;
	private static JButton twoTimesRaiseButton;
	private static JSlider slider;
	private static JLabel displaySliderValue;
	
	private static int sliderValue=0;
	
	private static boolean foldButtonPressed=false;
	private static boolean callButtonPressed=false;
	private static boolean raiseButtonPressed=false;
	private static boolean allInButtonPressed=false;
	private static boolean twoTimesRaiseButtonPressed = false;
	private static boolean threeTimesRaiseButtonPressed = false;
	private static boolean fiveTimesRaiseButtonPressed = false;
	private static boolean sliderButtonPressed = false;
	private static boolean buttonPressed = false;
	/*
	 * 
	 * Main
	 */
	public static void game() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					
		    		System.out.println("Welcome to Texas Holdem Poker!");
		

		    		// players = new Player[AMOUNT_OF_PLAYERS];
		    		table = new Table();
		    		createBoardGame();
		    		UIGame.playBackGround();
		    		UIGame.setVisible(true);
		    		UIGame.setLocationRelativeTo(null);
		    try{
		     new Thread(new Runnable() {
		    	  public void run() {
		    		  
		    		try {
	    			    Thread.sleep(800);
	    			} catch(InterruptedException ex) {
	    			    Thread.currentThread().interrupt();
	    			}

		    		firstTimeInitialize();
		    		
		    		int round = 0;
		    		while (gameInProgress == true) {
		    			if(round == 5){
		    				round = 0;
		    			}
		    			
		    			UIGame.clearFolds();
		    			table.newGame();


		    			clearEveryonesBets();
		    			table.setSmallAndBigBlind(round);
		    			UIGame.playsSmallBigBlindSound();
		    			UIGame.updateDealerChip(round);
		    			UIGame.updatePlayers(table);

		    			UIGame.dealCards();
		    			// 6 sec delay for the cards to deal
		    			try {
		    			    Thread.sleep(4000);
		    			} catch(InterruptedException ex) {
		    			    Thread.currentThread().interrupt();
		    			}
		    			UIGame.updatePlayersCards(table);

		    			
		    			if(!EveryOneFolded)gameEngine2(round, 0);

		    			table.initiateFlop();
		    			UIGame.playsGrabChipsSound();
		    			clearEveryonesBets();

		    			UIGame.updateInitiateFlop(table.cardsOnTable);
		    			UIGame.updatePlayers(table);
		    		
		    			if(!EveryOneFolded)gameEngine2(round, 3);

		    			table.initiateTurn();
		    			clearEveryonesBets();
		    			UIGame.playsGrabChipsSound();
		    			
		    			UIGame.updateFourthTableCard(table.cardsOnTable);
		    			UIGame.updatePlayers(table);
		    			
		    			if(!EveryOneFolded)gameEngine2(round, 4);

		    			table.initiateRiver();
		    			clearEveryonesBets();
		    			UIGame.playsGrabChipsSound();
		    			
		    			UIGame.updateRiverCard(table.cardsOnTable);
		    			UIGame.updatePlayers(table);
		    			
		    			if(!EveryOneFolded)gameEngine2(round, 5);

		    			setPlayersHands();
		    			UIGame.flipOtherPlayerCards(table);
		    			
		    	
		    			
		    			System.out.println(table.getWinner());
		    			
		    			
		    			UIGame.returnCards();
		    			try {
		    			    Thread.sleep(3000);
		    			} catch(InterruptedException ex) {
		    			    Thread.currentThread().interrupt();
		    			}
		    			clearEveryonesBets();
		    			
		    			
		    	
		    			UIGame.updatePlayers(table);

		    			round++;
		    		}
		    		
		    		//Put code here to close window. That is why game seems like it
		    		//is not ending

//		    		 AITest();
		    	//	cardTest();
		           	
		      }
		    }).start();         

		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
			} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}
	

	/**
	 * @wbp.parser.entryPoint
	 */
	private static void createBoardGame(){
		UIGame = new BoardGame();
		
		slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
	
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		displaySliderValue = new JLabel("");
		displaySliderValue.setHorizontalAlignment(SwingConstants.CENTER);

		sliderButton = new JButton("Bet");
		fiveTimesRaiseButton = new JButton("5X");
		threeTimesRaiseButton = new JButton("3X");
		twoTimesRaiseButton = new JButton("2X");
		foldButton = new JButton("Fold");
		raiseButton = new JButton("Raise");
		callButton = new JButton("Call/Check");
		allInButton = new JButton("All In");

		foldButton.setEnabled(false);
		raiseButton.setEnabled(false);
		allInButton.setEnabled(false);
		callButton.setEnabled(false);

		fiveTimesRaiseButton.setEnabled(false);
		threeTimesRaiseButton.setEnabled(false);
		twoTimesRaiseButton.setEnabled(false);

		UIGame.addHumanPanel(allInButton);
		UIGame.addHumanPanel(foldButton);
		UIGame.addHumanPanel(callButton);
		UIGame.addHumanPanel(raiseButton);
		UIGame.addRaiseButtonPanel(slider);
		UIGame.addDisplaySliderPanel(displaySliderValue );
		UIGame.addDisplaySliderPanel(sliderButton);
		UIGame.addPanel(fiveTimesRaiseButton);
		UIGame.addPanel(threeTimesRaiseButton);
		UIGame.addPanel(twoTimesRaiseButton);


		
	}
	
	private static void resetBooleans(){
		foldButtonPressed=false;
		callButtonPressed=false;
		buttonPressed=false;
		raiseButtonPressed=false;
		sliderButtonPressed = false;
		threeTimesRaiseButtonPressed = false;
		fiveTimesRaiseButtonPressed = false;
		twoTimesRaiseButtonPressed = false;
	}

	private static void setSliderValue(int value){
		sliderValue = value;
	}
	
	private static int getSliderValue(){
		return sliderValue;
	}
	
	public void setStartMoney(int money){
		this.startMoney = money;
	}
	
	public static int getStartMoney(){
		return startMoney;
	}
	
	public static void cardTest(){
		Deck d = new Deck();
		for(int i = 0; i < 52; i++){
			Card c = d.dealOffTheTopOfDeck();
			System.out.println(i+" "+c+" "+c.getRank());
		}
	}
	public static void firstTimeInitialize(){
		table.initialize();
		if( getStartMoney() == 0){
			for(int i = 0; i < 5; i++ ){
				table.player[i].setChips(5000);
			}
		}
		else{
			for(int i = 0; i < 5; i++ ){
				table.player[i].setChips( getStartMoney() );
			}
		}
		
		
	}

	public static void newRound(){
		clearEveryonesBets();
		table.dealCards();
	}
	public static void AITest() {
		for(int i = 0; i < 100; i++){
		Deck d = new Deck();
		d.shuffleDeck();

		Card[] c = new Card[3];
		c[0] = d.dealOffTheTopOfDeck();
		c[1] = d.dealOffTheTopOfDeck();
		c[2] = d.dealOffTheTopOfDeck();
		//c[3] = d.dealOffTheTopOfDeck();
		// c[4] = d.dealOffTheTopOfDeck();
		// c[5] = d.dealOffTheTopOfDeck();
		// c[6] = d.dealOffTheTopOfDeck();
		Player p = new Player();
		// p.setPlayersBet(10);
		AI ai = new AI(p,1);
		System.out.println(c[0].toReadableString()+"  "+c[1].toReadableString()+"  "+c[2].toReadableString());
		System.out.println( ai.aiBetCallFold(c, 0 , 10));
		}
	}

	public static void setPlayersHands() {
		for (int i = 0; i < 5; i++) {
			Card[] c = new Card[7];
			c[0] = table.cardsOnTable[0];
			c[1] = table.cardsOnTable[1];
			c[2] = table.cardsOnTable[2];
			c[3] = table.cardsOnTable[3];
			c[4] = table.cardsOnTable[4];
			c[5] = table.player[i].getCard(0);
			c[6] = table.player[i].getCard(1);
			table.player[i].getGoodHand(c);
			
		}
	}
	


	public static int getEndingBetter(int dealer) {
		if (dealer == 0) {
			if (table.player[2].isFold() == false) {
				return 2;
			} else if (table.player[3].isFold() == false) {
				return 3;
			} else if (table.player[4].isFold() == false) {
				return 4;
			} else if (table.player[0].isFold() == false) {
				return 0;
			} else if (table.player[1].isFold() == false) {
				return 1;
			}
		} else if (dealer == 1) {
			if (table.player[3].isFold() == false) {
				return 3;
			} else if (table.player[4].isFold() == false) {
				return 4;
			} else if (table.player[0].isFold() == false) {
				return 0;
			} else if (table.player[1].isFold() == false) {
				return 1;
			} else if (table.player[2].isFold() == false) {
				return 2;
			}
		} else if (dealer == 2) {
			if (table.player[4].isFold() == false) {
				return 4;
			} else if (table.player[0].isFold() == false) {
				return 0;
			} else if (table.player[1].isFold() == false) {
				return 1;
			} else if (table.player[2].isFold() == false) {
				return 2;
			} else if (table.player[3].isFold() == false) {
				return 3;
			}
		} else if (dealer == 3) {
			if (table.player[0].isFold() == false) {
				return 0;
			} else if (table.player[1].isFold() == false) {
				return 1;
			} else if (table.player[2].isFold() == false) {
				return 2;
			} else if (table.player[3].isFold() == false) {
				return 3;
			} else if (table.player[4].isFold() == false) {
				return 4;
			}
		} else if (dealer == 4) {
			if (table.player[1].isFold() == false) {
				return 1;
			} else if (table.player[2].isFold() == false) {
				return 2;
			} else if (table.player[3].isFold() == false) {
				return 3;
			} else if (table.player[4].isFold() == false) {
				return 4;
			} else if (table.player[0].isFold() == false) {
				return 0;
			}
		}
		return -1;
	}

	public static void gameEngine2(int dealer, int cards) {
		
		while (true) {
			if (dealer == 0) {
				betCheckOrFold(3, cards);
				betCheckOrFold(4, cards);
				betCheckOrFold(0, cards);
				betCheckOrFold(1, cards);
				betCheckOrFold(2, cards);
				
				if (table.player[getEndingBetter(dealer)].getPlayersBet() == table
						.getPreviousPlayersBet(2)
						&& table.checkIfBetsAreTheSame()) {
					break;
				} else {
					playerCanRaise = false;
					betCheckOrFold(3, cards);
					betCheckOrFold(4, cards);
					betCheckOrFold(0, cards);
					betCheckOrFold(1, cards);
					playerCanRaise = true;
					break;
				}
			} else if (dealer == 1) {
				betCheckOrFold(4, cards);
				betCheckOrFold(0, cards);
				betCheckOrFold(1, cards);
				betCheckOrFold(2, cards);
				betCheckOrFold(3, cards);
				if (table.player[getEndingBetter(dealer)].getPlayersBet() == table
						.getPreviousPlayersBet(3)
						&& table.checkIfBetsAreTheSame()) {
					break;
				} else {
					playerCanRaise = false;
					betCheckOrFold(4, cards);
					betCheckOrFold(0, cards);
					betCheckOrFold(1, cards);
					betCheckOrFold(2, cards);
					playerCanRaise = true;
					break;
				}
			} else if (dealer == 2) {
				betCheckOrFold(0, cards);
				betCheckOrFold(1, cards);
				betCheckOrFold(2, cards);
				betCheckOrFold(3, cards);
				betCheckOrFold(4, cards);
				if (table.player[getEndingBetter(dealer)].getPlayersBet() == table
						.getPreviousPlayersBet(4)
						&& table.checkIfBetsAreTheSame()) {
					break;
				} else {
					playerCanRaise = false;
					betCheckOrFold(0, cards);
					betCheckOrFold(1, cards);
					betCheckOrFold(2, cards);
					betCheckOrFold(3, cards);
					playerCanRaise = true;
					break;
				}
			} else if (dealer == 3) {
				betCheckOrFold(1, cards);
				betCheckOrFold(2, cards);
				betCheckOrFold(3, cards);
				betCheckOrFold(4, cards);
				betCheckOrFold(0, cards);
				if (table.player[getEndingBetter(dealer)].getPlayersBet() == table
						.getPreviousPlayersBet(0)
						&& table.checkIfBetsAreTheSame()) {
					break;
				} else {
					playerCanRaise = false;
					betCheckOrFold(1, cards);
					betCheckOrFold(2, cards);
					betCheckOrFold(3, cards);
					betCheckOrFold(4, cards);
					playerCanRaise = true;
					break;
				}
			} else if (dealer == 4) {
				betCheckOrFold(2, cards);
				betCheckOrFold(3, cards);
				betCheckOrFold(4, cards);
				betCheckOrFold(0, cards);
				betCheckOrFold(1, cards);
				if (table.player[getEndingBetter(dealer)].getPlayersBet() == table
						.getPreviousPlayersBet(1)
						&& table.checkIfBetsAreTheSame()) {
					break;
				} else {
					playerCanRaise = false;
					betCheckOrFold(2, cards);
					betCheckOrFold(3, cards);
					betCheckOrFold(4, cards);
					betCheckOrFold(0, cards);
					playerCanRaise = true;
					break;
				}
			}
		}
	}

	public static void clearEveryonesBets() {
		for (int i = 0; i < AMOUNT_OF_PLAYERS; i++) {
			table.player[i].setPlayersBet(0);
		}
		table.setCurrentBet(0);
	}

	public static boolean nextRound() {
		for (int i = 0; i < AMOUNT_OF_PLAYERS; i++) {
			if (table.player[i].getPlayersBet() != table.getCurrentBet())
				return false;
		}
		return true;
	}

	/*
	 * Create a deck deal each player 2 cards
	 */
	public static void printPlayersBet() {
		for (int i = 0; i < AMOUNT_OF_PLAYERS; i++) {
			System.out.println("player " + i + ": "
					+ table.player[i].getPlayersBet());
		}
	}

	/*
	 * Blinds are setup to the left of the dealer
	 */

	public static void betCheckOrFold(final int player, int howManyCards) {
		UIGame.updatePlayerTurn(player);
		
		int tempBet;
		EveryOneFolded = table.getFoldCountBool();
		if(EveryOneFolded)return;
		if (table.player[player].isFold() == false) {
			switch (howManyCards) {
			case 0:
				table.printTable(0);
				break;
			case 3:
				table.printTable(1);
				break;
			case 4:
				table.printTable(2);
				break;
			case 5:
				table.printTable(3);
				break;
			}
		}
		while (true) {
			if (table.player[player].isFold() == true) {
				break;
			}
///////////////////////////////////////////////////////////////////AI///////////////////////

			if (table.player[player].isHuman() == false) {
				System.out.println();
				System.out.println("AI " + player + " - Thinking");
				UIGame.aiThinking(player);
				
				AI ai = new AI(table.player[player],1);
				Card[] testCards = new Card[table.getActiveCardsOnTable()];
				testCards = ai.getTestCards();
				int aiChoice = ai.aiBetCallFold(testCards,table.getPot(),(table.getCurrentBet()==0)? 5: table.getCurrentBet() );
				table.player[player].setCurrentRR(ai.rateOfReturn(testCards, table.getPot(),table.getCurrentBet()));
				UIGame.updateAIPercentDisplay(table.player[player].getCurrentRR(), player);
				
				/*
				 * Fold 
				 */
				if (aiChoice == -1) {
					if (table.player[player].getPlayersBet() == table
							.getCurrentBet()) {// you can check no point in
												// folding
						System.out.println("AI - checked no point in folding");
						break;
					}
					System.out.println("AI - Folded");
					table.player[player].setFold(true);
					UIGame.foldCards(player);
					try {
					    Thread.sleep(3000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					break;
				/*
				 * Call	
				 */
				} else if (aiChoice == 0) {// call bet
					if (table.player[player].getPlayersBet() < table
							.getCurrentBet()) {
						if (table.player[player].getChips() < table
								.getCurrentBet()) {
							System.out
									.println("AI - doesn't have enough money!");
							System.out.println("AI - must fold!");
							table.player[player].setFold(true);
							break;
						} else {
							table.setPot(table.getPot() + table.getCurrentBet()
									- table.player[player].getPlayersBet());
							table.player[player].setChips(table.player[player]
									.getChips()
									- table.getCurrentBet()
									+ table.player[player].getPlayersBet());
							table.player[player].setPlayersBet(table
									.getCurrentBet());
							UIGame.playsBettingSound();
							UIGame.updatePlayers(table);
							
							System.out.println("AI - called");
							break;
						}
					} else {
						System.out.println("AI - checked");
						break;
					}
				} else if (aiChoice >= 1) {
					/*
					 * Not enough money. Decrease bet until player has enough
					 * money.
					 */
					if (aiChoice > table.player[player].getChips()) {
						int b = aiChoice;
						while (b > 0) {
							b--;
							if (b == 0) {
								System.out.println("AI-not enough money");
								System.out.println("AI-automatically folds");
								table.player[player].setFold(true);
								break;
							}
							if (b < table.player[player].getChips()) {
								table.setCurrentBet(aiChoice
										+ table.player[player].getPlayersBet());
								table.player[player]
										.setChips(table.player[player]
												.getChips() - aiChoice);
								table.player[player]
										.setPlayersBet(table.player[player]
												.getPlayersBet() + aiChoice);
								table.setPot(table.getPot() + aiChoice);
								System.out.println("AI - bet "
										+ table.getCurrentBet());
								UIGame.playsBettingSound();
								UIGame.updatePlayers(table);
								
								break;
							}
						}

					} else if (aiChoice + table.player[player].getPlayersBet() == table
							.getCurrentBet()) {
						table.setCurrentBet(aiChoice
								+ table.player[player].getPlayersBet());
						table.player[player].setChips(table.player[player]
								.getChips() - aiChoice);
						table.player[player].setPlayersBet(table.player[player]
								.getPlayersBet() + aiChoice);
						table.setPot(table.getPot() + aiChoice);
						System.out.println("AI - bet " + table.getCurrentBet());
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
						
						break;
					} else if (aiChoice + table.player[player].getPlayersBet() > table
							.getCurrentBet() && playerCanRaise) {
						table.setCurrentBet(aiChoice
								+ table.player[player].getPlayersBet());
						table.player[player].setChips(table.player[player]
								.getChips() - aiChoice);
						table.player[player].setPlayersBet(table.player[player]
								.getPlayersBet() + aiChoice);
						table.setPot(table.getPot() + aiChoice);
						System.out.println("AI - raised "
								+ table.getCurrentBet());
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
						
						break;
					/*
					 * Tried to raise but has to call instead	
					 */
					} else if (playerCanRaise == false) {
						System.out.println("AI - turn to bet is over");
						System.out.println("AI - Call Instead");
						
							if (table.player[player].getChips() < table
									.getCurrentBet()) {
								System.out
										.println("AI - doesn't have enough money!");
								System.out.println("AI - must fold!");
								table.player[player].setFold(true);
								break;
							} else {
								table.setPot(table.getPot() + table.getCurrentBet()
										- table.player[player].getPlayersBet());
								table.player[player].setChips(table.player[player]
										.getChips()
										- table.getCurrentBet()
										+ table.player[player].getPlayersBet());
								table.player[player].setPlayersBet(table
										.getCurrentBet());
								System.out.println("AI - called");
								UIGame.playsBettingSound();
								UIGame.updatePlayers(table);
								
								break;
							}
						
					} else {
						/*
						 * Increment AI's bet until he can call
						 */
						System.out.println("AI - must bet more");
						int tBet = aiChoice;
						while (true) {
							if (tBet < table.getCurrentBet()) {
								tBet++;
								if (tBet > table.player[player].getChips()) {
									System.out.println("AI-not enough money");
									System.out
											.println("AI-automatically folds");
									table.player[player].setFold(true);
									break;
								}
							} else if (tBet == table.getCurrentBet()) {
								table.setCurrentBet(aiChoice
										+ table.player[player].getPlayersBet());
								table.player[player]
										.setChips(table.player[player]
												.getChips() - aiChoice);
								table.player[player]
										.setPlayersBet(table.player[player]
												.getPlayersBet() + aiChoice);
								table.setPot(table.getPot() + aiChoice);
								System.out.println("AI - raised "
										+ table.getCurrentBet());
								UIGame.playsBettingSound();
								UIGame.updatePlayers(table);
								
							}
						}
						// break;
					}
					// System.out.println("bettingRound 0");
				/*
				 * Something went wrong fold
				 */
				}else{
					System.out.println("AI is confused - Folded");
					table.player[player].setFold(true);
					UIGame.foldCards(player);
					try {
					    Thread.sleep(3000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					break;
				}

/////////////////////////////////////////////////////////////////////HUMAN/////////////////////////////////
			} else if (table.player[player].isHuman()) {
				resetBooleans();
				System.out.println("================================ Player Can RAise: " + playerCanRaise );
				System.out.println("================================ foldButton: " + foldButtonPressed );

				int tickSpacing = (int) (table.player[player].getChips()*0.2);
				
				slider.setMinimum(table.getCurrentBet());
				slider.setMaximum((int) (table.player[player].getChips()*0.2));
				slider.setLabelTable(slider.createStandardLabels((int) ((table.player[player].getChips()*0.2)/2) ));
				slider.setMajorTickSpacing(tickSpacing);
				
				foldButton.setEnabled(true);
				
				if (playerCanRaise == false){
					callButton.setText("Call");		
					//raiseButton.setEnabled(true);
					callButton.setEnabled(true);
				}
				
				else if(table.player[player].getChips() >= table.getCurrentBet() && table.getCurrentBet()==0 && playerCanRaise == true){
					callButton.setText("Check");
					callButton.setEnabled(true);
					allInButton.setEnabled( true);
					if( playerCanRaise ==true ){
					raiseButton.setEnabled(true);
					}	
				}
				
				else if (table.player[player].getChips() >= table.getCurrentBet() && table.getCurrentBet()>0 && playerCanRaise==true)
				{
					callButton.setText("Call1");
					callButton.setEnabled(true);
					allInButton.setEnabled( true);
					if( playerCanRaise == true ){
						raiseButton.setEnabled(true);
						}					
					}
				
		
				
				
				while(buttonPressed!=true){
				
				// Fold Button
					class foldAction implements ActionListener{
						public void actionPerformed( ActionEvent e){
							buttonPressed=true;
							foldButtonPressed=true;
						}
					}
					
					foldButton.addActionListener(new foldAction() );
					
				// Call Button
					class callAction implements ActionListener{
						public void actionPerformed( ActionEvent e){
							buttonPressed=true;
							callButtonPressed=true;
						}
					}
					
					callButton.addActionListener(new callAction() );
					
					
				// All In Button
					class allInAction implements ActionListener{
						public void actionPerformed( ActionEvent e){
							buttonPressed=true;
							allInButtonPressed=true;
						}
					}
					
					allInButton.addActionListener(new allInAction() );
				
				// Raise Button
					class raiseAction implements ActionListener{
						public void actionPerformed( ActionEvent e){
							buttonPressed=true;
							raiseButtonPressed=true;
						}
					}
					
					raiseButton.addActionListener(new raiseAction() );
				}
			
				
				if(foldButtonPressed==true){
					System.out.println("You Folded");
					table.player[player].setFold(true);
					UIGame.foldCards(player);
					try {
					    Thread.sleep(3000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					
					}
				}
				
				else if(callButtonPressed == true){
						tempBet = table.getCurrentBet();
						table.player[player].setChips( table.player[player].getChips() - tempBet );
						table.player[player].setPlayersBet(tempBet);
						table.setPot(table.getPot() + tempBet);
						System.out.println("You Called: " + tempBet);
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);	
				}
				
				else if( allInButtonPressed == true){
					int allIn=table.player[player].getChips()+ table.player[player].getPlayersBet();
					System.out.println("You called ALL IN!");
					table.player[player].setPlayersBet( allIn );
					table.setPot(table.getPot() + table.player[player].getChips());
					table.setCurrentBet(allIn);
					table.player[player].setChips(0);
					UIGame.playsBettingSound();
					UIGame.updatePlayers(table);
				}
				
				else if(raiseButtonPressed == true){
					UIGame.showPanels();
					
					System.out.println("2 times: Chips=" + table.player[player].getChips() + " currentBet: " +(table.getCurrentBet()*2)  );
					System.out.println("3 times: Chips=" + table.player[player].getChips() + " currentBet: " +(table.getCurrentBet()*3)  );
					System.out.println("5 times: Chips=" + table.player[player].getChips() + " currentBet: " +(table.getCurrentBet()*5)  );
					
				if(table.player[player].getChips() != 0 && table.getCurrentBet() != 0){
					if(table.player[player].getChips() >= (table.getCurrentBet()*2) ){
						twoTimesRaiseButton.setEnabled(true);
					}
					
					if(table.player[player].getChips() >= (table.getCurrentBet()*3) ){
						threeTimesRaiseButton.setEnabled(true);
					}
					
					if( table.player[player].getChips() >= (table.getCurrentBet()*3) ){
						fiveTimesRaiseButton.setEnabled(true);
					}
					
				}
				
				else if(table.player[player].getChips() == 0){
					twoTimesRaiseButton.setEnabled(false);
					threeTimesRaiseButton.setEnabled(false);
					fiveTimesRaiseButton.setEnabled(false);

				}
					while(raiseButtonPressed!=false){
					
				
						class twoTimesAction implements ActionListener{
							public void actionPerformed( ActionEvent e){
								raiseButtonPressed=false;
								twoTimesRaiseButtonPressed = true;
							}
						}
					
						twoTimesRaiseButton.addActionListener(new twoTimesAction() );
						
						class threeTimesAction implements ActionListener{
							public void actionPerformed( ActionEvent e){
								raiseButtonPressed=false;
								threeTimesRaiseButtonPressed = true;
							}
						}
					
						threeTimesRaiseButton.addActionListener(new threeTimesAction() );
					
						class fiveTimesAction implements ActionListener{
							public void actionPerformed( ActionEvent e){
								raiseButtonPressed=false;
								fiveTimesRaiseButtonPressed = true;
							}
						}
						
						fiveTimesRaiseButton.addActionListener(new fiveTimesAction() );

						class sliderAction implements ChangeListener{
							public void stateChanged( ChangeEvent e){
								setSliderValue(slider.getValue());
								displaySliderValue.setText("" + getSliderValue());								
							}
						}
						
						slider.addChangeListener( new sliderAction() );
					
					
							
							class sliderButtonAction implements ActionListener{
								public void actionPerformed( ActionEvent e){
									raiseButtonPressed=false;
									sliderButtonPressed = true;
								}	
							}
						sliderButton.addActionListener(new sliderButtonAction() );

						
						
					}
				
					
					if( twoTimesRaiseButtonPressed == true){

						tempBet = table.getCurrentBet() * 2;
						System.out.println("You raise chips: " + tempBet);

						table.player[player].setChips( table.player[player].getChips() - tempBet );
						table.player[player].setPlayersBet(tempBet);
						table.setPot(table.getPot() + tempBet);
						table.setCurrentBet(tempBet);
						
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
					}
					
					else if( threeTimesRaiseButtonPressed == true){
						tempBet = table.getCurrentBet() * 3;
						System.out.println("You raise chips: " + tempBet);

						table.player[player].setChips( table.player[player].getChips() - tempBet );
						table.player[player].setPlayersBet(tempBet);
						table.setPot(table.getPot() + tempBet);
						table.setCurrentBet(tempBet);

						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
					}
					
					else if( fiveTimesRaiseButtonPressed == true){
						tempBet = table.getCurrentBet() * 5;
						System.out.println("You raise chips: " + tempBet);

						table.player[player].setChips( table.player[player].getChips() - tempBet);
						table.player[player].setPlayersBet(tempBet);
						table.setPot(table.getPot() + tempBet);
						table.setCurrentBet(tempBet);

						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
					}
					
					else if( sliderButtonPressed == true){
						System.out.println("=============== " + getSliderValue());
						int value= getSliderValue();
						System.out.println("You bet: " + value);
						
						table.player[player].setChips( table.player[player].getChips() - value );
						table.player[player].setPlayersBet(value);
						table.setPot(table.getPot() + value);
						table.setCurrentBet(table.getCurrentBet() + value);
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
					}
					
					
				}
				
				
				resetBooleans();
				fiveTimesRaiseButton.setEnabled(false);
				threeTimesRaiseButton.setEnabled(false);
				twoTimesRaiseButton.setEnabled(false);
				raiseButton.setEnabled(false);
				callButton.setEnabled(false);
				foldButton.setEnabled(false);
				allInButton.setEnabled(false);
				UIGame.hidePanels();
				
				break;
				
				
			/*
				System.out.println("PLAYER " + player
						+ " : turn\nCheck = 'c', Fold = 'f', Bet >= 1: ");

				Scanner a = new Scanner(System.in);
				String choice = a.next();

				if (choice.equals("f")) {
					System.out.println("You Folded");
					table.player[player].setFold(true);
					UIGame.foldCards(player);
					try {
					    Thread.sleep(3000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					break;
				} else if (choice.equals("c")) {
					if (table.player[player].getPlayersBet() < table
							.getCurrentBet()) {
						System.out.println("You can't check!");
					} else {
						System.out.println("You checked");
						break;
					}
				} else if (Integer.valueOf(choice) >= 1) {
					tempBet = Integer.valueOf(choice);
					if (tempBet > table.player[player].getChips()) {
						System.out.println("You dont have that much money");
					} else if (tempBet + table.player[player].getPlayersBet() == table
							.getCurrentBet()) {
						table.setCurrentBet(tempBet
								+ table.player[player].getPlayersBet());
						table.player[player].setChips(table.player[player]
								.getChips() - tempBet);
						table.player[player].setPlayersBet(table.player[player]
								.getPlayersBet() + tempBet);
						table.setPot(table.getPot() + tempBet);
						System.out.println("You bet " + table.getCurrentBet());
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
						
						break;
					} else if (tempBet + table.player[player].getPlayersBet() > table
							.getCurrentBet() && playerCanRaise) {
						table.setCurrentBet(tempBet
								+ table.player[player].getPlayersBet());
						table.player[player].setChips(table.player[player]
								.getChips() - tempBet);
						table.player[player].setPlayersBet(table.player[player]
								.getPlayersBet() + tempBet);
						table.setPot(table.getPot() + tempBet);
						System.out.println("You bet " + table.getCurrentBet());
						UIGame.playsBettingSound();
						UIGame.updatePlayers(table);
						
						break;
					} else if (playerCanRaise == false) {
						System.out.println("Your turn to bet is over");
					} else {
						System.out.println("You must bet more");
					}
					System.out.println("bettingRound 0");
				}
			*/	
				
				
				
				
			}
			// /////////////////////////////////////////HUMAN////////////////////////////
		}
	}

}
