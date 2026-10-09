package blackjack.views;

import javax.swing.*;

public class ChipGUI {

    private double chip;

    ChipGUI(double chip) {
        this.chip = chip;
    }

    public ImageIcon chip() {
        ImageIcon betLBL = new ImageIcon();
        String path = "chips/" + (int) chip + ".png";
        return createImageIcon(path);
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
}
