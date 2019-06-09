package blackjack;

import javax.swing.*;
import java.awt.*;

public class CardGUI extends JFrame {



    public CardGUI() {

    }

    public JLabel downCard(int width, int height) {
        JLabel cardLBL = new JLabel();
        String path = "cards/downHole.png";
        cardLBL.setIcon(scale(createImageIcon(path), width, height));
        return cardLBL;
    }

    public JLabel card(Card card, int width, int height) {
        JLabel cardLBL = new JLabel();
        String path = "cards/" + card.face.name + "-" + card.suit.name + ".png";
        cardLBL.setIcon(scale(createImageIcon(path), width, height));
        return cardLBL;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CardGUI.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private ImageIcon scale(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

        return new ImageIcon(newimg);  // transform it back
    }


}
