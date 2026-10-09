package blackjack;

import org.junit.jupiter.api.Test;

import static blackjack.Card.Face.*;
import static blackjack.Cards.hand;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void hardTotalsAddUp() {
        Hand h = hand(TEN, SEVEN);
        assertEquals(17, h.total);
        assertFalse(h.isSoft());
    }

    @Test
    void aceCountsElevenWhenItFits() {
        Hand h = hand(ACE, SIX);
        assertEquals(17, h.total);
        assertTrue(h.isSoft());
    }

    @Test
    void aceDropsToOneRatherThanBust() {
        Hand h = hand(ACE, SIX, NINE);
        assertEquals(16, h.total);
        assertFalse(h.isSoft());
    }

    @Test
    void twoAcesAreTwelve() {
        assertEquals(12, hand(ACE, ACE).total);
    }

    @Test
    void threeAcesAreThirteen() {
        assertEquals(13, hand(ACE, ACE, ACE).total);
    }

    @Test
    void fourAcesAndASevenAreSoftTwentyOne() {
        Hand h = hand(ACE, ACE, ACE, ACE, SEVEN);
        assertEquals(21, h.total);
        assertTrue(h.isSoft());
    }

    @Test
    void twoAcesAndNineAreTwentyOne() {
        assertEquals(21, hand(ACE, ACE, NINE).total);
    }

    /**
     * Regression: hitting a soft 21 with an ace only demoted one ace, so
     * A-5-5-A totalled a bust 22 instead of 12.
     */
    @Test
    void hittingSoftTwentyOneWithAnAceIsTwelve() {
        Hand h = hand(ACE, FIVE, FIVE, ACE);
        assertEquals(12, h.total);
        assertFalse(h.isSoft());
    }

    @Test
    void hardTwentyOnePlusAceBusts() {
        assertEquals(22, hand(TEN, FIVE, SIX, ACE).total);
    }

    @Test
    void handOverTwentyOneIsBust() {
        Hand h = hand(KING, QUEEN, TWO);
        assertEquals(22, h.total);
        assertTrue(Rules.isBust(h));
    }

    @Test
    void muckEmptiesTheHand() {
        Hand h = hand(ACE, KING);
        h.muck();
        assertTrue(h.hand.isEmpty());
        assertEquals(0, h.total);
        assertFalse(h.isSoft());
    }
}
