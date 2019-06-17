package blackjack.views;

import blackjack.Chip;
import blackjack.Game;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BetGUI {

    private JLabel stakeLBL;
    private JLabel betTotalLBL = new JLabel();

    private Chip chips;

    private Game blackjack;

    private final int CHIP_WIDTH = 75;
    private final int CHIP_HEIGHT = 75;

    private int chipPosition;

    public BetGUI(Game game) {
        this.blackjack = game;
        this.stakeLBL = this.blackjack.getStakeGUI().stake();
        this.chips = new Chip();
        this.bet();

        this.chipPosition = 75;
    }

    public JLabel betPanel() {
        JLabel betLBL = new JLabel();

        betLBL.add(this.chip(1));
        this.chipPosition += CHIP_HEIGHT + 3;

        betLBL.add(this.chip(5));
        this.chipPosition += CHIP_HEIGHT + 3;

        betLBL.add(this.chip(25));
        this.chipPosition += CHIP_HEIGHT + 3;

        betLBL.add(this.chip(100));
        this.chipPosition += CHIP_HEIGHT + 3;

        betLBL.add(this.chip(500));
        this.chipPosition += CHIP_HEIGHT + 3;

        betLBL.add(this.clear());
        betLBL.add(betTotalLBL);

        betLBL.setBounds(690,0,415,575);

        this.stakeLBL.setText(this.blackjack.player.getStake() + "");
        betLBL.add(stakeLBL);
        //betLBL.add(betButton);
        return betLBL;
    }

    public void bet() {

        betTotalLBL.setFont(new Font("Arial", Font.BOLD, 18));
        TitledBorder titled = new TitledBorder("Bet");
        betTotalLBL.setHorizontalAlignment(JLabel.CENTER);
        betTotalLBL.setVerticalAlignment(JLabel.CENTER);
        betTotalLBL.setVisible(true);

        // this.lbl.setForeground(Color.blue);
        betTotalLBL.setBackground(new Color(60,150,60));
        betTotalLBL.setOpaque(true);
        betTotalLBL.setText("0");
        betTotalLBL.setBorder(titled);
        betTotalLBL.setBounds(0,395,100,60);

    }

    private JButton clear(){
        JButton clearButton = new JButton(" Clear Bet ");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBounds(60, this.chipPosition + 5, 135, 40);
        clearButton.addActionListener(evt -> {
            this.blackjack.player.getBet().clear();
            this.blackjack.updateStake();
            betTotalLBL.setText(this.blackjack.player.getBet().getTotal() + "");

        });
        return clearButton;
    }

    private JButton chip(int chipValue) {

        JButton chipButton = new JButton("" + chipValue, new ChipGUI(chipValue).chip());
        chipButton.setEnabled(true);
        chipButton.setBorder(BorderFactory.createEmptyBorder());
        chipButton.setContentAreaFilled(false);
        chipButton.setBorderPainted(false);
        chipButton.setForeground(new Color(117, 118, 113));
        chipButton.setFont(new Font("Arial", Font.BOLD, 22));
        chipButton.setVerticalTextPosition(AbstractButton.CENTER);
        chipButton.setHorizontalTextPosition(AbstractButton.CENTER);
        chipButton.setBounds(120,this.chipPosition, this.CHIP_WIDTH, this.CHIP_HEIGHT);
        chipButton.setOpaque(false);
        chipButton.addActionListener(evt -> {
            this.blackjack.player.bet(chipValue);
            this.blackjack.updateStake();
            betTotalLBL.setText(this.blackjack.player.getBet().getTotal() + "");

        });

        return chipButton;
    }



}
