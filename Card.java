package texasholdem;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * 1 = ace
 * 2 = 2
 * 3 = 3
 * 4 = 4
 * 5 = 5
 * 6 = 6
 * 7 = 7
 * 8 = 8
 * 9 = 9 
 * 10 = 10
 * 11 = jack
 * 12 = queen
 * 13 = king
 * Ace can play 1 and 14;
 * 0 = diamond
 * 1 = clubs
 * 2 = hearts
 * 3 = spades
 */

public class Card {
	private ImageIcon imageCard;
	private int rank;
	private int suit;
	private JPanel contentPane;// Added by George
	private final int cardWidth = 68;// Added by George
	private final int cardHeight = 110;// Added by George
	private boolean cardShow;// Added by George
	// Added by George	
	
	// Added by George	
	private static void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}
	
// Added by George
	public Card() {
		rank = 0;
		suit = 0;
		cardShow=false;
		imageCard =null;
	}

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
// Added by George	
	public Card(int rank, int suit, boolean cardShow) {
		this.rank = rank;
		this.suit = suit;
		this.cardShow=cardShow;
	}
	/*
	 * r = 1-52
	 */
	
/*	public Card( int r ){
		Card c = setCardGivenRank(r);
		this.rank = c.rank;
		this.suit = c.suit;
	}*/
	public ImageIcon getImage(){
		
		return imageCard=getCardImage(getRankOneThroughFiftyTwo(),true);
	}
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	

	@Override
	public String toString() {
		return "Rank: " + rank + " Suit: " + suit;
	}
	/*
	 * Given a value between 1-52 inclusive
	 */
	private Card setCardGivenRank(int r){
		if( r < 14){
			return new Card(r,0);
		}else if( r < 27 ){
			return new Card(r-13,1);
		}else if( r < 40){
			return new Card(r-26,2);
		}else if( r < 53){//53
			return new Card(r-39,3);
		}else{
			return null;
		}
	}

	/*
	 * Get Card and convert it as a int
	 * 
	 */

	public int getRankOneThroughFiftyTwo(){
		if(getSuit() == 0){
			return getRank();
		}else if( getSuit() == 1){
			return getRank() + 13;
		}else if( getSuit() == 2){
			return getRank() + 26;
		}else if( getSuit() == 3){
			return getRank() + 39;
		}else{
			return -1;
		}
	}
	
	public String toReadableString(){
		String s = null;		
		switch(rank){
		case 1:
			switch(suit){
			case 0: s = "Ad";
				break;
			case 1: s = "Ac";
				break;
			case 2: s = "Ah";
				break;
			case 3: s = "As";
				break;
			}
			break;
		case 2:
			switch(suit){
			case 0: s = "2d";
				break;
			case 1: s = "2c";
				break;
			case 2: s = "2h";
				break;
			case 3: s = "2s";
				break;
			}
			break;
		case 3:
			switch(suit){
			case 0: s = "3d";
				break;
			case 1: s = "3c";
				break;
			case 2: s = "3h";
				break;
			case 3: s = "3s";
				break;
			}
			break;
		case 4:
			switch(suit){
			case 0: s = "4d";
				break;
			case 1: s = "4c";
				break;
			case 2: s = "4h";
				break;
			case 3: s = "4s";
				break;
			}
			break;
		case 5:
			switch(suit){
			case 0: s = "5d";
				break;
			case 1: s = "5c";
				break;
			case 2: s = "5h";
				break;
			case 3: s = "5s";
				break;
			}
			break;
		case 6:
			switch(suit){
			case 0: s = "6d";
				break;
			case 1: s = "6c";
				break;
			case 2: s = "6h";
				break;
			case 3: s = "6s";
				break;
			}
			break;
		case 7:
			switch(suit){
			case 0: s = "7d";
				break;
			case 1: s = "7c";
				break;
			case 2: s = "7h";
				break;
			case 3: s = "7s";
				break;
			}
			break;
		case 8:
			switch(suit){
			case 0: s = "8d";
				break;
			case 1: s = "8c";
				break;
			case 2: s = "8h";
				break;
			case 3: s = "8s";
				break;
			}
			break;
		case 9:
			switch(suit){
			case 0: s = "9d";
				break;
			case 1: s = "9c";
				break;
			case 2: s = "9h";
				break;
			case 3: s = "9s";
				break;
			}
			break;
		case 10:
			switch(suit){
			case 0: s = "10d";
				break;
			case 1: s = "10c";
				break;
			case 2: s = "10h";
				break;
			case 3: s = "10s";
				break;
			}
			break;
		case 11:
			switch(suit){
			case 0: s = "Jd";
				break;
			case 1: s = "Jc";
				break;
			case 2: s = "Jh";
				break;
			case 3: s = "Js";
				break;
			}
			break;
		case 12:
			switch(suit){
			case 0: s = "Qd";
				break;
			case 1: s = "Qc";
				break;
			case 2: s = "Qh";
				break;
			case 3: s = "Qs";
				break;
			}
			break;
		case 13:
			switch(suit){
			case 0: s = "Kd";
				break;
			case 1: s = "Kc";
				break;
			case 2: s = "Kh";
				break;
			case 3: s = "Ks";
				break;
			}
			break;
		case 14:
			System.out.println("ERROR 14");
			switch(suit){
			case 0: s = "Ad";
				break;
			case 1: s = "Ac";
				break;
			case 2: s = "Ah";
				break;
			case 3: s = "As";
				break;
			}
			break;
	}
	return s;
	
	}
	


