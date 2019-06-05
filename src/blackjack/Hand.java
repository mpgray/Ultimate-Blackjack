package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> hand = new ArrayList<>();
    private short total;
    public int ace;

    public Hand(){
        this.total = 0;
        this.ace = 0;
    }

    public void deal(Card card) {
        hand.add(card);
        if (card.face == Card.Face.ACE) {
            ace++;
        }
        this.total += card.value;
    }

    public short total(){
        if (ace > 1 && total > 21) {
            ace--;
            total -= 10;
        }
        return this.total;
    }

    public void muck() {
        hand.clear();
        total = 0;
        ace = 0;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            display.append("[").append(hand.get(i).face).append(hand.get(i).suit.value).append("(").append(hand.get(i).value).append(")]");
        }
        return display + "";
    }
}
