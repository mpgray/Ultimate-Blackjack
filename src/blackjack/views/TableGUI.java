package blackjack.views;

import blackjack.Card;
import blackjack.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableGUI extends JFrame {
    private JLayeredPane contentPane;
    private JButton hitButton, dealButton, stayButton, doubleButton, splitButton, insuranceButton, betButton;
    private Game blackjack;

    private JLabel stakeLBL = new JLabel();
    private JLabel dealerHandLBL = new JLabel();
    private JLabel downCardLBL = new JLabel();
    private JLabel handLBL = new JLabel();
    private ChatGUI chatGUI = new ChatGUI();


    public TableGUI(Game blackjack){

        this.blackjack = blackjack;
        this.create();
        this.move();

        this.setTitle("Ultimate Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 905, 710);
        this.setContentPane(contentPane);
    }


    private void create(){
        contentPane = new JLayeredPane();
        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(50, 100, 50));
        contentPane.setLayout(null);
        contentPane.setOpaque(true);


        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);
        stakeLBL.setBounds(725,350, 175, 30);
        contentPane.add(stakeLBL, JLayeredPane.MODAL_LAYER);

        contentPane.add(this.blackjack.messageLBL("Welcome to Blackjack"), JLayeredPane.POPUP_LAYER);
        contentPane.add(chatGUI.chat(), JLayeredPane.MODAL_LAYER);
    }

    private void move(){

        dealButton = new JButton(" Deal ");
        dealButton.setBounds(15, 35, 100, 25);
        dealButton.setEnabled(false); // for testing
        dealButton.addActionListener(evt -> {
            blackjack.start();
            this.beginGame();
            this.hand();
            chatGUI.systemText(blackjack.player);
        });

        hitButton = new JButton(" Hit ");
        hitButton.setBounds(15, 60, 100, 25);
        hitButton.addActionListener(evt -> {
            if(!blackjack.hit()) {
                this.endTurn();
            }
            doubleButton.setVisible(false);
            this.hand();
            chatGUI.systemText(blackjack.player);
        });

        stayButton = new JButton(" Stay ");
        stayButton.setBounds(15, 85, 100, 25);
        stayButton.addActionListener(evt -> {
            if(!blackjack.stay()) {
                this.endTurn();
            }
            chatGUI.systemText(blackjack.player);
        });

        doubleButton = new JButton(" Double ");
        doubleButton.setBounds(15, 110, 100, 25);
        doubleButton.addActionListener(evt -> {
            if(!blackjack.onecard()){
                this.endTurn();
            }
            this.hand();
            chatGUI.systemText(blackjack.player);
        });

        splitButton = new JButton(" Split ");
        splitButton.setBounds(15, 135, 100, 25);
        splitButton.setEnabled(false);
        splitButton.addActionListener(evt -> {
            if(!blackjack.split()){
                this.endTurn();
            }
            chatGUI.systemText(blackjack.player);
        });

        insuranceButton = new JButton(" Insurance ");
        insuranceButton.setBounds(15, 160, 140, 25);
        insuranceButton.setEnabled(false);
        insuranceButton.addActionListener(evt -> {
            if(!blackjack.insurance()) {
                this.endTurn();
            }
            chatGUI.systemText(blackjack.player);
        });

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
            this.hand();
            chatGUI.systemText(blackjack.player);
        });


        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);

        contentPane.add(dealButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(hitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(stayButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(doubleButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(splitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(insuranceButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(betButton,JLayeredPane.DEFAULT_LAYER);
    }

    private void beginGame() {

        dealButton.setVisible(false);
        betButton.setVisible(false);
        hitButton.setVisible(true);
        stayButton.setVisible(true);
        doubleButton.setVisible(true);

        this.blackjack.messageLBL.messageAlert.setText("");

        downCardLBL = blackjack.downCardLBL();
        downCardLBL.setBorder(BorderFactory.createEmptyBorder( 80 , 0, 0, 0 ));
        downCardLBL.setBackground(new Color(50, 100, 50));
        downCardLBL.setOpaque(true);
        downCardLBL.setBounds(300,0, 90, 205);
        contentPane.add(downCardLBL, JLayeredPane.MODAL_LAYER);

        if (blackjack.player.hand.hand.get(0).value == blackjack.player.hand.hand.get(1).value) {
            splitButton.setVisible(true);
        }

        if (blackjack.dealer.hand.hand.get(1).face == Card.Face.ACE) {
            insuranceButton.setVisible(true);
        } else {
            if(blackjack.player.hand.total == 21) {
                this.endTurn();
            }
        }

        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);
    }

    private void endTurn(){

        dealButton.setVisible(true);
        betButton.setVisible(true);
        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);

        contentPane.remove(downCardLBL);
        this.dealerTurn();

        chatGUI.print(blackjack.results());
        stakeLBL.setText("Chips: " + blackjack.player.getStake() + " Bet: " + blackjack.player.getBet().total);

        this.blackjack.player.clearBet();
    }

    private void dealerTurn() {

        contentPane.remove(dealerHandLBL);

        dealerHandLBL = blackjack.dealerLBL();
        dealerHandLBL.setBounds(300,10, 900, 900);
        contentPane.add(dealerHandLBL);


        chatGUI.systemText(blackjack.dealer);
    }


    private void hand() {

        this.dealerTurn();

        contentPane.remove(handLBL);


        contentPane.revalidate();
        contentPane.repaint();

        handLBL = blackjack.playerLBL();
        handLBL.setBounds(300,300, 900, 900);
        contentPane.add(handLBL);

    }

    public void run() {


        Runnable gameOn = () -> {


        }; new Thread(gameOn).start();
    }
}
