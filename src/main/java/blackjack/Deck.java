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

    /**
     * Shuffles all 52 cards back together and starts dealing from a full deck.
     * Resetting the count matters: without it a round started part-way through
     * the count would reshuffle mid-hand and could deal a card already on the table.
     */
    public void shuffle() {
        left = 52;
        Random rand = ThreadLocalRandom.current();
        for (int i = deck.length - 1; i > 0; i--)
        {
            int j = rand.nextInt(i + 1);
            // Simple swap
            Card swap = deck[j];
            deck[j] = deck[i];
            deck[i] = swap;
        }
    }

    public Card draw() {

        if (left == 0) {
            shuffle();
        }
        Card drew = deck[--left];

        return drew;
    }

    /** Cards left to deal before the deck reshuffles itself. */
    public int remaining() {
        return left;
    }



    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (Card card : deck) {
            display.append("[").append(card.face).append(card.suit.value).append("(").append(card.value).append(")]");

        }
        return display + "";
    }
}
