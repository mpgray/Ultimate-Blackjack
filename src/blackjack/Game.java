package blackjack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck = new Deck();

    public Dealer dealer = new Dealer(deck);
    private List<Player> players = new ArrayList<>();
    public Player player;


    private HandGUI handGUI;

    public Game() {
        this.addPlayer();
        this.player = players.get(0);
    }

    public void start(){
        this.deck.shuffle();
        this.dealer.clear();
        this.player.clear();


        // dealer gets 2 cards
        this.dealer.deal();
        this.dealer.deal();

        // player gets 2 cards
        this.player.deal();
        this.player.deal();

        this.blackJack();
    }

    private void blackJack() {
        if (this.player.hand.total == 21) {

        }
    }

    public JLabel playerLBL() {
        handGUI = new HandGUI(player.hand);
        return handGUI.hand();
    }

    public JLabel dealerLBL() {
        handGUI = new HandGUI(dealer.hand);
        return handGUI.hand();
    }

    public JLabel downCardLBL() {
        handGUI = new HandGUI(dealer.hand);
        return handGUI.downCard();
    }


    private void addPlayer() {
        this.players.add(new Player(deck));
    }

    public boolean hit() {
        player.deal();
        if (player.hand.total > 21) {
            this.done();
            return false;
        }
        return true;
    }

    public boolean stay() {
        this.done();
        return false;
    }

    public boolean onecard() {
        player.deal();
        this.done();
        return false;
    }

    public boolean split() {
        return false;
    }

    public boolean insurance() {
        return true;
    }

    private void done() {
        if (player.hand.total < 22) {
            this.dealer.dealSelf();
        }
    }

    public String results() {
        String results;
        if(player.hand.total == dealer.hand.total) {
            results = push();
        } else if((player.hand.total > dealer.hand.total || dealer.hand.total > 21) && player.hand.total < 22)  {
            results = win();
        } else {
            results = loose();
        }
        return results;
    }

    private String win() {
        player.setStake(5);
        return "You win! Total Chips " + player.getStake();
    }

    private String loose() {
        player.setStake(-5);
        return "You lost. Total Chips " + player.getStake();
    }

    private String push() {
        return "You push. Total Chips " + player.getStake();

    }



}
