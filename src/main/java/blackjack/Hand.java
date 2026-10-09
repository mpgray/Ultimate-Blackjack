package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> hand = new ArrayList<>();
    public short total;
    /** Aces in this hand still counted as 11. Non-zero means the total is soft. */
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

        // Count aces as 1 instead of 11 for as long as the hand would otherwise
        // bust. A single "if" here turned soft 21 + ace (e.g. A-5-5-A) into a
        // bust 22 instead of 12.
        while (ace > 0 && this.total > 21) {
            ace--;
            this.total -= 10;
        }
    }

    /** True when an ace is being counted as 11. */
    public boolean isSoft() {
        return ace > 0;
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
