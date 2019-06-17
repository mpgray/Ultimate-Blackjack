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
        }
        return false;
    }

    public boolean split() {
        return false;
    }

    public boolean blackJack() {
        if (this.blackjack.player.hand.total == 21 && this.blackjack.dealer.hand.hand.get(1).face != Card.Face.ACE && this.blackjack.dealer.hand.total != 21) {
            return true;
        }

        return false;
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
            if (this.blackjack.dealer.hand.hand.get(1).face == Card.Face.ACE) {
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

    public boolean insurance() {
        double bet = this.blackjack.player.getBet().getTotal();
        double insurance = bet / 2;
        if (this.blackjack.dealer.hand.total == 21) {
            this.blackjack.player.getBet().clear();
            return false;
        }
        this.blackjack.player.adjustStake(-insurance);
        return true;
    }

}
