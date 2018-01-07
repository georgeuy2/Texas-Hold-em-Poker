package texasholdem;

import java.util.ArrayList;

/*
 * Trent Robison
 * 
 */

public class HandEvaluation {
	// Object data
	Card[] cards;
	private int pairValue;
	private int threeOfAKindValue;
	private int fourOfAKindValue;
	private int straightHighCard;
	private int[] twoPair = new int[2];// FIX THIS
	// Constructor
	public HandEvaluation(Hand h) {
		cards = h.getCards();
	}

	public HandEvaluation() {
		// TODO Auto-generated constructor stub
	}

	public int getPairValue() {
		return pairValue;
	}

	public int getThreeOfAKindValue() {
		return threeOfAKindValue;
	}

	public int getFourOfAKindValue() {
		return fourOfAKindValue;
	}

	public int[] getTwoPair() {
		return twoPair;
	}

	public boolean isFlush() {
		// Store the suit of the first card
		// Compared every other card's suit to that one
		// Returns false if any suit does not match
		int suit = cards[0].getSuit();
		for (int i = 1; i < cards.length; i++) {
			if (cards[i].getSuit() != suit)
				return false;
		}
		return true;
	}

	public boolean isPair() {

		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}
		// Loop through the values. Compare each value to all values
		// If exactly two matches are made - return true
		for (int x = 1; x < values.length; x++) {
			for (int y = 0; y < x; y++) {

				if (values[x] == values[y]) {
					pairValue = values[x];
					return true;
				}

			}
		}

