package blackjack;

import blackjack.views.ChatGUI;
import blackjack.views.HandGUI;

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


        // dealer gets 2 cards
        this.dealer.deal();
        this.dealer.deal();

        // player gets 2 cards
        this.player.deal();
        this.player.deal();

        this.blackJack();
    }

    private void blackJack() {
        if (this.player.hand.total == 21 && this.dealer.hand.hand.get(1).face != Card.Face.ACE && this.dealer.hand.total != 21) {
            this.player.setStake(this.player.getBet().total * .5);

        }
    }



    public JLabel playerLBL() {
        HandGUI handGUI = new HandGUI(player.hand);
        playerLBL = handGUI.hand(300, 300);
        return playerLBL;
    }

    public JLabel dealerLBL() {
        HandGUI handGUI = new HandGUI(dealer.hand);
        dealerLBL = handGUI.dealerHand(300, 10);
        return dealerLBL;
    }

    public JLabel downCardLBL() {
        HandGUI handGUI = new HandGUI(dealer.hand);
        return handGUI.downCard();
    }


    private void addPlayer() {
        this.players.add(new Player(deck));
    }



    private void done() {
        if (player.hand.total < 22) {
            this.dealer.dealSelf();
        }
    }

    public void bust() {
        loose();
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

    private String formatChips(double chips) {
        if(chips % 1 == 0)
            return "" + (int) chips;
        return String.format("%.2f", chips) ;
    }

    private String win() {
        player.setStake(this.player.getBet().total *2);
        String won = "You won " + formatChips(this.player.getBet().total) + "! Total Chips " + formatChips(player.getStake());
        this.message.setMessage(won);
        return won;
    }

    private String loose() {
        String lost = "You lost " +  formatChips(this.player.getBet().total) + " Total Chips " + formatChips(player.getStake());
        this.message.setMessage(lost);
        return lost;
    }

    private String push() {
        player.setStake(this.player.getBet().total);
        String push = "You push. Total Chips " + player.getStake();
        this.message.setMessage(push);
        return push;
    }



    public ChatGUI getChatGUI() {
        return chatGUI;
    }

}
