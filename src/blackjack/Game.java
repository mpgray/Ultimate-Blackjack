package blackjack;

import blackjack.views.ChatGUI;
import blackjack.views.HandGUI;
import blackjack.views.StakeGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck = new Deck();

    public Dealer dealer = new Dealer(deck);
    private List<Player> players = new ArrayList<>();
    public Player player;
    public JLabel playerLBL = new JLabel();
    public JLabel dealerLBL = new JLabel();

    private JLayeredPane table = new JLayeredPane();

    private ChatGUI chatGUI = new ChatGUI();
    private StakeGUI stakeGUI = new StakeGUI();


    public Message message = new Message();

    public Game() {

        this.addPlayer();
        this.player = players.get(0);
    }

    public JLayeredPane getTable() {
        return table;
    }


    public void start(){
        this.deck.shuffle();
        this.dealer.clear();
        this.player.clear();
        this.player.getBet().clearOnecard();


        // dealer gets 2 cards
        this.dealer.deal();
        this.dealer.deal();

        // player gets 2 cards
        this.player.deal();
        this.player.deal();

        this.updateStake();

        this.table.add(this.playerLBL(),JLayeredPane.MODAL_LAYER);
        this.table.add(this.dealerLBL(true),JLayeredPane.MODAL_LAYER);

    }




    public JLabel playerLBL() {
        HandGUI handGUI = new HandGUI(this.player.hand);
        playerLBL = handGUI.hand(300, 300);
        return playerLBL;
    }

    public JLabel playerLBL(boolean onecard) {
        HandGUI handGUI = new HandGUI(this.player.hand);
        playerLBL = handGUI.hand(300, 300, onecard);
        return playerLBL;
    }

    public JLabel dealerLBL(boolean downCard) {
        HandGUI handGUI = new HandGUI(this.dealer.hand);
        dealerLBL = handGUI.dealerHand(300, 10, downCard);

        return dealerLBL;
    }

    public void reset() {
        this.message.clear();


        this.table.remove(this.dealerLBL);
        this.table.remove(this.playerLBL);

        this.table.invalidate();
        this.table.revalidate();
        this.table.repaint();

    }

    private void addPlayer() {
        this.players.add(new Player(deck));
    }



    public void done() {
        if (player.hand.total < 22) {
            this.dealer.dealSelf();
        }
    }

    public void bust() {
        loose();
    }

    public String results() {
        String results;
        if (player.hand.total == 21 && player.hand.hand.size() ==2) {
            results = blackjack();
        } else if(player.hand.total == dealer.hand.total) {
            results = push();
        } else if((player.hand.total > dealer.hand.total || dealer.hand.total > 21) && player.hand.total < 22)  {
            results = win();
        } else {
            results = loose();
        }
        this.message.setMessage(results);

        return results;
    }

    public void updateStake(){

        if (this.player.getBet().getOnecard() > 0) {
            this.getStakeGUI().setText(this.player.getStake(), this.player.getBet().getTotal(), this.player.getBet().getOnecard());
        } else {
            this.getStakeGUI().setText(this.player.getStake(), this.player.getBet().getTotal());
        }

    }

    public void updateStake(double stake){
        this.player.adjustStake(stake);
        if (this.player.getBet().getOnecard() > 0) {
            this.getStakeGUI().setText(this.player.getStake(), this.player.getBet().getTotal(), this.player.getBet().getOnecard());
        } else {
            this.getStakeGUI().setText(this.player.getStake(), this.player.getBet().getTotal());
        }

    }

    private String formatChips(double chips) {
        if(chips % 1 == 0)
            return "" + (int) chips;
        return String.format("$%.2f", chips) ;
    }

    private String blackjack() {
        this.updateStake(this.player.getBet().getTotal() * 2.5);
        String blackjack =  "Blackjack! You won " + formatChips(this.player.getBet().all() * 1.5) + "!";
        return blackjack;
    }

    private String win() {
        this.updateStake(this.player.getBet().all() *2);
        String won =  "You won " + formatChips(this.player.getBet().all()) + "! Dealer has " + this.dealer.hand.total;
        return won;
    }

    private String loose() {
        String lost = "You lost " +  formatChips(this.player.getBet().all()) +  ". Dealer has " + this.dealer.hand.total;
        return lost;
    }

    private String push() {
        this.updateStake(this.player.getBet().all());
        String push = "You push. Dealer has " + this.dealer.hand.total;
        return push;
    }



    public ChatGUI getChatGUI() {
        return chatGUI;
    }

    public StakeGUI getStakeGUI() {
        return stakeGUI;
    }



}
