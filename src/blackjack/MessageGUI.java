package blackjack;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MessageGUI extends JFrame {
    public  JLabel messageAlert = new JLabel();

    MessageGUI() {

    }

    public JLabel message (String message) {
        messageAlert.setFont(new Font("Arial", Font.BOLD, 26));
        //TitledBorder titled = new TitledBorder("Message");
        messageAlert.setHorizontalAlignment(JLabel.CENTER);
        messageAlert.setVerticalAlignment(JLabel.CENTER);

        //messageAlert.setForeground(Color.blue);
       // messageAlert.setBackground(new Color(60,150,60));
       // messageAlert.setOpaque(true);

        //messageAlert.setBorder(titled);
        messageAlert.setBounds(150,220,600,70);
        messageAlert.setText(message);
        return messageAlert;
    }

}
