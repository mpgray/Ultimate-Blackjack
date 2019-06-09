package blackjack;

public class Dealer {
    public Hand hand = new Hand();
    private Deck deck;
    private boolean isHoleVisable = false;


    Dealer(Deck deck) {
        this.deck = deck;
    }

    public void deal(){
        hand.deal(deck.draw());
    }

    public void clear() {
        hand.muck();
        isHoleVisable = false;
    }

    public void dealSelf(){
        isHoleVisable = true;
        while(hand.total < 17 || hand.ace > 0 && hand.total == 17) {
            deal();
        }
    }

    private String holeCard() {
        if (!isHoleVisable) {
            return "(" + hand.hand.get(1).value + ") [**]";
        }
        return "(" + hand.total + ") " + "[" + hand.hand.get(0).face.name + hand.hand.get(0).suit.value + "]";
    }

    @Override
    public String toString() {
        if (hand.hand.size() > 0) {
            return holeCard() + hand.toString();
        }
        return "";
    }

}
