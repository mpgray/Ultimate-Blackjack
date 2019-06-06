package blackjack;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            TableGUI table = new TableGUI();
            table.setVisible(true);
            table.game();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
