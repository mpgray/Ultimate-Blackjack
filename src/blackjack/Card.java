package blackjack;

public class Card {
    public Face face;
    public Suit suit;
    public int value;

    enum Suit {
        HEARTS('\u2665'), DIAMONDS('\u2666'), CLUBS('\u2663'), SPADES('\u2660');

        char value;

        private Suit(char value) {
            this.value = value;
        }
    }

    enum Face {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        int value;
        private Face(int value) {
            this.value = value;
        }
    }

    public Card(Face face, Suit suit) {
        this.suit = suit;
        this.face = face;
        this.value = face.value;
    }
}
