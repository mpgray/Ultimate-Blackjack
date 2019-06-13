package blackjack.views;

import blackjack.Game;
import blackjack.Play;


import javax.swing.*;
import javax.swing.border.TitledBorder;


public class PlayGUI {
    JLayeredPane table;

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

    private void hitButton(){
        hitButton = new JButton(" Hit ");
        hitButton.setBounds(15, 60, 100, 25);
        hitButton.addActionListener(evt -> {
            if(!this.play.hit()) {
                this.blackjack.bust();
                this.hide();
                this.play.reset();
            }
            this.turn();
            this.chatGUI.systemText(this.blackjack.player);

        });
    }

    private void hide() {
        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);
    }



    private void turn() {

        this.table.remove(this.blackjack.playerLBL);

        this.table.invalidate();
        this.table.revalidate();
        this.table.repaint();

        this.table.add(this.blackjack.playerLBL());

    }

    public JLabel sidePanel(){


        dealButton = new JButton(" Deal ");
        dealButton.setBounds(15, 35, 100, 25);
        dealButton.setEnabled(false); // for testing
        dealButton.addActionListener(evt -> {
        //    blackjack.start();
         //   this.beginGame();
         //   this.hand();
         //   chatGUI.systemText(blackjack.player);
        });

        stayButton = new JButton(" Stay ");
        stayButton.setBounds(15, 85, 100, 25);
        stayButton.addActionListener(evt -> {
        //    if(!blackjack.stay()) {
         //       this.endTurn();
         //   }
         //   chatGUI.systemText(blackjack.player);
        });

        doubleButton = new JButton(" Double ");
        doubleButton.setBounds(15, 110, 100, 25);
        doubleButton.addActionListener(evt -> {
         //   if(!blackjack.onecard()){
         //       this.endTurn();
         //   }
         //   this.hand();
         //   chatGUI.systemText(blackjack.player);
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
        insuranceButton.setEnabled(false);
        insuranceButton.addActionListener(evt -> {
         //   if(!blackjack.insurance()) {
          //      this.endTurn();
          //  }
          //  chatGUI.systemText(blackjack.player);
        });


        sidePanel.add(dealButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(hitButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(stayButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(doubleButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(splitButton,JLayeredPane.MODAL_LAYER);
        sidePanel.add(insuranceButton,JLayeredPane.MODAL_LAYER);


        TitledBorder titled = new TitledBorder("Play");
        sidePanel.setBorder(titled);
        sidePanel.setBounds(0,0, 125, 300);

        return sidePanel;
    }

}
