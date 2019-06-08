package blackjack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HandGUI extends JFrame {
    private Hand hand;
    private CardGUI cardGUI = new CardGUI();

   private List<JLabel> cardLBLs = new ArrayList<>();

    HandGUI(Hand hand) {
        this.hand = hand;
    }

    public List<JLabel> hand() {

        int x = 400;
        int y = 300;

        for (int i = 0; i < hand.hand.size(); i++) {
            JLabel cardLBL = new JLabel();
            cardLBL = cardGUI.card(hand.hand.get(i), 90,125);
            cardLBL.setBounds(x,0, 90, 125);
            cardLBLs.add(cardLBL);
            System.out.println(x);
            x += 20;
        }


        return cardLBLs;


    }

}
