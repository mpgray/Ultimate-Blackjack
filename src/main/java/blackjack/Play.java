package blackjack;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Play{

    private Game blackjack;

    private boolean hit;
    private boolean stay;
    private boolean onecard;
    private boolean split;
    private boolean insurance;

    public Play(Game game) {
        this.blackjack = game;

        this.hit = false;
        this.stay = false;
        this.onecard = false;
        this.split = false;
        this.insurance = false;
    }

    public boolean hit() {
        this.blackjack.player.deal();
        if (this.blackjack.player.hand.total > 21) {
            return false;
        }
        return true;
    }



    public boolean stay() {
        return false;
    }

    public boolean onecard() {
        if (this.blackjack.player.getBet().getTotal() <= this.blackjack.player.getStake()) {
            this.blackjack.player.getBet().setOnecard(this.blackjack.player.getBet().getTotal());

            this.blackjack.player.adjustStake(-this.blackjack.player.getBet().getOnecard());
            this.blackjack.player.deal();

            return false;
        } else {
            this.blackjack.getChatGUI().print("Not enough chips to double.");
        }
        return true;

    }

    public boolean split() {
        return false;
    }

    /**
     * A player blackjack ends the round at once unless the dealer shows an ace,
     * in which case the player is still offered insurance first. A dealer
     * blackjack in the hole no longer lets the player keep hitting a natural:
     * the round ends and {@link Rules#outcome} scores it as a push.
     */
    public boolean blackJack() {
        return Rules.isBlackjack(this.blackjack.player.hand)
                && this.blackjack.dealer.upCard().face != Card.Face.ACE;
    }


    public void options() {
        List<Card> hand = blackjack.player.hand.hand;
        int total = blackjack.player.hand.total;

        this.hit = false;
        this.stay = false;
        this.onecard = false;
        this.split = false;
        this.insurance = false;

        if (hand.size() == 2) {
            if (hand.get(0).value == hand.get(1).value) {
                split = true;
            }
            if (this.blackjack.dealer.upCard().face == Card.Face.ACE) {
                insurance = true;
            }
            this.onecard = true;
        }

        if (total < 22 && hand.size() > 0) {
            this.hit = true;
            this.stay = true;
        }
    }

    public boolean canHit() {
        return hit;
    }

    public boolean canStay() {
        return stay;
    }

    public boolean canDouble() {
        return onecard;
    }

    public boolean canSplit() {
        return split;
    }

    public boolean canInsurance() {
        return insurance;
    }

    public double insuranceCost() {
        return Rules.insuranceCost(this.blackjack.player.getBet().getTotal());
    }

    public boolean canAffordInsurance() {
        return insuranceCost() <= this.blackjack.player.getStake();
    }

    /**
     * Takes insurance. Returns false when the dealer has blackjack and the round
     * is over; the insurance then pays 2:1, which offsets the lost main bet.
     * (It used to clear the bet without paying anything, so an insured player
     * still lost the whole wager.)
     */
    public boolean insurance() {
        double insurance = insuranceCost();
        this.blackjack.player.adjustStake(-insurance);
        boolean dealerBlackjack = Rules.isBlackjack(this.blackjack.dealer.hand);
        this.blackjack.player.adjustStake(Rules.insurancePayout(insurance, dealerBlackjack));
        return !dealerBlackjack;
    }

}
