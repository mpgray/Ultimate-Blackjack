package blackjack;

public class Card {
    public Face face;
    public Suit suit;
    public int value;

    enum Suit {
        HEARTS('\u2665', "heart"), DIAMONDS('\u2666', "diamond"), CLUBS('\u2663', "club"), SPADES('\u2660', "spade");

        char value;
        String name;
        private Suit(char value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    enum Face {
        TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
        EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), JACK(10, "J"), QUEEN(10, "Q"),
        KING(10, "K"), ACE(11, "A");

        int value;
        String name;
        private Face(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public Card(Face face, Suit suit) {
        this.suit = suit;
        this.face = face;
        this.value = face.value;
    }
}
