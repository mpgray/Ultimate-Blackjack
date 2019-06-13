package blackjack;


import javax.swing.*;

public class Play{

    private Game blackjack;


    public Play(Game game) {
        this.blackjack = game;
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
        if (this.blackjack.player.getBet().total <= this.blackjack.player.getStake()) {
            this.blackjack.player.addBet(this.blackjack.player.getBet().total);
            this.blackjack.player.deal();
        }
        return false;
    }

    public boolean split() {


        return false;
    }

    public void reset() {
        JLayeredPane table = this.blackjack.getTable();

        this.blackjack.getTable().remove(this.blackjack.dealerLBL);
        this.blackjack.getTable().remove(this.blackjack.playerLBL);


        this.blackjack.getTable().invalidate();
        this.blackjack.getTable().revalidate();
        this.blackjack.getTable().repaint();

    }

    public boolean insurance() {
        return true;
    }

}
