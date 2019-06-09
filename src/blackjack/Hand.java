package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> hand = new ArrayList<>();
    public short total;
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

        if (ace > 0) {
            if (this.total > 21) {
                ace--;
                this.total -= 10;
            }
        }
    }


    public void muck() {
        this.hand.clear();
        this.total = 0;
        this.ace = 0;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (int i = 1; i < hand.size(); i++) {
            display.append("[").append(hand.get(i).face.name).append(hand.get(i).suit.value).append("]");
        }
        return display + "";
    }
}
