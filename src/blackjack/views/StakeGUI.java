package blackjack.views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StakeGUI {

    private JLabel stakeLBL = new JLabel();

    public JLabel stake() {
        this.stakeLBL.setFont(new Font("Arial", Font.BOLD, 18));
        TitledBorder titled = new TitledBorder("Chips");
        this.stakeLBL.setHorizontalAlignment(JLabel.CENTER);
        this.stakeLBL.setVerticalAlignment(JLabel.CENTER);
        this.stakeLBL.setVisible(true);

        this.stakeLBL.setForeground(Color.blue);
        this.stakeLBL.setBackground(new Color(60,150,60));
        this.stakeLBL.setOpaque(true);

        this.stakeLBL.setBorder(titled);
        this.stakeLBL.setBounds(0,10,105,60);

        return stakeLBL;
    }

 //   public JLabel stake() {
//        stakeLBL.setText("Buy In");
//        stakeLBL.setBounds(0,385, 400, 30);
//        return stakeLBL;
//    }

    public void setText(double setText) {
        stakeLBL.setText(setText + "");
    }

    public void setText(double stake, double bet) {
        stakeLBL.setText(stake + "");
    }

    public void setText(double stake, double bet, double onecard) {
        stakeLBL.setText(stake + "");
    }

}
