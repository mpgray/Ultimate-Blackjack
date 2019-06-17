package blackjack.views;

import blackjack.Hand;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandGUI extends JFrame  {
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

    public JLabel hand(int xBound, int yBound, boolean onecard) {
        JLabel handLBL = new JLabel();


        handLBL.add(this.total());

        int x = 0;
        int y = 70;
        for (int i = hand.hand.size() - 2; i >= 0; i--) {
            x = 30 * i;
            JLabel cardLBL;
            cardLBL = cardGUI.card(hand.hand.get(i), 90,125);
            cardLBL.setBounds(x,y, 90, 125);
            cardLBLs.add(cardLBL);


        }
        JLabel cardLBL;
        cardLBL = cardGUI.card(hand.hand.get(2), 90,125);
        cardLBL.setBounds(x+80,5, 90, 125);
        cardLBLs.add(cardLBL);

        int size = cardLBLs.size();
        for(int i = 0; i < size; i++) {
            handLBL.add(cardLBLs.get(i));
        }

        handLBL.setBounds(xBound,yBound, 900, 900);
        return handLBL;
    }

    public JLabel dealerHand(int xBound, int yBound, boolean downCard ) {
        downCardLBL.setBorder(BorderFactory.createEmptyBorder( 60, 0, 0, 0 ));
        downCardLBL.setBackground(new Color(50, 100, 50));
        downCardLBL.setOpaque(true);
        downCardLBL.setBounds(0,0, 90, 205);
        if (downCard == false) {
            hideDownCard();
        }


        JLabel dealerHand = new JLabel();
        dealerHand.add(downCardLBL);
        dealerHand.add(this.hand(0, 0));
        dealerHand.setBounds(xBound,yBound, 900, 900);

        return dealerHand;
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

    private void hideDownCard() {
        downCardLBL.setVisible(false);
    }

}