		return false;
	}

	public boolean isThreeOfAKind() {
		// int[] values = new int[5];
		int counter = 0;
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}

		// Same process as isPair(), except return true for 3 matches
		for (int x = 2; x < values.length; x++) {
			for (int y = 0; y < x; y++) {
				if (values[x] == values[y])
					counter++;
				if (counter == 2) {
					threeOfAKindValue = values[x];
					return true;
				}
			}
			counter = 0;
		}

		return false;
	}

	public boolean isFourOfAKind() {
		// int[] values = new int[5];
		int counter = 0;
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}

		/*
		 * for (int i = 0; i < cards.length; i++) { values[i] =
		 * cards[i].getRank(); }
		 */
		// Same process as isPair(), except return true for 4 matches
		for (int x = 3; x < values.length; x++) {
			for (int y = 0; y < x; y++) {
				if (values[x] == values[y])
					counter++;
				if (counter == 3) {
					fourOfAKindValue = values[x];
					return true;
				}
			}
			counter = 0;
		}

		return false;
	}

	public boolean isTwoPair() {
		// Including FourOfKind and FullHouse
		// int[] values = new int[5];
		int sum = 0;
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}

		/*
		 * for (int i = 0; i < cards.length; i++) { values[i] =
		 * cards[i].getRank(); }
		 */
		for (int x = 1; x < values.length; x++) {
			for (int y = 0; y < x; y++) {
				if (values[x] == values[y]) {
					sum++;
					int f = 0;
					twoPair[f] = values[x];
					f++;
				}
			}
		}

		if (sum >= 2) {
			return true;
		}

		return false;
	}

	public boolean isFullHouse() {
		// Implementation is missing.
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}

		if (isThreeOfAKind() == true) {
			// int[] values = new int[5];
			int pos;
			int temp;

			// Sort Numerically
			for (int i = 1; i < values.length; i++) {
				pos = i;
				while (pos != 0) {
					if (values[pos] < values[pos - 1]) {
						temp = values[pos];
						values[pos] = values[pos - 1];
						values[pos - 1] = temp;
					}
					pos--;
				}
			}
			/*
			 * for (int i = 0; i < cards.length; i++) { values[i] =
			 * cards[i].getRank(); }
			 */
			if ((values[0] == values[1]) && (values[2] == values[3])
					&& (values[3] == values[4]))
				return true;
			else if (values[0] == values[1] && values[1] == values[2]
					&& values[3] == values[4])
				return true;
			else
				return false;
		} else {
			return false;
		}
	}// end of method

	public boolean isStraight() {

		int pos;
		int temp;
		int[] values = new int[5];
		for (int i = 0; i < 5; i++) {
			values[i] = cards[i].getRank();
		}

		// Set values in array
		for (int i = 0; i < cards.length; i++) {
			values[i] = cards[i].getRank();
			// If Ace
			if (values[i] == 1)
				values[i] = 14;
		}

		// Sort Numerically
		for (int i = 1; i < values.length; i++) {
			pos = i;
			while (pos != 0) {
				if (values[pos] < values[pos - 1]) {
					temp = values[pos];
					values[pos] = values[pos - 1];
					values[pos - 1] = temp;
				}
				pos--;
			}
		}

		// Test for Straight
		// Each successive card should be +1
		for (int i = 0; i < values.length - 1; i++) {
			if (values[i] != values[i + 1] - 1)
				return false;
			straightHighCard = values[i];
		}

		return true;
	}

	public boolean isStraightFlush() {
		// If theres a straight and a flush present
		if (isStraight() == true && isFlush() == true)
			return true;

		return false;
	}

	public boolean isRoyalFlush() {
		if (isFlush() == false || isStraight() == false)
			return false;

		int[] values = new int[5];
		int pos;
		int temp;

		// Set values in array
		for (int i = 0; i < cards.length; i++) {
			values[i] = cards[i].getRank();
			// If Ace
			if (values[i] == 1)
				values[i] = 14;
		}

		// Sort Numerically
		for (int i = 1; i < values.length; i++) {
			pos = i;
			while (pos != 0) {
				if (values[pos] < values[pos - 1]) {
					temp = values[pos];
					values[pos] = values[pos - 1];
					values[pos - 1] = temp;
				}
				pos--;
			}
		}
		// Royal flush is a straight flush, with the lowest card being a 10
		if (values[0] == 10)
			return true;

		return false;
	}

	public int giveHandIntegerRanking() {
		int hCard = 0;
		if (isStraightFlush()) {
			hCard = getHighCard();
			return 1600 + hCard + getBestSuitGivenValue(hCard, cards);
		} else if (isFourOfAKind()) {
			return 1400 + fourOfAKindValue
					+ getBestSuitGivenValue(fourOfAKindValue, cards);
		} else if (isFullHouse()) {
			hCard = getHighCard();
			return 1200 + hCard + getBestSuitGivenValue(hCard, cards);
		} else if (isFlush()) {
			hCard = getHighCard();
			return 1000 + hCard + getBestSuitGivenValue(hCard, cards);
		} else if (isStraight()) {
			return 800 + straightHighCard
					+ getBestSuitGivenValue(straightHighCard, cards);
		} else if (isThreeOfAKind()) {
			return 600 + threeOfAKindValue
					+ getBestSuitGivenValue(threeOfAKindValue, cards);
		} else if (isTwoPair()) {
			int bestPairValue = getBestPair();
			return 400 + bestPairValue
					+ getBestSuitGivenValue(bestPairValue, cards);
		} else if (isPair()) {
			return 200 + pairValue + getBestSuitGivenValue(pairValue, cards);
		} else {// highCard
			hCard = getHighCard();
			return hCard + getBestSuitGivenValue(hCard, cards);
		}
	}

	/*
	 * Also checks for aces changing value from 1 to 14
	 */
	public int getHighCard() {
		int [] v = new int[5];
		for(int i = 0; i < 5; i++){
			v[i] = cards[i].getRank();
		}
		if (v[0] == 1)
			v[0] = 14;// ace
		int highestCard = v[0];

		for (int i = 1; i < 5; i++) {
			if (v[i] == 1)
				v[i] = 14;// ace
			if (highestCard < v[i]) {
				highestCard = v[i];
			}
		}
		return highestCard;
	}

	/*
	 * ex 2h 4d 4s 10s 10h
	 * 
	 * will return spades because 10s
	 */

	public int getBestSuitGivenValue(int v, Card[] c) {
		ArrayList<Card> c2 = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			if (v == c[i].getRank()) {
				c2.add(c[i]);
			}
		}
		int bestSuit = 0;
		if (c2.size() > 0) {
			bestSuit = c2.get(0).getSuit();
			for (int i = 0; i < c2.size(); i++) {
				if (bestSuit < c2.get(i).getSuit()) {
					bestSuit = c2.get(i).getSuit();
				}
			}
		}
		return bestSuit;
	}

	public int getBestPair() {
		int [] v = new int[5];
		for(int i = 0; i < 5; i++){
			v[i] = cards[i].getRank();
		}
		if (v[0] == 1)
			v[0] = 14;// ace
		int highestCard = v[0];
		int secondHighestCard = 0;

		for (int i = 1; i < 5; i++) {
			if (v[i] == 1)
				v[i] = 14;// ace
			if (highestCard < v[i]) {
				secondHighestCard = highestCard;
				highestCard = v[i];
			}
		}
		int countH = 0;
		for (int i = 0; i < 5; i++) {
			if (v[i] == highestCard) {
				countH++;
			}
		}
		return (countH >= 2) ? highestCard : secondHighestCard;
	}

	public Card getCard(int i) {
		return cards[i];
	}

}