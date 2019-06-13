package blackjack.views;

import blackjack.Hand;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HandGUI extends JFrame {
    private Hand hand;
    private CardGUI cardGUI = new CardGUI();
    private JLabel downCardLBL = cardGUI.downCard(90, 125);

    private List<JLabel> cardLBLs = new ArrayList<>();

    public HandGUI(Hand hand) {
        this.hand = hand;
    }

    public JLabel hand(int xBound, int yBound) {
        JLabel handLBL = new JLabel();


        handLBL.add(this.total());

        int x = 0;
        for (int i = hand.hand.size() - 1; i >= 0; i--) {
            x = 30 * i;
            JLabel cardLBL;
            cardLBL = cardGUI.card(hand.hand.get(i), 90,125);
            cardLBL.setBounds(x,70, 90, 125);
            cardLBLs.add(cardLBL);

        }

        int size = cardLBLs.size();
        for(int i = 0; i < size; i++) {
            handLBL.add(cardLBLs.get(i));
        }

        handLBL.setBounds(xBound,yBound, 900, 900);
        return handLBL;
    }

    public JLabel dealerHand(int xBound, int yBound ){
        downCardLBL.setBorder(BorderFactory.createEmptyBorder( 60, 0, 0, 0 ));
        downCardLBL.setBackground(new Color(50, 100, 50));
        downCardLBL.setOpaque(true);
        downCardLBL.setBounds(0,0, 90, 205);

        JLabel handLBL = new JLabel();
        handLBL.add(downCardLBL);
        handLBL.setBounds(xBound,yBound, 900, 900);
        handLBL.add(this.hand(0, 0));
        return handLBL;
    }

    public void toggleDownCard(boolean set) {

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
