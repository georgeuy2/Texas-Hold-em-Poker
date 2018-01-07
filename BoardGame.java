/*
		George Uy de Ong II
*/
package texasholdem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;




public class BoardGame extends JFrame implements ActionListener{

	private static JPanel contentPane;

	private final int FIXED_XCORD = 222;
	private final int FIXED_YCORD = 290;

	private ImageIcon pokerTableImage;
	private ImageIcon dealerChipImage;
	private ImageIcon backCardImage;
	
	private JLabel labelTimer;

	private JLabel player2Name;
	private JLabel player3Name;
	private JLabel player4Name;
	private JLabel player5Name;
	
	private JLabel displayTableCards_1;
	private JLabel displayTableCards_2;
	private JLabel displayTableCards_3;
	private JLabel displayTableCards_4;
	private JLabel displayTableCards_5;
	
	private JLabel displayPlayerTwoCard_1;
	private JLabel displayPlayerTwoCard_2;
	private JLabel displayPlayerThreeCard_1;
	private JLabel displayPlayerThreeCard_2;
	private JLabel displayPlayerFourCard_1;
	private JLabel displayPlayerFourCard_2;
	private JLabel displayPlayerFiveCard_1;
	private JLabel displayPlayerFiveCard_2;
	private JLabel displayHumanPlayerCard_1;
	private JLabel displayHumanPlayerCard_2;
	
	private JLabel displayHumanPlayersMoney;
	private JLabel displayPlayer_2Money;
	private JLabel displayPlayer_3Money;
	private JLabel displayPlayer_4Money;
	private JLabel displayPlayer_5Money;
	
	private JLabel displayDealerChip_5;
	private JLabel displayDealerChip_4;
	private	JLabel displayDealerChip_3;
	private JLabel displayDealerChip_2;
	private JLabel displayDealerChip_Human;
	
	private JLabel displayTablesPot;
	
	private JLabel displayPlayer_2Bet;
	private JLabel displayPlayer_3Bet;
	private JLabel displayPlayer_4Bet;
	private JLabel displayPlayer_5Bet;
	private JLabel displayHumanPlayerBet;
	
	private JButton pauseButton;

	private Table table = new Table();
	private JPanel player5Turn;
	private JPanel player4Turn;
	private JPanel player2Turn;
	private JPanel player3Turn;
	private JPanel humanPlayerTurn;
	private JLabel labelDeck;
	private Timer timer = new Timer(0,this);
	
	private int xCord = 222;
	private int yCord = 290;
	private int xCord2 = 222;
	private int yCord2 = 290;
	
	private int xCord5_1=222;
	private int yCord5_1=290;
	private int xCord5_2=222;
	private int yCord5_2=290;
	
	private int xCord3_1=222;
	private int yCord3_1=290;
	private int xCord3_2=222;
	private int yCord3_2=290;
	
	private int xCord2_1=222;
	private int yCord2_1=290;
	private int xCord2_2=222;
	private int yCord2_2=290;
	
	private int xCord1_1=222;
	private int yCord1_1=290;
	private int xCord1_2=222;
	private int yCord1_2=290;
	
	private int xCordT1=222;
	private int yCordT1=290;
	
	private int xCordT2=222;
	private int yCordT2=290;

	private int xCordT3=222;
	private int yCordT3=290;
	
	private int xCordT4=222;
	private int yCordT4=290;	
	
	private int xCordT5=222;
	private int yCordT5=290;

	private int xCordPlayer3_1 = 76;
	private int yCordPlayer3_1 = 124;
	private int xCordPlayer3_2 = 150;
	private int yCordPlayer3_2= 124;
	
	private int xCordPlayer2_1 = 76;
	private int yCordPlayer2_1 = 430;
	private int xCordPlayer2_2 = 152;
	private int yCordPlayer2_2= 430;
	
	private int xCordPlayer4_1 = 735;
	private int yCordPlayer4_1 = 124;
	private int xCordPlayer4_2 = 814;
	private int yCordPlayer4_2= 124;
	
	private int xCordPlayer5_1 = 735;
	private int yCordPlayer5_1 = 450;
	private int xCordPlayer5_2 = 814;
	private int yCordPlayer5_2= 450;
	
	private int xCordHumanPlayer_1 = 402;
	private int yCordHumanPlayer_1 = 574;
	private int xCordHumanPlayer_2 = 478;
	private int yCordHumanPlayer_2= 574;
	
	boolean dealCards=false;
	boolean returnCards=false;
	boolean player1= false;
	boolean player2= false;
	boolean player3= false;
	boolean player4= false;
	boolean player5= false;
	
	boolean player1Fold= false;
	boolean player2Fold= false;
	boolean player3Fold= false;
	boolean player4Fold= false;
	boolean player5Fold= false;
	
	boolean p1FoldOnce= false;
	boolean p2FoldOnce= false;
	boolean p3FoldOnce= false;
	boolean p4FoldOnce= false;
	boolean p5FoldOnce= false;
	
	boolean firstTimeFlipCards=false;
	
	boolean table1=false;
	boolean table2=false;
	boolean table3=false;
	boolean table4=false;
	boolean table5=false;
	boolean fold = false;

	boolean didPlayerFold=false;

	private int velX=1;
	private int velY=1;
	
	
// loud sound clips	
	Clip bgm=loadClip("SuperMarioTheme.wav");
		
	Clip bet=loadClip("chipsHandle6.wav");
	Clip smallBlind=loadClip("chipsHandle1.wav");
	Clip bigBlind=loadClip("chipsHandle3.wav");
	Clip grabChips=loadClip("chipsHandle5.wav");

	Clip winner = loadClip("KidsCheering.wav");
	
	Clip flip=loadClip("flip.wav");
	Clip flip1=loadClip("cardSlide1.wav");
	Clip flip2=loadClip("cardSlide2.wav");
	Clip flip3=loadClip("cardSlide3.wav");
	Clip flip4=loadClip("cardSlide4.wav");

	Clip fold1 = loadClip("cardShove1.wav");
	Clip fold2 = loadClip("cardShove2.wav");
	
	Clip dealCard1=loadClip("cardPlace1.wav");
	Clip dealCard2 = loadClip("cardPlace2.wav");
	Clip dealCard3 = loadClip("cardPlace3.wav");
	Clip dealCard4 = loadClip("cardPlace4.wav");
	
	java.util.Timer clock;
	task task;
	//ptask ptask;
	java.util.Timer clock2;
	private JLabel ai2Thinking;
	private JLabel ai3Thinking;
	private JLabel ai1Thinking;
	private JLabel ai4Thinking;
	private JPanel HumanPanel;
	private JPanel raiseButtonPanel;
	private JPanel panel;
	private JPanel DisplaySliderPanel;
	private JLabel totalChips;
	private JLabel displayAI4Percent;
	private JLabel displayAI3Percent;
	private JLabel displayAI2Percent;
	private JLabel displayAI1Percent;
	private JLabel displayWinningChanceLabel1;
	private JLabel displayWinningChanceLabel2;
	private JLabel displayWinningChanceLabel3;
	private JLabel displayWinningChanceLabel4;

	/*

	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	public void init(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("icons/)
		}catch(IOException e){
			e.printStackTrace();
		}
	}
*/
	
	
	
// ============================== TEST GAME ==============================
	public void updateAIPercentDisplay(float percent, int player){
		double x=Double.parseDouble(Float.toString(percent));
		DecimalFormat currencyFormat = new DecimalFormat("###.##");
		
		switch(player){
		case 1: displayAI1Percent.setText( currencyFormat.format(x) );
				break;
				
		case 2: displayAI2Percent.setText( currencyFormat.format(x) );
				break;
		
		case 3: displayAI3Percent.setText( currencyFormat.format(x) );
				break;
		
		case 4: displayAI4Percent.setText( currencyFormat.format(x) );
				break;
		}

	}

	public void addHumanPanel(JButton button){
		HumanPanel.add(button);
	}
	
	public void addRaiseButtonPanel(JSlider slider){
		raiseButtonPanel.add(slider, BorderLayout.CENTER);
	}
	
	public void addDisplaySliderPanel(JLabel label){
		DisplaySliderPanel.add(label);
	}
	
	public void addDisplaySliderPanel(JButton button){
		DisplaySliderPanel.add(button);
	}
	
	public void addPanel(JButton button){
		panel.add(button);
	}
	
	public void aiThinking(int player){
		try {
			Thread.sleep(125);
	
		switch(player){
		case 1: ai1Thinking.setText(".");
				Thread.sleep(300);
				ai1Thinking.setText(". .");
				Thread.sleep(300);
				ai1Thinking.setText(". . .");
				Thread.sleep(100);
				ai1Thinking.setText("");
				break;
		
		case 2: ai2Thinking.setText(".");
				Thread.sleep(300);
				ai2Thinking.setText(". .");
				Thread.sleep(300);
				ai2Thinking.setText(". . .");
				Thread.sleep(100);
				ai2Thinking.setText("");
				break;
		
		case 3:	ai3Thinking.setText(".");
				Thread.sleep(300);
				ai3Thinking.setText(". .");
				Thread.sleep(300);
				ai3Thinking.setText(". . .");
				Thread.sleep(100);
				ai3Thinking.setText("");
				break;
		
		case 4: ai4Thinking.setText(".");
				Thread.sleep(300);
				ai4Thinking.setText(". .");
				Thread.sleep(300);
				ai4Thinking.setText(". . .");
				Thread.sleep(100);
				ai4Thinking.setText("");
				break;
		}
		
		} catch (InterruptedException e) {}	
	}
	
