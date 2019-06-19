package blackjack.views;

import blackjack.Game;
import blackjack.Play;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class PlayGUI {
    JLayeredPane table;

    private JLabel hideBetLBL = new JLabel();
    private JButton hitButton, dealButton, stayButton, doubleButton, splitButton, insuranceButton;
    private Game blackjack;
    private Play play;

    private JLabel  sidePanel = new JLabel();
    private ChatGUI chatGUI;

    public PlayGUI(Game blackjack) {
        this.blackjack  = blackjack;
        this.table = this.blackjack.getTable();

        this.play = new Play(this.blackjack);
        this.hitButton();


        this.chatGUI = this.blackjack.getChatGUI();

    }

    private void canBet(boolean showBets) {

        hideBetLBL.setBounds(800,0,200,575);
        hideBetLBL.setOpaque(!showBets);
        hideBetLBL.setBackground(new Color(50, 100, 50));
        this.table.add(hideBetLBL, JLayeredPane.MODAL_LAYER);
    }


    private void hitButton(){
        hitButton = new JButton(" Hit ");
        hitButton.setBounds(15, 60, 100, 25);
        hitButton.addActionListener(evt -> {
            if(!this.play.hit()) {
                this.endTurn();
            }

            this.turn();
            this.playerAction();

        });
    }


    private void options() {
        this.play.options();
        hitButton.setVisible(this.play.canHit());
        stayButton.setVisible(this.play.canStay());
        doubleButton.setVisible(this.play.canDouble());
        splitButton.setVisible(this.play.canSplit());
        insuranceButton.setVisible(this.play.canInsurance());
    }

    private void turn() {
        this.options();
        this.table.remove(this.blackjack.playerLBL);

        this.table.invalidate();
        this.table.revalidate();
        this.table.repaint();

        this.blackjack.updateStake();
        this.chatGUI.systemText(this.blackjack.player);

    }

    private void playerAction () {
        this.table.add(this.blackjack.playerLBL(), JLayeredPane.MODAL_LAYER);
    }

    private void playerAction (boolean onecard) {
        this.table.add(this.blackjack.playerLBL(onecard), JLayeredPane.MODAL_LAYER);
    }

    private void dealerTurn() {

        this.table.remove(this.blackjack.dealerLBL);

        this.table.invalidate();
        this.table.revalidate();
        this.table.repaint();


        this.table.add(this.blackjack.dealerLBL(false));
        chatGUI.systemText(blackjack.dealer);


    }

    private void endTurn() {

        this.blackjack.done();
        this.dealerTurn();
        this.canBet(true);

        this.chatGUI.print(this.blackjack.results());
        dealButton.setVisible(true);

        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);
    }

    public JLabel sidePanel(){


        dealButton = new JButton(" Deal ");
        dealButton.setBounds(15, 35, 100, 25);
        dealButton.addActionListener(evt -> {
            if(this.blackjack.player.getBet().getTotal() > this.blackjack.player.getStake()){
                this.blackjack.player.getBet().clear();
                this.blackjack.getChatGUI().print("Not enough chips.");
            } else if (this.blackjack.player.getBet().getTotal() > 0) {
                this.blackjack.player.bet();
                this.blackjack.reset();
                this.blackjack.start();
                this.options();
                this.canBet(false);
                chatGUI.systemText(this.blackjack.dealer);
                chatGUI.systemText(blackjack.player);

                dealButton.setVisible(false);
                if(this.play.blackJack()) {
                    this.endTurn();
                    dealButton.setVisible(true);
                    this.blackjack.message.setMessage("Blackjack!");
                }
            } else {
                this.blackjack.message.setMessage("Bet table minimum to play.");
            }

        });

        stayButton = new JButton(" Stay ");
        stayButton.setBounds(15, 85, 100, 25);
        stayButton.addActionListener(evt -> {
             if(!play.stay()) {
                this.endTurn();
             }

        });

        doubleButton = new JButton(" Double ");
        doubleButton.setBounds(15, 110, 100, 25);
        doubleButton.addActionListener(evt -> {
            if(!play.onecard()){
                this.turn();
                this.playerAction(true);
                this.endTurn();
            }
        });

        splitButton = new JButton(" Split ");
        splitButton.setBounds(15, 135, 100, 25);
        splitButton.setEnabled(false);
        splitButton.addActionListener(evt -> {
          //  if(!blackjack.split()){
         //       this.endTurn();
          //  }
         //   chatGUI.systemText(blackjack.player);
        });

        insuranceButton = new JButton(" Insurance ");
        insuranceButton.setBounds(15, 160, 140, 25);
        insuranceButton.addActionListener(evt -> {
              if(!play.insurance()) {
                  this.endTurn();
             }
            this.blackjack.message.setMessage("Dealer doesn't have blackjack.");
            insuranceButton.setVisible(false);
        });


        sidePanel.add(dealButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(hitButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(stayButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(doubleButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(splitButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(insuranceButton,JLayeredPane.MODAL_LAYER);
        this.options();

        TitledBorder titled = new TitledBorder("Play");
        sidePanel.setBorder(titled);
        sidePanel.setBounds(0,0, 125, 300);

        return sidePanel;
    }




}
