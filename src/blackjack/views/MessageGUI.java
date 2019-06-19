package blackjack.views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MessageGUI extends JFrame {



    private  JLabel lbl = new JLabel();

    public MessageGUI() {
        this.lbl.setFont(new Font("Arial", Font.BOLD, 24));
        TitledBorder titled = new TitledBorder("Message");
        this.lbl.setHorizontalAlignment(JLabel.CENTER);
        this.lbl.setVerticalAlignment(JLabel.CENTER);
        this.lbl.setVisible(false);

       // this.lbl.setForeground(Color.blue);
        this.lbl.setBackground(new Color(60,150,60));
        this.lbl.setOpaque(true);

        this.lbl.setBorder(titled);
        this.lbl.setBounds(100,220,700,72);
    }

    public void popup (String message) {
        this.lbl.setVisible(true);
        this.lbl.setText(message);
    }

    public JLabel getLBL() {
        return this.lbl;
    }

    public void hideLbl() {
        this.lbl.setVisible(false);
    }

}