	public void flipOtherPlayerCards(Table t){
		if( t.player[1].isFold() == false ){
			playClip(flip1);
			// Player 2
				displayPlayerTwoCard_1.setIcon(t.player[1].getCard(0).getImage());
				displayPlayerTwoCard_2.setIcon(t.player[1].getCard(1).getImage());
		}
		if( t.player[2].isFold() == false ){
			// Player 3
			playClip(flip2);
				displayPlayerThreeCard_1.setIcon(t.player[2].getCard(0).getImage());
				displayPlayerThreeCard_2.setIcon(t.player[2].getCard(1).getImage());
		}
		if( t.player[3].isFold() == false ){
				// Player 4	
			playClip(flip3);
				displayPlayerFourCard_1.setIcon(t.player[3].getCard(0).getImage());
				displayPlayerFourCard_2.setIcon(t.player[3].getCard(1).getImage());
		}
		if( t.player[4].isFold() == false ){
			// player 5
			playClip(flip4);
				displayPlayerFiveCard_1.setIcon(t.player[4].getCard(0).getImage());
				displayPlayerFiveCard_2.setIcon(t.player[4].getCard(1).getImage());
		}
	}

	private ImageIcon getDealerChipImage(){
		dealerChipImage = new ImageIcon( getClass().getResource("Icons/DealerChip.png") );
		Card c = new Card();
		Image tempImage = dealerChipImage.getImage();
		dealerChipImage=c.getResizeImageCopy(tempImage, 50,50);
		return dealerChipImage;
	}

	private ImageIcon getBackCardImage(){
		Card c = new Card();
		ImageIcon tempIcon = c.getCardImage(11, false);
		Image tempImage = tempIcon.getImage();
		tempIcon=c.getResizeImageCopy(tempImage,80,120);
		return tempIcon;
	}
		
	public void showPanels(){
		raiseButtonPanel.setVisible(true);
		DisplaySliderPanel.setVisible(true);
	}
	
	
	public void hidePanels(){
		raiseButtonPanel.setVisible(false);
		DisplaySliderPanel.setVisible(false);
	}
	
	public void updatePlayers(Table t){
		int total = 0;
		for(int i =0; i <=4; i++){
			total +=t.player[i].getChips();
		}
	//	total += t.getPot(); 
		//totalChips.setText("Total Chips: " + total);

		displayTablesPot.setText("$ " + t.getPot());
		
		player2Name.setText(t.player[1].getPlayersName());
		player3Name.setText(t.player[2].getPlayersName());
		player4Name.setText(t.player[3].getPlayersName());
		player5Name.setText(t.player[4].getPlayersName());
		
		displayPlayer_2Bet.setText("Bet: $" + t.player[1].getPlayersBet());
		displayPlayer_3Bet.setText("Bet: $" + t.player[2].getPlayersBet());
		displayPlayer_4Bet.setText("Bet: $" + t.player[3].getPlayersBet());
		displayPlayer_5Bet.setText("Bet: $" + t.player[4].getPlayersBet());
		displayHumanPlayerBet.setText("Bet: $" + t.player[0].getPlayersBet());
		
		displayPlayer_2Money.setText("$" + t.player[1].getChips());
		displayPlayer_3Money.setText("$" + t.player[2].getChips());
		displayPlayer_4Money.setText("$" + t.player[3].getChips());
		displayPlayer_5Money.setText("$" + t.player[4].getChips());
		displayHumanPlayersMoney.setText("$" + t.player[0].getChips());
	}

	public void updatePlayerTurn(int playerTurn){
				
		switch(playerTurn){
		
		case 0: humanPlayerTurn.setVisible(true);
				player2Turn.setVisible(false);
				player3Turn.setVisible(false);
				player4Turn.setVisible(false);
				player5Turn.setVisible(false);
				break;
		
		case 1: humanPlayerTurn.setVisible(false);
				player2Turn.setVisible(true);
				player3Turn.setVisible(false);
				player4Turn.setVisible(false);
				player5Turn.setVisible(false);
				break;
				
		case 2: player2Turn.setVisible(false);
				humanPlayerTurn.setVisible(false);
				player3Turn.setVisible(true);
				player4Turn.setVisible(false);
				player5Turn.setVisible(false);
				break;
				
		case 3: player2Turn.setVisible(false);
				humanPlayerTurn.setVisible(false);
				player3Turn.setVisible(false);
				player4Turn.setVisible(true);
				player5Turn.setVisible(false);
				break;
				
		case 4: player2Turn.setVisible(false);
				humanPlayerTurn.setVisible(false);
				player3Turn.setVisible(false);
				player4Turn.setVisible(false);
				player5Turn.setVisible(true);
				break;
		}
	}
	
	public void setCardsToDeck(){
	
		int xCord = 222;
		int yCord = 290;
		int width = 80;
		int height = 120;
		
		displayTableCards_1.setBounds(xCord, yCord,width, height);
		displayTableCards_2.setBounds(xCord, yCord,width, height);
		displayTableCards_3.setBounds(xCord, yCord,width, height);
		displayTableCards_4.setBounds(xCord, yCord,width, height);
		displayTableCards_5.setBounds(xCord, yCord,width, height);
		displayPlayerTwoCard_1.setBounds(xCord, yCord,width, height);
		displayPlayerTwoCard_2.setBounds(xCord, yCord,width, height);
	
		displayPlayerThreeCard_1.setBounds(xCord, yCord,width, height);
		displayPlayerThreeCard_2.setBounds(xCord, yCord,width, height);
	
		displayPlayerFourCard_1.setBounds(xCord, yCord,width, height);
		displayPlayerFourCard_2.setBounds(xCord, yCord,width, height);
	
		displayPlayerFiveCard_1.setBounds(xCord, yCord,width, height);
		displayPlayerFiveCard_2.setBounds(xCord, yCord,width, height);
		displayHumanPlayerCard_1.setBounds(xCord, yCord,width, height);
		displayHumanPlayerCard_2.setBounds(xCord, yCord,width, height);
	}
	
	public void dealCards(){
		dealCards=true;
		player1 = true;
		 xCord = 222;
		 yCord = 290;
		 xCord2 = 222;
		 yCord2 = 290;
		
		 xCord5_1=222;
		yCord5_1=290;
		xCord5_2=222;
		 yCord5_2=290;
		
		 xCord3_1=222;
		yCord3_1=290;
		 xCord3_2=222;
		 yCord3_2=290;
		
		 xCord2_1=222;
		yCord2_1=290;
		 xCord2_2=222;
		yCord2_2=290;
		
		 xCord1_1=222;
		yCord1_1=290;
		xCord1_2=222;
		yCord1_2=290;
		
		 xCordT1=222;
		 yCordT1=290;
		
		xCordT2=222;
		 yCordT2=290;

		 xCordT3=222;
		 yCordT3=290;
		
		 xCordT4=222;
		yCordT4=290;	
		
		xCordT5=222;
		 yCordT5=290;

		timer.start();
	}
	
	public void returnCards(){
		playClip(flip);
		
		displayTableCards_1.setIcon(getBackCardImage());
		displayTableCards_2.setIcon(getBackCardImage());
		displayTableCards_3.setIcon(getBackCardImage());
		displayTableCards_4.setIcon(getBackCardImage());
		displayTableCards_5.setIcon(getBackCardImage());
		displayPlayerTwoCard_1.setIcon(getBackCardImage());
		displayPlayerTwoCard_2.setIcon(getBackCardImage());
		playClip(flip1);

		displayPlayerThreeCard_1.setIcon(getBackCardImage());
		displayPlayerThreeCard_2.setIcon(getBackCardImage());
	
		displayPlayerFourCard_1.setIcon(getBackCardImage());
		displayPlayerFourCard_2.setIcon(getBackCardImage());
		playClip(flip2);

		displayPlayerFiveCard_1.setIcon(getBackCardImage());
		displayPlayerFiveCard_2.setIcon(getBackCardImage());
		displayHumanPlayerCard_1.setIcon(getBackCardImage());
		displayHumanPlayerCard_2.setIcon(getBackCardImage());

		returnCards=true;

		timer.start();
	}
	
