package blackjack;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            TableGUI table = new TableGUI(new Game());
            table.setVisible(true);
            table.run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
