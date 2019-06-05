package blackjack;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private Card[] deck = new Card[52];
    private short left = 52;

    public Deck() {
        create();
    }

    public void create() {
        int i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Face face : Card.Face.values()) {
                try {
                    deck[i++] = new Card(face, suit);
                }  catch (InputMismatchException e1) {
                    System.err.println("Expected 52 cards in this Deck and got more!");
                }
            }
        }
    }

    public void shuffle() {
        Random rand = ThreadLocalRandom.current();
        for (int i = deck.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            // Simple swap
            Card swap = deck[index];
            deck[index] = deck[i];
            deck[i] = swap;
        }
    }

    public Card draw() {
        Card drew = null;
        
        if (left > 1) {
            drew = deck[--left];
        }

        return drew;
    }



    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (Card card : deck) {
            display.append("[").append(card.face).append(card.suit.value).append(card.value).append("]");

        }
        return display + "";
    }
}