	public void clearFolds(){
		xCordPlayer3_1 = 76;
		 yCordPlayer3_1 = 124;
		 xCordPlayer3_2 = 150;
		 yCordPlayer3_2= 124;
		
		 xCordPlayer2_1 = 76;
		 yCordPlayer2_1 = 430;
		xCordPlayer2_2 = 152;
		yCordPlayer2_2= 430;
		
		 xCordPlayer4_1 = 735;
		 yCordPlayer4_1 = 124;
		 xCordPlayer4_2 = 814;
		 yCordPlayer4_2= 124;
		
		 xCordPlayer5_1 = 735;
		 yCordPlayer5_1 = 450;
		 xCordPlayer5_2 = 814;
		 yCordPlayer5_2= 450;
		
		 xCordHumanPlayer_1 = 402;
		 yCordHumanPlayer_1 = 574;
		 xCordHumanPlayer_2 = 478;
		 yCordHumanPlayer_2= 574;
		player1Fold=false;
		player2Fold=false;
		player3Fold=false;
		player4Fold=false;
		player5Fold=false;
		p1FoldOnce= false;
		p2FoldOnce= false;
		p3FoldOnce= false;
		p4FoldOnce= false;
		p5FoldOnce= false;
		returnCards = false;
	}
	public void foldCards(int player){

		switch(player){
		case 0: player1Fold=true;
				player2Fold=false;
				player3Fold=false;
				player4Fold=false;
				player5Fold=false;
				playClip(fold1);
				break;
				
		case 1: player1Fold=false;
				player2Fold=true;
				player3Fold=false;
				player4Fold=false;
				player5Fold=false;
				playClip(fold2);
				break;
				
		case 2: player1Fold=false;
				player2Fold=false;
				player3Fold=true;
				player4Fold=false;
				player5Fold=false;
				playClip(fold1);
				break;
				
		case 3: player1Fold=false;
				player2Fold=false;
				player3Fold=false;
				player4Fold=true;
				player5Fold=false;
				playClip(fold2);
				break;
				
		case 4: player1Fold=false;
				player2Fold=false;
				player3Fold=false;
				player4Fold=false;
				player5Fold=true;
				playClip(fold1);
				break;
		}
		timer.start();
	}
	
	public void updatePlayersCards(Table t, int player){

		if(t.player[player].isFold()==true){
			switch(player){
				case 0 : displayHumanPlayerCard_1.setIcon(getBackCardImage());
						 displayHumanPlayerCard_2.setIcon(getBackCardImage());
						 break;
				case 1: displayPlayerTwoCard_1.setIcon(getBackCardImage());
						displayPlayerTwoCard_2.setIcon(getBackCardImage());
						break;
				case 2: displayPlayerThreeCard_1.setIcon(getBackCardImage());
						displayPlayerThreeCard_2.setIcon(getBackCardImage());
						break;
				case 3: displayPlayerFourCard_1.setIcon(getBackCardImage());
						displayPlayerFourCard_2.setIcon(getBackCardImage());
						break;
				case 4: displayPlayerFiveCard_1.setIcon(getBackCardImage());
						displayPlayerFiveCard_2.setIcon(getBackCardImage());
						break;
				}
		}
			
	}
	
	
	public void updatePlayersCards(Table t){
		
		displayPlayerTwoCard_1.setIcon(getBackCardImage());
		displayPlayerTwoCard_2.setIcon(getBackCardImage());
	
		displayPlayerThreeCard_1.setIcon(getBackCardImage());
		displayPlayerThreeCard_2.setIcon(getBackCardImage());
	
		displayPlayerFourCard_1.setIcon(getBackCardImage());
		displayPlayerFourCard_2.setIcon(getBackCardImage());
	
		displayPlayerFiveCard_1.setIcon(getBackCardImage());
		displayPlayerFiveCard_2.setIcon(getBackCardImage());
	// Human player
		playClip(flip);
		displayHumanPlayerCard_1.setIcon(t.player[0].getCard(0).getImage());
		displayHumanPlayerCard_2.setIcon(t.player[0].getCard(1).getImage());
				
	}
	
	
	
	public void updateInitiateFlop(Card[] cards){
		playClip(flip);
		displayTableCards_1.setIcon( cards[0].getImage());
		displayTableCards_2.setIcon(cards[1].getImage());
		displayTableCards_3.setIcon( cards[2].getImage());

	}
	
	public void updateFourthTableCard(Card[] cards){
		displayTableCards_4.setIcon( cards[3].getImage());
		playClip(flip);


	}
	public void updateRiverCard(Card[] cards){
		displayTableCards_5.setIcon( cards[4].getImage());
		playClip(flip);
	}

	public void updateDealerChip(int dealer){
		dealerChipImage=getDealerChipImage();

		switch(dealer){
		case 4: displayDealerChip_5.setIcon(dealerChipImage);
				displayDealerChip_4.setIcon(null);
				displayDealerChip_3.setIcon(null);
				displayDealerChip_2.setIcon(null);
				displayDealerChip_Human.setIcon(null);
				break;
		case 3: displayDealerChip_5.setIcon(null);
				displayDealerChip_4.setIcon(dealerChipImage);
				displayDealerChip_3.setIcon(null);
				displayDealerChip_2.setIcon(null);
				displayDealerChip_Human.setIcon(null);
				break;
		case 2:  displayDealerChip_5.setIcon(null);
				 displayDealerChip_4.setIcon(null);
				 displayDealerChip_3.setIcon(dealerChipImage);
				 displayDealerChip_2.setIcon(null);
				 displayDealerChip_Human.setIcon(null);
				 break;
		case 1: displayDealerChip_5.setIcon(null);
				displayDealerChip_4.setIcon(null);
				displayDealerChip_3.setIcon(null);
				displayDealerChip_2.setIcon(dealerChipImage);
				displayDealerChip_Human.setIcon(null);
				break;
		case 0:	displayDealerChip_5.setIcon(null);
				displayDealerChip_4.setIcon(null);
				displayDealerChip_3.setIcon(null);
				displayDealerChip_2.setIcon(null);
				displayDealerChip_Human.setIcon(dealerChipImage);
				break;
		
		default: System.out.println("Player " + dealer + " does not exitst!");
				 break;
		}
	}
	
	public void addTime(String time)
	{
		labelTimer.setText(time);
	}
	

	public void playsBettingSound(){
		playClip(bet);
	}
	
	public void playsSmallBigBlindSound(){
		playClip(smallBlind);
		playClip(bigBlind);
	}
	
	public void playsGrabChipsSound(){
		playClip(grabChips);
	}

	public void playWinnerSound(){
		playClip(winner);
	}

	public void playBackGround(){
		loopClip(bgm);
	}
	
	public BoardGame() {
		clock = new java.util.Timer();
		clock2=new java.util.Timer();
		task = new task();
		//ptask=new ptask();

		clock.scheduleAtFixedRate(task, 1000,1000);
		//clock2.scheduleAtFixedRate(ptask,1000,1000);
		
		//ptask.setPlayerPause(true);
		
		setResizable(false);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 840);
		
// Menu bar which has File, Setting, Help
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		
		JMenuItem mntmEndGame = new JMenuItem("End Game");
		mnFile.add(mntmEndGame);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowToPlay = new JMenuItem("How to Play");
		mnHelp.add(mntmHowToPlay);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
	// ======= Locating Images in any computer ========
	
