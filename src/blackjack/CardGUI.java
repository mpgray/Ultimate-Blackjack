package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardGUI extends JFrame {

    private JLabel cardLBL;

    public CardGUI() {
        this.cardLBL = new JLabel();

    }

    public JLabel card(Card card, int width, int height) {
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
