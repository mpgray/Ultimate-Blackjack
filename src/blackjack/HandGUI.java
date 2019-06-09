package blackjack;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HandGUI extends JFrame {
    private Hand hand;
    private CardGUI cardGUI = new CardGUI();

    private List<JLabel> cardLBLs = new ArrayList<>();

    HandGUI(Hand hand) {
        this.hand = hand;
    }

    public JLabel hand() {
        JLabel handLBL = new JLabel();


        handLBL.add(this.total());

        int x = 0;
        for (int i = 0; i < hand.hand.size(); i++) {

            JLabel cardLBL;
            cardLBL = cardGUI.card(hand.hand.get(i), 90,125);
            cardLBL.setBounds(x,70, 90, 125);
            cardLBLs.add(cardLBL);
            x += 30;
        }



        int size = cardLBLs.size();
        for(int i = 0; i < size; i++) {
            handLBL.add(cardLBLs.get(i));
        }


        return handLBL;
    }

    private JLabel total() {
        this.setLayout(new GridLayout(1, 1, 5, 5));

        JLabel totalLBL = new JLabel("" + hand.total);
        TitledBorder titled = new TitledBorder("Total");
        totalLBL.setHorizontalAlignment(JLabel.CENTER);

        totalLBL.setForeground(Color.blue);
        totalLBL.setBackground(new Color(60,150,60));
        totalLBL.setOpaque(true);

        totalLBL.setBorder(titled);

        totalLBL.setBounds(15,0, 60, 45);


        return totalLBL;
    }

    public JLabel downCard() {
        JLabel cardLBL = cardGUI.downCard(90, 125);
        cardLBL.setBounds(0, 70, 90, 125);
        return cardLBL;
    }

}
