package blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.Card.Face.*;
import static blackjack.Cards.*;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private static Dealer dealerWith(Card.Face... order) {
        Dealer dealer = new Dealer(stacked(order));
        dealer.deal();
        dealer.deal();
        return dealer;
    }

    @Test
    void hitsSixteen() {
        Dealer dealer = dealerWith(TEN, SIX, FIVE);
        dealer.dealSelf();
        assertEquals(21, dealer.hand.total);
        assertEquals(3, dealer.hand.hand.size());
    }

    @Test
    void standsOnHardSeventeen() {
        Dealer dealer = dealerWith(TEN, SEVEN);
        dealer.dealSelf();
        assertEquals(17, dealer.hand.total);
        assertEquals(2, dealer.hand.hand.size());
    }

    @Test
    void hitsSoftSeventeen() {
        Dealer dealer = dealerWith(ACE, SIX, TWO);
        dealer.dealSelf();
        assertEquals(19, dealer.hand.total);
        assertEquals(List.of(ACE, SIX, TWO), faces(dealer.hand));
    }

    @Test
    void softSeventeenThatTurnsHardKeepsDrawing() {
        // A-6 (soft 17) + 10 = hard 17, stand.
        Dealer dealer = dealerWith(ACE, SIX, TEN);
        dealer.dealSelf();
        assertEquals(17, dealer.hand.total);
        assertFalse(dealer.hand.isSoft());
        assertEquals(3, dealer.hand.hand.size());
    }

    @Test
    void standsOnSoftEighteen() {
        Dealer dealer = dealerWith(ACE, SEVEN);
        dealer.dealSelf();
        assertEquals(18, dealer.hand.total);
        assertEquals(2, dealer.hand.hand.size());
    }

    @Test
    void drawsUntilSeventeenOrBust() {
        Dealer dealer = dealerWith(TWO, THREE, FOUR, TWO, TEN);
        dealer.dealSelf();
        assertEquals(21, dealer.hand.total);
        assertEquals(5, dealer.hand.hand.size());
    }

    @Test
    void canBust() {
        Dealer dealer = dealerWith(TEN, SIX, KING);
        dealer.dealSelf();
        assertTrue(Rules.isBust(dealer.hand));
    }

    @Test
    void upCardIsTheSecondCardDealt() {
        Dealer dealer = dealerWith(KING, ACE);
        assertEquals(ACE, dealer.upCard().face);
    }
}
