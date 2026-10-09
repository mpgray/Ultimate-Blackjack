package blackjack;

import blackjack.views.BetGUI;
import blackjack.views.PlayGUI;
import blackjack.views.TableGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        // Swing components must be created on the event dispatch thread.
        SwingUtilities.invokeLater(() -> {
            try {
                Game game = new Game();
                PlayGUI playGUI = new PlayGUI(game);
                BetGUI betGui = new BetGUI(game);
                TableGUI table = new TableGUI(game, playGUI, betGui);

                table.setVisible(true);
                table.run();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
