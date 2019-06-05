package blackjack;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand player = new Hand();
        System.out.println(deck.toString());

        // This deals 1 card from deck to player.
        player.deal(deck.draw());
        player.deal(deck.draw());

        System.out.println(player.toString());
        System.out.println(deck.toString());
        System.out.println(player.total());

        try {
            TableGUI table = new TableGUI();
            table.setVisible(true);
            table.print(player.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