// Added by George	
	
	public boolean getCardShow(){
		return cardShow;
	}
	
	public boolean setShowCard( boolean a){
		return cardShow=a;
	}

	
// Added by George	
// Get card width
	public int getCardImageWidth(){
		return this.cardWidth;
	}
	
// Added by George	
// Get card height
	public int getCardImageHeight(){
		return this.cardHeight;
	}

	// Added by George
// Changes ImageSize	
	public ImageIcon getResizeImageCopy(Image originalImage, int width, int height){
		Image newimg = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		return newIcon;
}
	
// Added by George	
// Get card Image return a JLabel
	public ImageIcon getCardImage(int cardNumber, boolean showCard){
		ImageIcon backCardImage = new ImageIcon(getClass().getResource("Icons/CardsImage/BackCard/backcard1.png"));
		Image image = null;
		
		//cardNumber+=1;
		
		try{
			switch( cardNumber){
	
				case 1: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsAce.png"));
				break;	
				
				case 2: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsTwo.png"));
				break;	
		
				case 3: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsThree.png"));
				break;	
		
				case 4: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsFour.png"));
				break;	
		
				case 5: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsFive.png"));
				break;	
		
				case 6: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsSix.png"));
				break;	
		
				case 7: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsSeven.png"));
				break;
				
				case 8: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsEight.png"));
				break;
		
				case 9: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsNine.png"));
				break;
		
				case 10: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsTen.png"));
				 break;
		
				case 11: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsJack.png"));
				 break;
		
				case 12: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsQueen.png"));
				 break;
		
				case 13: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/DiamondsKing.png"));
				 break;
		
				case 14: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsAce.png"));
				 break;
		
				case 15: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsTwo.png"));
				 break;
		
				case 16: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsThree.png"));
				 break;
		
				case 17: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsFour.png"));
				 break;
		
				case 18: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsFive.png"));
				 break;
		
				case 19: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsSix.png"));
				 break;
				 
				case 20: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsSeven.png"));
				 break;
		 
				case 21: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsEight.png"));
				 break;
		
				case 22: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsNine.png"));
				 break;
		
				case 23: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsTen.png"));
		 		 break;
		
				case 24: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsJack.png"));
				 break;
		
				case 25: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsQueen.png"));
				 break;
	
				case 26: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/ClubsKing.png"));
				 break;
		
				case 27: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsAce.png"));
				 break;
		
				case 28: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsTwo.png"));
				 break;
		
				case 29: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsThree.png"));
				 break;
		
				case 30: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsFour.png"));
			 	 break;
		
				case 31: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsFive.png"));
				 break;
		
				case 32: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsSix.png"));
				 break;
		
				case 33: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsSeven.png"));
				break;
		
				case 34: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsEight.png"));
				 break;
		
				case 35: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsNine.png"));
				 break;
		
				case 36: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsTen.png"));
				 break;
		
				case 37: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsJack.png"));
				 break;
		
				case 38: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsQueen.png"));
				 break;
		
				case 39: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/HeartsKing.png"));
				 break;
		
				case 40: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesAce.png"));
				 break;
	
				case 41: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesTwo.png"));
				 break;
	
				case 42: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesThree.png"));
				 break;
	
				case 43: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesFour.png"));
				 break;
	
				case 44: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesFive.png"));
				 break;
	
				case 45: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesSix.png"));
				break;
	
				case 46: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesSeven.png"));
				 break;
	
				case 47: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesEight.png"));
				 break;
	
				case 48: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesNine.png"));
				 break;
	
				case 49: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesTen.png"));
				 break;
		
				case 50: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesJack.png"));
				 break;
		
				case 51: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesQueen.png"));
				 break;
		
				case 52: imageCard = new ImageIcon( getClass().getResource("Icons/CardsImage/Deck2/SpadesKing.png"));
		 		 break;
		
				default: System.out.println("no match of image Number: " + cardNumber);
				 break;
			}
		}
		catch(NullPointerException e){
			System.out.println("CardImage not located Number: " + cardNumber);
			}
		
		if(showCard==true){
		 image= imageCard.getImage();
		 imageCard=getResizeImageCopy( image, cardWidth, cardHeight );
		}
		else{
		 image=backCardImage.getImage();
		 imageCard=getResizeImageCopy( image, 80, 120 );
		}
		
	 return imageCard;
	}
	
	

} //========================= END OF CARDS =========================
