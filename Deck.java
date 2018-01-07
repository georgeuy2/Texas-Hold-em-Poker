package texasholdem;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	// private Card [] cards = new Card[52];
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		intitializCards();

	}

	public Deck(Card[] c) {
		cards = new ArrayList<Card>();
		for (int i = 0; i < c.length; i++) {
			cards.add(c[i]);
		}
	}

	/*
	 * Gets the top card then removes that card from deck
	 */
	public Card dealOffTheTopOfDeck() {
		Card c = cards.get(cards.size() - 1);
		cards.remove(cards.size() - 1);
		return c;
	}

	public void ToString() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println("Card[" + i + "] "
					+ cards.get(i).toReadableString());
		}
	}

	
	/*
	 * This good
	 */
	public void intitializCards() {

		for (int i = 0; i < 52; i++) {
			cards.add(new Card());
		}
		int j = 0;
		int k = 1;
		for (int i = 0; i < 52; i++) {
			if (k == 14) {
				if (k == 14 && j == 3){
					break;
				}
				k = 1;
				j++;
			}
			cards.get(i).setRank(k);
			cards.get(i).setSuit(j);
			k++;
		}
	}

	/*
	 * Removes cards from deck and return deck
	 */
	public void findAndDiscard(Card[] c) {
		ArrayList<Card> temp = new ArrayList<Card>();
		for (int i = 0; i < 52 - c.length; i++) {
			if (!(cards.get(i).equals(c[0]) || cards.get(i).equals(c[1]))) {
				temp.add(cards.get(i));
			}
		}
		cards = temp;
	}

	/*
	 * returns rand 1-52
	 */
	public void shuffleDeck() {
		Random rnd = new Random();
		for (int i = cards.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Card temp = cards.get(index);

			// int a = ar[index];
			cards.set(index, cards.get(i));
			// ar[index] = ar[i];
			cards.set(i, temp);
			// ar[i] = a;
		}
	}

	public void cut() {
		int cutHere;
		cutHere = 1 + (int) (Math.random() * ((52 - 1) + 1));

		ArrayList<Card> c = new ArrayList<Card>();
		int j = 0;
		for (int i = cutHere; i < 52; i++) {
			c.add(j, cards.get(i));
			j++;
		}

		for (int i = 0; i < cutHere; i++) {
			c.add(j, cards.get(i));
			j++;
		}
		cards = c;
	}

}
