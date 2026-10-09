package blackjack;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private static String key(Card c) {
        return c.face + "/" + c.suit;
    }

    private static Set<String> drawAll(Deck deck, int n) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            assertTrue(seen.add(key(deck.draw())), "duplicate card dealt at draw " + (i + 1));
        }
        return seen;
    }

    @Test
    void newDeckHasFiftyTwoUniqueCards() {
        Deck deck = new Deck();
        assertEquals(52, deck.remaining());
        assertEquals(52, drawAll(deck, 52).size());
    }

    @Test
    void everyFaceAndSuitIsPresent() {
        Set<String> cards = drawAll(new Deck(), 52);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Face face : Card.Face.values()) {
                assertTrue(cards.contains(face + "/" + suit), face + " of " + suit + " missing");
            }
        }
    }

    @Test
    void shufflePreservesTheCards() {
        Deck deck = new Deck();
        Set<String> before = drawAll(deck, 52);
        deck.shuffle();
        assertEquals(before, drawAll(deck, 52));
    }

    /**
     * Regression: shuffle() did not reset the deal count, so a round started
     * near the end of the count reshuffled mid-round and could deal a card that
     * was already on the table.
     */
    @Test
    void shuffleStartsAFullDeck() {
        Deck deck = new Deck();
        drawAll(deck, 50);
        deck.shuffle();
        assertEquals(52, deck.remaining());
        drawAll(deck, 52);
    }

    @Test
    void exhaustedDeckReshufflesItself() {
        Deck deck = new Deck();
        drawAll(deck, 52);
        assertEquals(0, deck.remaining());
        assertNotNull(deck.draw());
        assertEquals(51, deck.remaining());
    }
}
