package blackjack;

import org.junit.jupiter.api.Test;

import static blackjack.Card.Face.*;
import static blackjack.Cards.hand;
import static blackjack.Rules.Outcome.*;
import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    @Test
    void aceAndTenValueCardIsBlackjack() {
        assertTrue(Rules.isBlackjack(hand(ACE, KING)));
        assertTrue(Rules.isBlackjack(hand(TEN, ACE)));
    }

    @Test
    void threeCardTwentyOneIsNotBlackjack() {
        Hand h = hand(SEVEN, SEVEN, SEVEN);
        assertEquals(21, h.total);
        assertFalse(Rules.isBlackjack(h));
    }

    @Test
    void dealerHitDecision() {
        assertTrue(Rules.dealerShouldHit(hand(TEN, SIX)));
        assertTrue(Rules.dealerShouldHit(hand(ACE, SIX)), "soft 17 hits");
        assertFalse(Rules.dealerShouldHit(hand(TEN, SEVEN)), "hard 17 stands");
        assertFalse(Rules.dealerShouldHit(hand(ACE, SEVEN)), "soft 18 stands");
        assertFalse(Rules.dealerShouldHit(hand(TEN, SIX, KING)), "bust stands");
    }

    @Test
    void higherTotalWins() {
        assertEquals(WIN, Rules.outcome(hand(TEN, NINE), hand(TEN, EIGHT)));
        assertEquals(LOSE, Rules.outcome(hand(TEN, EIGHT), hand(TEN, NINE)));
    }

    @Test
    void equalTotalsPush() {
        assertEquals(PUSH, Rules.outcome(hand(TEN, EIGHT), hand(NINE, NINE)));
    }

    @Test
    void dealerBustPaysAStandingPlayer() {
        assertEquals(WIN, Rules.outcome(hand(TEN, TWO), hand(TEN, SIX, KING)));
    }

    @Test
    void playerBustLosesEvenIfDealerBusts() {
        assertEquals(LOSE, Rules.outcome(hand(TEN, SIX, KING), hand(TEN, SIX, QUEEN)));
    }

    @Test
    void playerBlackjackBeatsDealerTwentyOne() {
        assertEquals(BLACKJACK, Rules.outcome(hand(ACE, KING), hand(SEVEN, SEVEN, SEVEN)));
    }

    /** Regression: a player blackjack used to be paid 3:2 even against a dealer blackjack. */
    @Test
    void blackjackAgainstBlackjackPushes() {
        assertEquals(PUSH, Rules.outcome(hand(ACE, KING), hand(QUEEN, ACE)));
    }

    /** Regression: a three-card 21 used to push against a dealer blackjack instead of losing. */
    @Test
    void dealerBlackjackBeatsThreeCardTwentyOne() {
        assertEquals(LOSE, Rules.outcome(hand(SEVEN, SEVEN, SEVEN), hand(ACE, KING)));
    }

    @Test
    void payoutsReturnTheWagerPlusWinnings() {
        assertEquals(25.0, Rules.payout(BLACKJACK, 10), "blackjack pays 3:2");
        assertEquals(20.0, Rules.payout(WIN, 10), "win pays 1:1");
        assertEquals(10.0, Rules.payout(PUSH, 10), "push returns the wager");
        assertEquals(0.0, Rules.payout(LOSE, 10));
    }

    @Test
    void blackjackOnOddBetPaysFractionalChips() {
        assertEquals(12.5, Rules.payout(BLACKJACK, 5));
    }

    @Test
    void doubledWinPaysOnTheDoubledWager() {
        // Bet 10, doubled with another 10: a win returns 40 to the stake.
        assertEquals(40.0, Rules.payout(WIN, 10 + 10));
    }

    /**
     * Regression: insurance against a dealer blackjack cleared the bet and paid
     * nothing. Insurance pays 2:1, so the insured player breaks even.
     */
    @Test
    void insuranceAgainstDealerBlackjackBreaksEven() {
        double bet = 10;
        double stake = 100 - bet;                       // bet taken on the deal
        double insurance = Rules.insuranceCost(bet);
        assertEquals(5.0, insurance);
        stake -= insurance;
        stake += Rules.insurancePayout(insurance, true);
        stake += Rules.payout(Rules.outcome(hand(TEN, NINE), hand(ACE, KING)), bet);
        assertEquals(100.0, stake);
    }

    @Test
    void insuranceIsLostWhenDealerHasNoBlackjack() {
        assertEquals(0.0, Rules.insurancePayout(5, false));
    }
}
