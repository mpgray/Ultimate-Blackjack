package blackjack;

/**
 * The blackjack rules this game plays by, as pure functions over {@link Hand}s.
 *
 * <p>Nothing here touches Swing, so the rules can be tested headless. The GUI
 * classes ({@link Game}, {@link Play}, {@link Dealer}) ask this class what
 * happened and only decide how to show it.
 *
 * <ul>
 *   <li>Dealer hits soft 17 and stands on hard 17 or more.</li>
 *   <li>Blackjack (an ace and a ten-value card as the first two cards) pays 3:2.</li>
 *   <li>A dealer blackjack beats any player hand except a player blackjack, which pushes.</li>
 *   <li>A player who busts loses even if the dealer also busts.</li>
 *   <li>Insurance costs half the bet and pays 2:1 when the dealer has blackjack.</li>
 * </ul>
 */
public final class Rules {

    public static final int BLACKJACK = 21;
    public static final int DEALER_STANDS_ON = 17;

    public enum Outcome { BLACKJACK, WIN, PUSH, LOSE }

    private Rules() {
    }

    public static boolean isBlackjack(Hand hand) {
        return hand.hand.size() == 2 && hand.total == BLACKJACK;
    }

    public static boolean isBust(Hand hand) {
        return hand.total > BLACKJACK;
    }

    /** Dealer draws below 17 and on a soft 17. */
    public static boolean dealerShouldHit(Hand dealer) {
        return dealer.total < DEALER_STANDS_ON
                || (dealer.total == DEALER_STANDS_ON && dealer.isSoft());
    }

    public static Outcome outcome(Hand player, Hand dealer) {
        boolean playerBlackjack = isBlackjack(player);
        boolean dealerBlackjack = isBlackjack(dealer);

        if (playerBlackjack && dealerBlackjack) {
            return Outcome.PUSH;
        }
        if (playerBlackjack) {
            return Outcome.BLACKJACK;
        }
        if (isBust(player) || dealerBlackjack) {
            return Outcome.LOSE;
        }
        if (isBust(dealer) || player.total > dealer.total) {
            return Outcome.WIN;
        }
        if (player.total == dealer.total) {
            return Outcome.PUSH;
        }
        return Outcome.LOSE;
    }

    /**
     * Chips returned to the player's stake at the end of a round. The wager was
     * taken from the stake when it was placed, so this includes the wager itself:
     * a win returns twice the wager, a blackjack two and a half times.
     */
    public static double payout(Outcome outcome, double wager) {
        return switch (outcome) {
            case BLACKJACK -> wager * 2.5;
            case WIN -> wager * 2;
            case PUSH -> wager;
            case LOSE -> 0;
        };
    }

    /** Insurance is half the original bet. */
    public static double insuranceCost(double bet) {
        return bet / 2;
    }

    /** Chips returned for an insurance bet, including the insurance stake (2:1 when it wins). */
    public static double insurancePayout(double insuranceBet, boolean dealerHasBlackjack) {
        return dealerHasBlackjack ? insuranceBet * 3 : 0;
    }
}
