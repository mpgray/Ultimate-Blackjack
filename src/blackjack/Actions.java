package blackjack;

public class Actions {
    public enum Suit {
        HIT(0), STAY(1), DOUBLE(2), SPLIT(3);

        int value;
        private Suit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