		pokerTableImage = new ImageIcon( getClass().getResource("Icons/pokertable.png") );
	
	
		
		
// Main Menu Button		
		pauseButton = new JButton("Pause");
		pauseButton.setBackground(Color.WHITE);
		pauseButton.setForeground(Color.BLACK);
		pauseButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		pauseButton.setBounds(840, 0, 114, 34);
		contentPane.add(pauseButton);
		pauseButton.addActionListener(this);
		
		
				
		
		HumanPanel = new JPanel();
		HumanPanel.setBackground(Color.BLACK);
		HumanPanel.setBounds(138, 710, 702, 80);
		contentPane.add(HumanPanel);
		
				

// Displays Players name on each seat	
	final int MAX_WIDTH_LABEL = 100;
	final int MAX_HEIGHT_LABEL = 20;
		HumanPanel.setLayout(new GridLayout(0, 5, 0, 0));
		displayHumanPlayersMoney = new JLabel("$" );
		displayHumanPlayersMoney.setBounds(0, 627, 186, 93);
		displayHumanPlayersMoney.setForeground(Color.WHITE);
		displayHumanPlayersMoney.setHorizontalAlignment(SwingConstants.CENTER);
		displayHumanPlayersMoney.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 35));
		HumanPanel.add(displayHumanPlayersMoney);

	// Player 2 (Player[1]) 
		player2Name = new JLabel("Player 2");			// Player 2 name
		player2Name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		player2Name.setForeground(Color.YELLOW);
		player2Name.setBounds(54, 366, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(player2Name);
				
		
	// Player 3 (player[2])
		player3Name = new JLabel("Player 3" );			// Player 3 name
		player3Name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		player3Name.setForeground(Color.YELLOW);
		player3Name.setBounds(54, 75, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(player3Name);
	

	// Player 4 (player[3])
		player4Name = new JLabel("Player 4" );			// Player 4 name
		player4Name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		player4Name.setForeground( Color.YELLOW);
		player4Name.setBounds(811, 75, 90, 20);
		contentPane.add(player4Name);
	
	// Player 5 (player[4])
		player5Name = new JLabel( "Player 5" );			// Player 5 name
		player5Name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		player5Name.setForeground(Color.YELLOW);
		player5Name.setBounds(811, 366, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(player5Name);
	
// ===================== Label each players Money =====================

	
	// Players 2 (player[1]) Money
		displayPlayer_2Money = new JLabel("$" );
		displayPlayer_2Money.setIcon(null);
		displayPlayer_2Money.setForeground(Color.WHITE);
		displayPlayer_2Money.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_2Money.setLabelFor(player2Name);
		displayPlayer_2Money.setBounds(54, 394, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_2Money);
	
	// Player 3 (player[2]) money
		displayPlayer_3Money = new JLabel("$");
		displayPlayer_3Money.setForeground(Color.WHITE);
		displayPlayer_3Money.setIcon(null);
		displayPlayer_3Money.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_3Money.setLabelFor(player3Name);
		displayPlayer_3Money.setBounds(54, 94, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_3Money);
		
	// Player 4 (player[3]) money	
		displayPlayer_4Money = new JLabel("$");
		displayPlayer_4Money.setForeground(Color.WHITE);
		displayPlayer_4Money.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_4Money.setLabelFor(player4Name);
		displayPlayer_4Money.setBounds(811, 94, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_4Money);
		
	// Player 5 (player[4]) money
		displayPlayer_5Money = new JLabel("$");
		displayPlayer_5Money.setForeground(Color.WHITE);
		displayPlayer_5Money.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_5Money.setBackground(Color.WHITE);
		displayPlayer_5Money.setLabelFor(player5Name);
		displayPlayer_5Money.setBounds(811, 394, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_5Money);

// ======================== Display Bets ========================
		// Players 2 (player[1]) Money
		displayPlayer_2Bet = new JLabel("Bet: ");
		displayPlayer_2Bet.setIcon(null);
		displayPlayer_2Bet.setForeground(Color.WHITE);
		displayPlayer_2Bet.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_2Bet.setLabelFor(player2Name);
		displayPlayer_2Bet.setBounds(265, 430, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_2Bet);
		
		displayPlayer_3Bet = new JLabel("Bet: " );
		displayPlayer_3Bet.setIcon(null);
		displayPlayer_3Bet.setForeground(Color.WHITE);
		displayPlayer_3Bet.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_3Bet.setLabelFor(player3Name);
		displayPlayer_3Bet.setBounds(265, 234, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_3Bet);
		
		displayPlayer_4Bet = new JLabel("Bet: ");
		displayPlayer_4Bet.setIcon(null);
		displayPlayer_4Bet.setForeground(Color.WHITE);
		displayPlayer_4Bet.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_4Bet.setLabelFor(player4Name);
		displayPlayer_4Bet.setBounds(620, 234, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_4Bet);
		
		displayPlayer_5Bet = new JLabel("Bet: ");
		displayPlayer_5Bet.setIcon(null);
		displayPlayer_5Bet.setForeground(Color.WHITE);
		displayPlayer_5Bet.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayPlayer_5Bet.setLabelFor(player5Name);
		displayPlayer_5Bet.setBounds(620, 430, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(displayPlayer_5Bet);
		
		displayHumanPlayerBet = new JLabel("Bet: ");
		displayHumanPlayerBet.setIcon(null);
		displayHumanPlayerBet.setForeground(Color.WHITE);
		displayHumanPlayerBet.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayHumanPlayerBet.setBounds(430, 500, 115, 20);
		contentPane.add(displayHumanPlayerBet);
		
// ===================== Display Tables Pot =====================		
		JLabel tableTitle = new JLabel("Tables Pot");
		tableTitle.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		tableTitle.setForeground(Color.white);
		tableTitle.setBounds(445, 154, MAX_WIDTH_LABEL, MAX_HEIGHT_LABEL);
		contentPane.add(tableTitle);
		
		String displayTableMoney= String.valueOf( table.getPot() );
		displayTablesPot = new JLabel("$");
		displayTablesPot.setIcon(null);
		displayTablesPot.setForeground(Color.WHITE);
		displayTablesPot.setFont(new Font("American Typewriter", Font.PLAIN, 16));
		displayTablesPot.setBounds(445, 174, 114, 20);
		contentPane.add(displayTablesPot);
		
		
// ======== Dealer Chip rotations ===========
	
	// Player 5 dealer chip
		displayDealerChip_5 = new JLabel();
		displayDealerChip_5.setBounds(620, 380, 70, 70);
		displayDealerChip_5.setLabelFor(player5Name);
		displayDealerChip_5.setForeground( Color.WHITE );
		contentPane.add(displayDealerChip_5);
		
	// Player 4 dealer chip	
		displayDealerChip_4 = new JLabel();
		displayDealerChip_4.setBounds(620, 240, 70, 70);
		displayDealerChip_4.setLabelFor(player4Name);
		displayDealerChip_4.setForeground(Color.white);
		contentPane.add(displayDealerChip_4);
		
	// Player 3 dealer chip
		displayDealerChip_3 = new JLabel();
		displayDealerChip_3.setBounds(240, 240, 70, 70);
		displayDealerChip_3.setLabelFor(player3Name);
		displayDealerChip_3.setForeground(Color.white);
		contentPane.add(displayDealerChip_3);
		
	// Player 2 dealer chip
		displayDealerChip_2= new JLabel();
		displayDealerChip_2.setBounds(240,385, 70, 70);
		displayDealerChip_2.setLabelFor(player2Name);
		displayDealerChip_2.setForeground(Color.white);
		contentPane.add(displayDealerChip_2);
		
	// Human Player dealer chip
		displayDealerChip_Human = new JLabel();
		displayDealerChip_Human.setForeground(Color.white);
		displayDealerChip_Human.setBounds(450, 450, 70,70 );
		contentPane.add(displayDealerChip_Human);
		

			
// ======================== Display cards ========================
		Card c = new Card();	
		int cardWidth= c.getCardImageWidth();
		int cardHeight=c.getCardImageHeight();
		int backCardWidht= 80;
		int backCardHeight = 120;
		final int setHeight=285;
		backCardImage = c.getCardImage(11, false);
		Image tempImage = backCardImage.getImage();
		backCardImage=c.getResizeImageCopy(tempImage,80,120);
		
  //============== Table cards==============
		// First card on the table
		displayTableCards_1 = new JLabel();
		displayTableCards_1.setBounds(FIXED_XCORD, FIXED_YCORD, 68, 110);
		displayTableCards_1.setIcon(backCardImage);
		displayTableCards_1.setVisible(true);
		contentPane.add(displayTableCards_1);
		
	// Second card on the table
		displayTableCards_2 = new JLabel();
		displayTableCards_2.setBounds(FIXED_XCORD, FIXED_YCORD, cardWidth, cardHeight);
		displayTableCards_2.setIcon(backCardImage);
		displayTableCards_2.setVisible(true);
		contentPane.add(displayTableCards_2);
	
	// Third card on the table
		displayTableCards_3 = new JLabel();
		displayTableCards_3.setBounds(FIXED_XCORD, FIXED_YCORD, cardWidth, cardHeight);
		displayTableCards_3.setIcon(backCardImage);
		displayTableCards_3.setVisible(true);
		contentPane.add(displayTableCards_3);
		
	// Fourth card on the table
		displayTableCards_4 = new JLabel();
		displayTableCards_4.setBounds(FIXED_XCORD, FIXED_YCORD, cardWidth, cardHeight);
		displayTableCards_4.setIcon(backCardImage);
		displayTableCards_4.setVisible(true);
		contentPane.add(displayTableCards_4);
		
	// Fifth card on the table
		displayTableCards_5 = new JLabel();
		displayTableCards_5.setBounds(FIXED_XCORD, FIXED_YCORD, cardWidth, cardHeight);
		displayTableCards_5.setIcon(backCardImage);
		displayTableCards_5.setVisible(true);
		contentPane.add(displayTableCards_5);
		
		labelDeck = new JLabel(backCardImage);
		labelDeck.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeck.setBounds(222, 290, 80, 120);
		contentPane.add(labelDeck);
		
  //============== Player Cards ==============
		
	// Player 2
		displayPlayerTwoCard_1 = new JLabel(backCardImage);
		displayPlayerTwoCard_1.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerTwoCard_1.setLabelFor(player2Name);
		contentPane.add(displayPlayerTwoCard_1);
		
		displayPlayerTwoCard_2 = new JLabel(backCardImage);
		displayPlayerTwoCard_2.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerTwoCard_2.setLabelFor(player2Name);
		contentPane.add(displayPlayerTwoCard_2);
		
	// Player 3
		displayPlayerThreeCard_1 = new JLabel(backCardImage);
		displayPlayerThreeCard_1.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerThreeCard_1.setLabelFor(player3Name);
		contentPane.add(displayPlayerThreeCard_1);
		
		displayPlayerThreeCard_2 = new JLabel(backCardImage);
		displayPlayerThreeCard_2.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerThreeCard_2.setLabelFor(player3Name);
		contentPane.add(displayPlayerThreeCard_2);
		
	// Player 4
		displayPlayerFourCard_1 = new JLabel(backCardImage);
		displayPlayerFourCard_1.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerFourCard_1.setLabelFor(player4Name);
		contentPane.add(displayPlayerFourCard_1);
		
		displayPlayerFourCard_2 = new JLabel(backCardImage); 
		displayPlayerFourCard_2.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerFourCard_2.setLabelFor(player4Name);
		contentPane.add(displayPlayerFourCard_2);
		
	// Player 5
		displayPlayerFiveCard_1 = new JLabel(backCardImage);
		displayPlayerFiveCard_1.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerFiveCard_1.setLabelFor(player5Name);
		contentPane.add(displayPlayerFiveCard_1);
		
		displayPlayerFiveCard_2 = new JLabel(backCardImage);
		displayPlayerFiveCard_2.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		displayPlayerFiveCard_2.setLabelFor(player5Name);
		contentPane.add(displayPlayerFiveCard_2);
		
	// Human Player
		displayHumanPlayerCard_1 = new JLabel(backCardImage);
		displayHumanPlayerCard_1.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		contentPane.add(displayHumanPlayerCard_1);
		
		displayHumanPlayerCard_2 = new JLabel(backCardImage);
		displayHumanPlayerCard_2.setBounds(FIXED_XCORD,FIXED_YCORD, cardWidth, cardHeight);
		contentPane.add(displayHumanPlayerCard_2);
		
	// Here is the label timer
		labelTimer = new JLabel("Timer");
		labelTimer.setBackground(Color.BLACK);
		labelTimer.setFont(new Font("Tahoma", Font.BOLD, 28));
		labelTimer.setHorizontalAlignment(SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setBounds(380, 0, 200, 46);
		contentPane.add(labelTimer);
		

		player3Turn = new JPanel();
		player3Turn.setBackground(new Color(13,166,69));
		player3Turn.setBounds(42, 75, 114, 46);
		player3Turn.setVisible(false);
		contentPane.add(player3Turn);
		
		player2Turn = new JPanel();
		player2Turn.setBackground(new Color(13,166,69));
		player2Turn.setBounds(42, 366, 114, 53);
		player2Turn.setVisible(false);
		contentPane.add(player2Turn);
		
		player4Turn = new JPanel();
		player4Turn.setBackground(new Color(13,166,69));
		player4Turn.setBounds(797, 75, 114, 46);
		player4Turn.setVisible(false);
		contentPane.add(player4Turn);
		
		player5Turn = new JPanel();
		player5Turn.setBackground(new Color(13,166,69));
		player5Turn.setBounds(797, 366, 114, 53);
		player5Turn.setVisible(false);
		contentPane.add(player5Turn);
		
		humanPlayerTurn = new JPanel();
		humanPlayerTurn.setBackground(new Color(13,166,69));
		humanPlayerTurn.setBounds(393, 571, 177, 126);
		humanPlayerTurn.setVisible(false);
		contentPane.add(humanPlayerTurn);
		
		ai1Thinking = new JLabel("");
		ai1Thinking.setForeground(Color.WHITE);
		ai1Thinking.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		ai1Thinking.setBounds(93, 338, 61, 25);
		contentPane.add(ai1Thinking);
		
		ai2Thinking = new JLabel("");
		ai2Thinking.setForeground(Color.WHITE);
		ai2Thinking.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		ai2Thinking.setBounds(166, 78, 61, 25);
		contentPane.add(ai2Thinking);
		
		ai3Thinking = new JLabel("");
		ai3Thinking.setForeground(Color.WHITE);
		ai3Thinking.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		ai3Thinking.setBounds(724, 78, 61, 25);
		contentPane.add(ai3Thinking);
		
		ai4Thinking = new JLabel("");
		ai4Thinking.setForeground(Color.WHITE);
		ai4Thinking.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		ai4Thinking.setBounds(811, 338, 61, 25);
		contentPane.add(ai4Thinking);
		
		raiseButtonPanel = new JPanel();
		raiseButtonPanel.setBackground(new Color(192, 192, 192));
		raiseButtonPanel.setBounds(590, 519, 146, 190);
		raiseButtonPanel.setLayout(new BorderLayout(0, 0));
		raiseButtonPanel.setVisible(false);
	
		
	
		panel = new JPanel();
		raiseButtonPanel.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
			
		DisplaySliderPanel = new JPanel();
		DisplaySliderPanel.setBackground(new Color(192, 192, 192));
		DisplaySliderPanel.setBounds(735, 598, 100, 62);
		DisplaySliderPanel.setVisible(false);
		contentPane.add(DisplaySliderPanel);
		DisplaySliderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		
		contentPane.add(raiseButtonPanel);
		
		totalChips = new JLabel("");
		totalChips.setForeground(Color.WHITE);
		totalChips.setFont(new Font("Tahoma", Font.BOLD, 11));
		totalChips.setBounds(166, 650, 177, 46);
		contentPane.add(totalChips);
		
		
		displayWinningChanceLabel1 = new JLabel("Hand Strength: " );
		displayWinningChanceLabel1.setVerticalAlignment(SwingConstants.TOP);
		displayWinningChanceLabel1.setForeground(Color.WHITE);
		displayWinningChanceLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayWinningChanceLabel1.setBounds(20, 309, 125, 25);
		contentPane.add(displayWinningChanceLabel1);
		
		displayWinningChanceLabel2 = new JLabel("Hand Strength: " );
		displayWinningChanceLabel2.setVerticalAlignment(SwingConstants.TOP);
		displayWinningChanceLabel2.setForeground(Color.WHITE);
		displayWinningChanceLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayWinningChanceLabel2.setBounds(178, 49, 125, 18);
		contentPane.add(displayWinningChanceLabel2);
		
		displayWinningChanceLabel3 = new JLabel("Hand Strength: " );
		displayWinningChanceLabel3.setVerticalAlignment(SwingConstants.TOP);
		displayWinningChanceLabel3.setForeground(Color.WHITE);
		displayWinningChanceLabel3.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayWinningChanceLabel3.setBounds(665, 49, 125, 20);
		contentPane.add(displayWinningChanceLabel3);
		
		displayWinningChanceLabel4 = new JLabel("Hand Strength: " );
		displayWinningChanceLabel4.setVerticalAlignment(SwingConstants.TOP);
		displayWinningChanceLabel4.setForeground(Color.WHITE);
		displayWinningChanceLabel4.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayWinningChanceLabel4.setBounds(811, 314, 134, 20);
		contentPane.add(displayWinningChanceLabel4);
		
		displayAI1Percent = new JLabel("");
		displayAI1Percent.setHorizontalAlignment(SwingConstants.CENTER);
		displayAI1Percent.setForeground(Color.WHITE);
		displayAI1Percent.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayAI1Percent.setBounds(20, 329, 125, 34);
		contentPane.add(displayAI1Percent);
		
		displayAI2Percent = new JLabel("");
		displayAI2Percent.setHorizontalAlignment(SwingConstants.CENTER);
		displayAI2Percent.setForeground(Color.WHITE);
		displayAI2Percent.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayAI2Percent.setBounds(166, 64, 125, 50);
		contentPane.add(displayAI2Percent);
		
		displayAI3Percent = new JLabel("");
		displayAI3Percent.setHorizontalAlignment(SwingConstants.CENTER);
		displayAI3Percent.setForeground(Color.WHITE);
		displayAI3Percent.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayAI3Percent.setBounds(665, 70, 125, 44);
		contentPane.add(displayAI3Percent);
		
		displayAI4Percent = new JLabel("");
		displayAI4Percent.setHorizontalAlignment(SwingConstants.CENTER);
		displayAI4Percent.setForeground(Color.WHITE);
		displayAI4Percent.setFont(new Font("Tahoma", Font.BOLD, 12));
		displayAI4Percent.setBounds(811, 330, 134, 33);
		contentPane.add(displayAI4Percent);
		
		JLabel Table = new JLabel("");
		Table.setBounds(0, 0, 960, 720);
		Table.setIcon(pokerTableImage);
		contentPane.add(Table);
		

		
	
	
		
		
		
		
		


		
		
		//===================== Menu Bar Action Listener =============================
			
			// End Game Action Listener
			class endGameAction implements ActionListener{
				public void actionPerformed ( ActionEvent e){
					task.setpause(true);
					//ptask.setPlayerPause(true);
					int choice=JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?" , "Quit", JOptionPane.YES_NO_OPTION);
					
					if(choice ==0){
						 stopClip(bgm);
						MainMenu menu = new MainMenu();
						menu.main(null);
						dispose();	
					}
					else if( choice == 1){
						task.setpause(false);
						//ptask.setPlayerPause(false);
					}
				}
			}
			
			mntmEndGame.addActionListener(new endGameAction() );
		
		
			// New Game Action Listener
			class newGameAction implements ActionListener{
				public void actionPerformed( ActionEvent e){
					Main main = new Main();
					main.game();
					dispose();
					}
			}
			
			mntmNewGame.addActionListener(new newGameAction());
			
			// Quit Action Listener
			class quitAction implements ActionListener{
				public void actionPerformed( ActionEvent e){
					task.setpause(true);
				//	ptask.setPlayerPause(true);
					int choice=JOptionPane.showConfirmDialog(null,"Are you sure you want to quit the game?" , "Quit", JOptionPane.YES_NO_OPTION);
					
					if(choice ==0){
						stopClip(bgm);
						System.exit(0);
					}
					else if( choice == 1){
						task.setpause(false);
						//ptask.setPlayerPause(false);
					}
				}
			}
			
			mntmQuit.addActionListener(new quitAction() );
			
			// About Action Listener
			class aboutAction implements ActionListener{
				public void actionPerformed( ActionEvent e){
					final JFrame aboutFrame= new JFrame("About");
					JPanel aboutPanel= new JPanel();
					Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
					
					int CENTER_FRAME_WIDTH = aboutFrame.getSize().width;
					int CENTER_FRAME_HEIGHT= aboutFrame.getSize().height;

					int locationX= ((dim.width-CENTER_FRAME_WIDTH)/2)-140;
					int locationY= ((dim.height-CENTER_FRAME_HEIGHT)/2)-120;
													
					aboutFrame.setSize(300,200);
					
					JLabel aboutLabel= new JLabel("Texas Hold'em Poker");
					aboutLabel.setBounds(85, 20, 150, 50);
					
					JLabel aboutLabel_2= new JLabel("Version 1.0");
					aboutLabel_2.setBounds(110, 50, 100, 50);
					aboutPanel.setLayout(null);
					
					aboutFrame.getContentPane().add(aboutPanel);
					aboutPanel.add(aboutLabel);
					aboutPanel.add(aboutLabel_2);
					
					
					aboutFrame.setLocation(locationX, locationY);
					aboutFrame.setVisible(true);
					aboutFrame.setResizable(false);
					
// Ok Button in the about frame		
					JButton aboutButton = new JButton("OK");
					aboutButton.setBounds(120,110, 55, 20);
				// Action Listener for the ok button
					aboutButton.addActionListener( new ActionListener() {
					public void actionPerformed( ActionEvent e){
						// it will close the about frame
							aboutFrame.setVisible(false);
							aboutFrame.dispose();
						}
					});
					aboutPanel.add(aboutButton);
					
				}
			}
			
			mntmAbout.addActionListener( new aboutAction() );

			
} // ================= END OF BACKGROUND =================

// ============================= Methods =============================

	public void dealHumanCards(){
		// human player
					// Card 1
					if ( xCord1_1 >= 222 && xCord1_1 < 402 ){
						if(yCord1_1 >= 290 && yCord1_1 < 573 ){
						yCord1_1 += (velY+1);
						}
						xCord1_1 += (velX);
						displayHumanPlayerCard_1.setBounds(xCord1_1,yCord1_1, 80, 120);
					}
					// Card 2
					if ( yCord1_2 >= 290 && yCord1_2 < 573 ){
						if(xCord1_2 >= 222 && xCord1_2 < 477 ){
							xCord1_2 += (velX+1);
							}
						yCord1_2 += (velY+1);
						displayHumanPlayerCard_2.setBounds(xCord1_2,yCord1_2, 80, 120);
					}

					else if(xCord1_1 == 402 && yCord1_1 == 574 &&  xCord1_2 == 478 && yCord1_2 == 574){
						playClip(dealCard2);
						displayHumanPlayerCard_1.setBounds(402,574, 80, 120);
						displayHumanPlayerCard_2.setBounds(478,574, 80, 120);
						player1 = false;
						player2 = true;
						
					}
					 
	}
	
	private void dealPlayer2Cards(){
		// Card 1
					if( xCord2_1 <=222 && xCord2_1 > 76){
						if( yCord2_1 >= 285 && yCord2_1 <430){
							yCord2_1 += (velY+1);
						}
						xCord2_1 -= (velX+1);	
						displayPlayerTwoCard_1.setBounds(xCord2_1,yCord2_1, 80, 120);
					}

					// Card 2
					if( yCord2_2 >= 285 && yCord2_2 < 430 ){
						if( xCord2_2 <=222 && xCord2_2 > 150){
							xCord2_2 -= (velX);
						}
						yCord2_2 += (velY+1);
						displayPlayerTwoCard_2.setBounds(xCord2_2,yCord2_2, 80, 120);
					}
					
					else if(xCord2_1 == 76 && yCord2_1 == 430 && xCord2_2 == 152 && yCord2_2 == 430){
						playClip(dealCard2);
						displayPlayerTwoCard_1.setBounds(76,430, 80, 120);
						displayPlayerTwoCard_2.setBounds(152,430, 80, 120);
						player3 = true;
						player2 = false;
					}
	}
	
	private void dealPlayer3Cards(){
		// Player 3
		// Card 1
		if( yCord3_1 <=290 && yCord3_1 >= 125){
			yCord3_1 -= (velY+1);
			if(xCord3_1 <=222 && xCord3_1 > 76){
			xCord3_1 -= (velX+1);
			}
			displayPlayerThreeCard_1.setBounds( xCord3_1,yCord3_1, 80, 120);
		}
	
		// Card 2
		if( yCord3_2 <=290 && yCord3_2 >= 125){
			yCord3_2 -= (velY+1);
			if(xCord3_2 <=222 && xCord3_2 > 150){
			xCord3_2 -= velX;
			}
			displayPlayerThreeCard_2.setBounds( xCord3_2,yCord3_2, 80, 120);
		}
		else if(yCord3_1 ==124 && xCord3_1==76 && xCord3_2 == 150 && yCord3_2 == 124){
			playClip(dealCard3);
			displayPlayerThreeCard_1.setBounds( 76,124, 80, 120);
			displayPlayerThreeCard_2.setBounds( 150,124, 80, 120);
			player3=false;
			player4=true;
		}

	}
	
	private void dealPlayer4Cards(){
		
		// Card 1
		if(xCord >= 222 && xCord < 735){
			xCord += (velX+2);
			if(yCord <=290 && yCord > 124){
				yCord -= (velY+1);
			}
			displayPlayerFourCard_1.setBounds(xCord,yCord, 80, 120);
		}
		
		//Card 2
		if(xCord2 >=222 && xCord2 < 814){
			xCord2 += (velX+3);
			if(yCord2 <= 290 && yCord2 >124){
			yCord2 -= (velY+1);
			}
			displayPlayerFourCard_2.setBounds(xCord2,yCord2, 80, 120);
		}

		else if( xCord ==735 && yCord == 124 && xCord2 == 814 && yCord2 == 124){
			playClip(dealCard1);
			displayPlayerFourCard_1.setBounds(735,124, 80, 120);
			displayPlayerFourCard_2.setBounds(814,124, 80, 120);
			player4 = false;
			player5 =true;
		}

	}
	
	private void dealPlayer5Cards(){
		// Card 1
		if(xCord5_1 >= 222 && xCord5_1< 735){
			if(yCord5_1 >= 290 && yCord5_1< 450){
				yCord5_1 += velY;
			}
			xCord5_1 += (velX+2);
			displayPlayerFiveCard_1.setBounds(xCord5_1,yCord5_1, 80, 120);

		}
		
		// Card 2
		if(yCord5_2 >= 290 && yCord5_2< 450){
			if(xCord5_2 >= 222 && xCord5_2< 814){
				xCord5_2 += (velX+3);
			}	
			yCord5_2 += (velY);
			displayPlayerFiveCard_2.setBounds(xCord5_2,yCord5_2, 80, 120);
		}

		else if(xCord5_1 == 735 && yCord5_2 == 450 && xCord5_2 == 814  && yCord5_2 ==450){
			playClip(dealCard1);
			displayPlayerFiveCard_1.setBounds(735,450, 80, 120);
			displayPlayerFiveCard_2.setBounds(814,450, 80, 120);
			player5=false;
			table1=true;
			table2=true;
			table3=true;
			table4=true;
			table5=true;
		}

	}
	
	private void dealTable_1Cards(){
		if( xCordT1 >= 222 && xCordT1 < 305){
			xCordT1 += velX;
			displayTableCards_1.setBounds(xCordT1,285, 80, 120);
		}
		else if(xCordT1 == 305 ){
			playClip(dealCard2);
			table1=false;
		}
	//	System.out.println("Table 1: " + xCordT1);
	}
	
	private void dealTable_2Cards(){
		if( xCordT2 >= 222 && xCordT2 < 372){
			xCordT2 += velX;
			displayTableCards_2.setBounds(xCordT2,285, 80, 120);
		}
		else if(xCordT2 == 372 ){
			playClip(dealCard2);
			table2=false;
		}
	//	System.out.println("Table 2: " + xCordT2);

	}
	
	private void dealTable_3Cards(){
		if( xCordT3 >= 222 && xCordT3 < 439){
			xCordT3 += velX;
			displayTableCards_3.setBounds(xCordT3,285, 80, 120);
		}
		else if(xCordT3 == 439 ){
			playClip(dealCard2);
			table3=false;
		}
	//	System.out.println("Table 3: " + xCordT3);

	}
	
	private void dealTable_4Cards(){
		if( xCordT4 >= 222 && xCordT4 < 506){
			xCordT4 += velX;
			displayTableCards_4.setBounds(xCordT4,285, 80, 120);
		}
		else if(xCordT4 == 506 ){
			playClip(dealCard2);
			table4=false;
		}
//		System.out.println("Table 4: " + xCordT4);

	}
	
	private void dealTable_5Cards(){
		if( xCordT5 >= 222 && xCordT5 < 573){
			xCordT5 += velX;
			displayTableCards_5.setBounds(xCordT5,285, 80, 120);
		}
		else if(xCordT5 == 573 ){
			table5=false;
			playClip(dealCard2);
			timer.stop();
		}
	//	System.out.println("Table 5: " + xCordT5);

	}
	

	public void actionPerformed( ActionEvent e){


	// Deal Cards Animation
		

		if(dealCards==true){

			if(player1 == true){
				dealHumanCards();
			}
		
		// Player 2
			else if(player2 == true){	
				dealPlayer2Cards();
			}
		
			else if( player3 == true ){
				dealPlayer3Cards();
			}
		
		
		// Player 4
			else if(player4 == true){			
				dealPlayer4Cards();
			}
			
		
		// Player 5 
			else if( player5 == true){
				dealPlayer5Cards();
			}
		
		
		// Table Card 1
			if( table1 == true){
			 dealTable_1Cards();
			}
		
			if( table2 == true){
			 dealTable_2Cards();
			}
		
			if( table3 == true){
			 dealTable_3Cards();
			}
		
			if( table4 == true){
			 dealTable_4Cards();
			}
		
		
			if( table5 == true){
			 dealTable_5Cards();
			}
			
	}	// ============= end of dealCard if statment ==================		
		
		
	
		

		
	// ===== FOld cards animation or Return Cards ====	
		
	// Human Player 
	 if( player1Fold == true && p1FoldOnce == false) {
		
		 
		// Card 1		
		 if(  yCordHumanPlayer_1 <= 574 && yCordHumanPlayer_1 > 290 ){
			 if(xCordHumanPlayer_1 <= 402 && xCordHumanPlayer_1 > 222){
			xCordHumanPlayer_1 -= (velX+1);
			 }
			yCordHumanPlayer_1 -= (velY+1);
			displayHumanPlayerCard_1.setBounds(xCordHumanPlayer_1,yCordHumanPlayer_1, 80, 120);
		}
		
		
		// Card 2
		if( yCordHumanPlayer_2 <= 574 && yCordHumanPlayer_2 > 290){
			if( xCordHumanPlayer_2 <= 478 && xCordHumanPlayer_2 > 222){
			xCordHumanPlayer_2 -= (velX+1);
			}
			yCordHumanPlayer_2 -= (velY+1);
			displayHumanPlayerCard_2.setBounds(xCordHumanPlayer_2,yCordHumanPlayer_2, 80, 120);
		}

		
		else if( xCordHumanPlayer_1 ==222 && yCordHumanPlayer_1 ==290 && yCordHumanPlayer_2 ==290 && xCordHumanPlayer_2 == 222){
			displayHumanPlayerCard_2.setBounds(222,290, 80, 120);
			displayHumanPlayerCard_1.setBounds(222,290, 80, 120);
			p1FoldOnce= true;
			timer.stop();
			System.out.println("Timer Human timer stop!");
		}
	//	System.out.println("Xcord: " + xCordHumanPlayer_1 + " YCord: " + yCordHumanPlayer_1 +"Xcord2: " + xCordHumanPlayer_2 + " YCord2: " + yCordHumanPlayer_2);

	}
		
	
	// Player 2 Folds
	else if( player2Fold ==true && p2FoldOnce == false){

		//Card 1
		if( (xCordPlayer2_1>=76 && xCordPlayer2_1<=219) ){
			xCordPlayer2_1 += velX;	
			if( yCordPlayer2_1 >=290 && yCordPlayer2_1 <= 430){
			yCordPlayer2_1 -= velY;
			}
			displayPlayerTwoCard_1.setBounds(xCordPlayer2_1,yCordPlayer2_1, 80, 120);
		}
				
	
		// Card 2
		if( yCordPlayer2_2 <=430 && yCordPlayer2_2 >= 290){
			yCordPlayer2_2 -= velY;		
			if(xCordPlayer2_2 >= 152 && xCordPlayer2_2 <=222 ){
			xCordPlayer2_2 += velX;
			}
			displayPlayerTwoCard_2.setBounds(xCordPlayer2_2,yCordPlayer2_2, 80, 120);
		}
				

	
		else if( xCordPlayer2_1  == 220 && yCordPlayer2_1 == 289 && yCordPlayer2_2 == 289 && xCordPlayer2_2 == 223){
			displayPlayerTwoCard_1.setBounds(222,290, 80, 120);
			displayPlayerTwoCard_2.setBounds(222,290, 80, 120);
			p2FoldOnce = true;
			timer.stop();
			System.out.println("Timer player 2 stop");
		}
		//System.out.println("Xcord: " + xCordPlayer2_1 + " YCord: " + yCordPlayer2_1 +"Xcord2: " + xCordPlayer2_2 + " YCord2: " + yCordPlayer2_2);

	}
						
		// Player 3 folds
		else if(player3Fold==true && p3FoldOnce == false){

			// Card 1
			if( yCordPlayer3_1 >= 124 && yCordPlayer3_1<= 290 ){
				yCordPlayer3_1 += velY;	
				if( xCordPlayer3_1 >= 76 && xCordPlayer3_1<=222){
					xCordPlayer3_1 += velX;
				}
				displayPlayerThreeCard_1.setBounds(xCordPlayer3_1,yCordPlayer3_1, 80, 120);
			}
		
			
			// Card 2
			if(  yCordPlayer3_2 >= 124 && yCordPlayer3_2<= 290 ){
				yCordPlayer3_2 += velY;
				if( xCordPlayer3_2 >= 150 && xCordPlayer3_2 <= 222){
					xCordPlayer3_2 += velX;
				}
				displayPlayerThreeCard_2.setBounds(xCordPlayer3_2,yCordPlayer3_2, 80, 120);
			}
			
	
		
			else if( xCordPlayer3_1 == 223 && yCordPlayer3_1 == 291 && yCordPlayer3_2 == 291 && xCordPlayer3_2 == 223){
				displayPlayerThreeCard_1.setBounds(222,290, 80, 120);
				displayPlayerThreeCard_2.setBounds(222,290, 80, 120);
				p3FoldOnce = true;
				timer.stop();
				System.out.println("Timer player 3 stop");
			}
			
		//	System.out.println(" Xcord :  " +xCordPlayer3_1 + " xcord2: " + xCordPlayer3_2 + " ycord:" + yCordPlayer3_1 + "ycord2 " + yCordPlayer3_2);

		}
	
		//Player 4
		else if( player4Fold==true && p4FoldOnce == false){

			// Card 1
			if(xCordPlayer4_1 <= 735 && xCordPlayer4_1>222){
				xCordPlayer4_1 -= (velX+2);
				if (yCordPlayer4_1 <=290 && yCordPlayer4_1 >= 124){
				yCordPlayer4_1 += velY;
			}
				displayPlayerFourCard_1.setBounds(xCordPlayer4_1,yCordPlayer4_1, 80, 120);
				}

	
	
			//Card 2
			if(yCordPlayer4_2 <= 290 && yCordPlayer4_2 >= 124){
				yCordPlayer4_2 += velY;
				if(xCordPlayer4_2 <= 814 && xCordPlayer4_2 > 222){
				xCordPlayer4_2 -= (velX+3);
				}
				displayPlayerFourCard_2.setBounds(xCordPlayer4_2,yCordPlayer4_2, 80, 120);
			}
	
			else if ( xCordPlayer4_1 == 222 && xCordPlayer4_2 == 222 && yCordPlayer4_1 == 291 && yCordPlayer4_2== 291){
					displayPlayerFourCard_1.setBounds(222,290, 80, 120);
					displayPlayerFourCard_2.setBounds(222,290, 80, 120);
					p4FoldOnce = true;
					timer.stop();
					System.out.println("Timer player 4 stop");
			}
		//	System.out.println(" Xcord :  " + xCordPlayer4_1 + " ycord: " + yCordPlayer4_1 + " xcord2:" + xCordPlayer4_2 + "ycord2 " + yCordPlayer4_2);


		}	
		
	// Player 5
		else if( player5Fold == true && p5FoldOnce == false){
			// Card 1
			if(yCordPlayer5_1 <= 450 && yCordPlayer5_1 > 290){
				yCordPlayer5_1 -= velY;
				if(xCordPlayer5_1 <= 735 && xCordPlayer5_1 > 223 ){
				xCordPlayer5_1 -= (velX+3);
				}
				displayPlayerFiveCard_1.setBounds(xCordPlayer5_1,yCordPlayer5_1, 80, 120);
			}
	
			
	
			// Card 2
			if( yCordPlayer5_2 <= 450 && yCordPlayer5_2 > 290){
				yCordPlayer5_2 -= velY;
				if( xCordPlayer5_2 <= 814 && xCordPlayer5_2 > 223){
				xCordPlayer5_2 -= (velX+3);
				}
				displayPlayerFiveCard_2.setBounds(xCordPlayer5_2,yCordPlayer5_2, 80, 120);
			}

			else if(xCordPlayer5_1 == 223 &&  xCordPlayer5_2 == 222 && yCordPlayer5_1 == 290 && yCordPlayer5_2 == 290){
				System.out.println("Timer Player 5 stop!" );
				displayPlayerFiveCard_1.setBounds(222,290, 80, 120);
				displayPlayerFiveCard_2.setBounds(222,290, 80, 120);
				p5FoldOnce = true;
				timer.stop();
			}

			//System.out.println(" Xcord :  " + xCordPlayer5_1 + " ycord: " + yCordPlayer5_1 + " xcord2:" + xCordPlayer5_2 + " ycord2 " + yCordPlayer5_2);

		}
	
	 
	 
	 
// ========================== Return Cards =======================	 
	else if (returnCards == true ) {
	if(p1FoldOnce == false){

		// Card 1		
		 if(  yCordHumanPlayer_1 <= 574 && yCordHumanPlayer_1 > 290 ){
			 if(xCordHumanPlayer_1 <= 402 && xCordHumanPlayer_1 > 222){
			xCordHumanPlayer_1 -= (velX+1);
			 }
			yCordHumanPlayer_1 -= (velY+1);
			displayHumanPlayerCard_1.setBounds(xCordHumanPlayer_1,yCordHumanPlayer_1, 80, 120);
		}
		
		
		// Card 2
		if( yCordHumanPlayer_2 <= 574 && yCordHumanPlayer_2 > 290){
			if( xCordHumanPlayer_2 <= 478 && xCordHumanPlayer_2 > 222){
			xCordHumanPlayer_2 -= (velX+1);
			}
			yCordHumanPlayer_2 -= (velY+1);
			displayHumanPlayerCard_2.setBounds(xCordHumanPlayer_2,yCordHumanPlayer_2, 80, 120);
		}
		
		else if ( yCordHumanPlayer_1 == 574){
			playClip(fold1);
		}
	}
	
	 if(p2FoldOnce == false){
	// Player 2 Folds

		//Card 1
			if( (xCordPlayer2_1>=76 && xCordPlayer2_1<=219) ){
				xCordPlayer2_1 += velX;	
				if( yCordPlayer2_1 >=290 && yCordPlayer2_1 <= 430){
				yCordPlayer2_1 -= velY;
				}
				displayPlayerTwoCard_1.setBounds(xCordPlayer2_1,yCordPlayer2_1, 80, 120);
			}
					
		
			// Card 2
			if( yCordPlayer2_2 <=430 && yCordPlayer2_2 >= 290){
				yCordPlayer2_2 -= velY;		
				if(xCordPlayer2_2 >= 152 && xCordPlayer2_2 <=222 ){
				xCordPlayer2_2 += velX;
				}
				displayPlayerTwoCard_2.setBounds(xCordPlayer2_2,yCordPlayer2_2, 80, 120);
			}
			
			else if(xCordPlayer2_1 == 76){
				playClip(fold2);
			}
					
	}	
	
	
		// Player 3 folds
	 if(p3FoldOnce == false){
		// Card 1
				if( yCordPlayer3_1 >= 124 && yCordPlayer3_1<= 290 ){
						yCordPlayer3_1 += velY;	
						if( xCordPlayer3_1 >= 76 && xCordPlayer3_1<=222){
							xCordPlayer3_1 += velX;
						}
						displayPlayerThreeCard_1.setBounds(xCordPlayer3_1,yCordPlayer3_1, 80, 120);
					}
				
					
					// Card 2
					if(  yCordPlayer3_2 >= 124 && yCordPlayer3_2<= 290 ){
						yCordPlayer3_2 += velY;
						if( xCordPlayer3_2 >= 150 && xCordPlayer3_2 <= 222){
							xCordPlayer3_2 += velX;
						}
						displayPlayerThreeCard_2.setBounds(xCordPlayer3_2,yCordPlayer3_2, 80, 120);
					}
					else if(yCordPlayer3_1 == 124){
						playClip(fold1);
					}
	}
		
	
		//Player 4
	 if(p4FoldOnce == false){
			// Card 1
			if(xCordPlayer4_1 <= 735 && xCordPlayer4_1>222){
				xCordPlayer4_1 -= (velX+2);
				if (yCordPlayer4_1 <=290 && yCordPlayer4_1 >= 124){
				yCordPlayer4_1 += velY;
			}
				displayPlayerFourCard_1.setBounds(xCordPlayer4_1,yCordPlayer4_1, 80, 120);
				}

	
	
			//Card 2
			if(yCordPlayer4_2 <= 290 && yCordPlayer4_2 >= 124){
				yCordPlayer4_2 += velY;
				if(xCordPlayer4_2 <= 814 && xCordPlayer4_2 > 222){
				xCordPlayer4_2 -= (velX+3);
				}
				displayPlayerFourCard_2.setBounds(xCordPlayer4_2,yCordPlayer4_2, 80, 120);
			}
			
			else if(xCordPlayer4_1 == 735){
				playClip(fold2);
			}
	
	}		
		
	// Player 5
	 if(p5FoldOnce == false){
			// Card 1
			if(yCordPlayer5_1 <= 450 && yCordPlayer5_1 > 290){
				yCordPlayer5_1 -= velY;
				if(xCordPlayer5_1 <= 735 && xCordPlayer5_1 > 223 ){
				xCordPlayer5_1 -= (velX+3);
				}
				displayPlayerFiveCard_1.setBounds(xCordPlayer5_1,yCordPlayer5_1, 80, 120);
			}
	
			
	
			// Card 2
			if( yCordPlayer5_2 <= 450 && yCordPlayer5_2 > 290){
				yCordPlayer5_2 -= velY;
				if( xCordPlayer5_2 <= 814 && xCordPlayer5_2 > 223){
				xCordPlayer5_2 -= (velX+3);
				}
				displayPlayerFiveCard_2.setBounds(xCordPlayer5_2,yCordPlayer5_2, 80, 120);
			}
			
			else if(yCordPlayer5_1 ==450){
				playClip(fold1);
			}
			
		}
	 // this will return all the table cards to the deck
	 if(dealCards==true){
		 if(xCordT1 == 305){
			 playClip(fold1);
		 }
		 
		 if(xCordT2==372){
		 playClip(fold2);
		 }

		 if(xCordT1 <= 305 && xCordT1 >222 ){
			 xCordT1 -= velX;
			 if(yCordT1 >=285 && yCordT1 <= 290){
				 yCordT1 -= velY;
			 }
			displayTableCards_1.setBounds(xCordT1,yCordT1, 80, 120);
		 }
		 
		 if(xCordT2 <= 372 && xCordT2 >222 ){
			 xCordT2 -= velX;
			 if(yCordT2 >=285 && yCordT2 <= 290){
				 yCordT2 -= velY;
			 }
			displayTableCards_2.setBounds(xCordT2,yCordT2, 80, 120);
		 }

		 if(xCordT3 <= 439 && xCordT3 >222 ){
			 xCordT3 -= velX;
			 if(yCordT3 >=285 && yCordT3 <= 290){
				 yCordT3 -= velY;
			 }
			displayTableCards_3.setBounds(xCordT3,yCordT3, 80, 120);
		 }
			
		 if(xCordT4 <= 506 && xCordT4 >222 ){
			 xCordT4 -= velX;
			 if(yCordT4 >=285 && yCordT4 <= 290){
				 yCordT4 -= velY;
			 }
			displayTableCards_4.setBounds(xCordT4,yCordT4, 80, 120);
		 }	
			
		 if(xCordT5 <= 573 && xCordT5 >222 ){
			 xCordT5 -= velX;
			 if(yCordT5 >=285 && yCordT5 <= 290){
				 yCordT5 -= velY;
			 }
			displayTableCards_5.setBounds(xCordT5,yCordT5, 80, 120);
		 }	
			

	 }
		 
	} // ======================================== END OF RETURN CARDS =============================================
		
		
		
		if(pauseButton == e.getSource() ){
			task.setpause(true);
			//ptask.setPlayerPause(true);
			int choice=JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?" , "Quit", JOptionPane.YES_NO_OPTION);
			
			if(choice ==0){
				stopClip(bgm);
				MainMenu menu = new MainMenu();
				menu.main(null);
				dispose();	
			}
			else if( choice == 1){
				task.setpause(false);
				//ptask.setPlayerPause(false);
			}
		}
	}
	
	
// Action Listener for menu bars
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	


	class task extends TimerTask
  {
		private int Timeinseconds=0,Timeinminutes=0, Timeinhours=0;
		private boolean pause=false;
			public void run()
			{
				if(pause==true)
				{ ; }
				
				if(pause==false)
				{
					Timeinseconds++;
					
					 if(Timeinseconds==60)
					 {
						 Timeinseconds=0;
						 Timeinminutes++;
					 }
					 
					 if(Timeinminutes==60)
					 {
						 Timeinminutes=0;
						 Timeinhours++;
						 }

					 if(Timeinhours==99&&Timeinminutes==60&&Timeinseconds==60)
					 {
						 getTimer().cancel();}
				}
		addTime(String.format("%02d",Timeinminutes)+":"+String.format("%02d",Timeinseconds));
	}

			public void setpause(boolean s)
			{		
				pause=s;
			}

		}

	public java.util.Timer getTimer()
	{
		return clock;
		}
	
	/*
	class ptask extends TimerTask
	{
		private int Timeinseconds=0,Timeinminutes=0, Timeinhours=0;
		private boolean pause2=false;
		public void run()
		{
			if(pause2==true)
			{;}
			
			if(pause2==false)
			{
				Timeinseconds++;
				if(Timeinseconds==60)
				{
					Timeinseconds=0;
					Timeinminutes++;
				}
				if(Timeinminutes==60)
				{
					Timeinminutes=0;
					Timeinhours++;
					}
				if(Timeinhours==99&&Timeinminutes==60&&Timeinseconds==60)
				{
					getTimer().cancel();
				}
			}
			addPlayerTime(Integer.toString(Timeinhours)+":"+Integer.toString(Timeinminutes)+":"+Integer.toString(Timeinseconds));
	}
	

		public void setPlayerPause(boolean bool)
		{
			pause2=bool;
			}
		public void resetTimer(){
			Timeinseconds=0;
			Timeinminutes=0;
			Timeinhours=0;
		}
	}
	*/
	
	public Clip loadClip( String filename )
		{
		Clip clip = null;

		try
		{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream( getClass().getResource( "sounds/" + filename ) );
			clip = AudioSystem.getClip();
			clip.open( audioIn );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return clip;
	}
	
	public void playClip( Clip clip )
		{
			if( clip.isRunning() )
			{
				clip.stop();
			}
		
    
			clip.setFramePosition( 0 );
			clip.start();
		}
	
	public void loopClip(Clip clip)
	{
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		//volume.setValue(-10);
		clip.loop(10000);}
	
	public void stopClip(Clip clip)
	{
		clip.stop();
	}
	}


