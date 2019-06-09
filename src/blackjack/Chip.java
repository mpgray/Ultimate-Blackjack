package blackjack;

public class Chip {

    public Chips value;

    enum Chips {
        PINK(.5), WHITE(1), RED(5), GREEN(25), BLACK(100), PURPLE(500), YELLOW(1000), BROWN(5000);

        double value;
        private Chips(double value) {
            this.value = value;
        }
    }

}
