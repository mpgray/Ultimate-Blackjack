package blackjack.views;

import blackjack.Card;
import blackjack.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableGUI extends JFrame {

    private JLayeredPane contentPane;


    private Game blackjack;

    private JButton betButton;

    private JLabel stakeLBL = new JLabel();


    private ChatGUI chatGUI;
    private PlayGUI playGUI;


    public TableGUI(Game blackjack, PlayGUI playGUI){
        this.blackjack = blackjack;
        this.contentPane = this.blackjack.getTable();
        this.playGUI = playGUI;

        this.chatGUI = this.blackjack.getChatGUI();

        this.create();
        this.move();

        this.setTitle("Ultimate Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 905, 710);
        this.setContentPane(contentPane);
    }

    private void move() {


        betButton = new JButton("5", new ChipGUI(5).chip());
        betButton.setEnabled(true);
        // betButton.setBorder(BorderFactory.createEmptyBorder());
        betButton.setContentAreaFilled(false);
        //betButton.setBorderPainted(false);
        betButton.setForeground(new Color( 215,216,211));
        betButton.setFont(new Font("Arial", Font.PLAIN, 32));
        betButton.setVerticalTextPosition(AbstractButton.CENTER);
        betButton.setHorizontalTextPosition(AbstractButton.CENTER);
        betButton.setBounds(770,395,115,115);
        // betButton.setOpaque(false);
        betButton.addActionListener(evt -> {
             blackjack.player.setBet(blackjack.player.getBet().total + 5);

             blackjack.start();
             this.beginGame();
             chatGUI.systemText(blackjack.player);
        });

        contentPane.add(betButton,JLayeredPane.DEFAULT_LAYER);
    }

    private void create(){

        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(50, 100, 50));
        contentPane.setLayout(null);
        contentPane.setOpaque(true);

        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);
        stakeLBL.setBounds(725,350, 175, 30);
        contentPane.add(stakeLBL, JLayeredPane.MODAL_LAYER);

        contentPane.add(this.blackjack.message.popupLBL(), JLayeredPane.POPUP_LAYER);
        contentPane.add(chatGUI.chat(), JLayeredPane.MODAL_LAYER);
        contentPane.add(playGUI.sidePanel(),JLayeredPane.PALETTE_LAYER);
    }


    private void beginGame() {

        this.blackjack.message.clear();

        contentPane.add(this.blackjack.dealerLBL(), JLayeredPane.MODAL_LAYER);
        contentPane.add(this.blackjack.playerLBL(), JLayeredPane.MODAL_LAYER);

        if (blackjack.player.hand.hand.get(0).value == blackjack.player.hand.hand.get(1).value) {
     //       splitButton.setVisible(true);
        }

        if (blackjack.dealer.hand.hand.get(1).face == Card.Face.ACE) {
//            insuranceButton.setVisible(true);
        } else {
            if(blackjack.player.hand.total == 21) {
                this.endTurn();
            }
        }

        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);
    }

    private void endTurn(){


        chatGUI.print(blackjack.results());
        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);

        this.blackjack.player.clearBet();
    }


    public Game getBlackjack() {
        return blackjack;
    }

    public void run() {


        Runnable gameOn = () -> {


        }; new Thread(gameOn).start();
    }
}

