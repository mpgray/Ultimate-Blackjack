package blackjack;



public class Dealer{
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

    public void dealSelf() {
        reveal();
        while (Rules.dealerShouldHit(hand)) {
            this.deal();
        }
    }

    /** Turns the hole card over without drawing, e.g. when the player has already busted. */
    public void reveal() {
        isHoleVisable = true;
    }

    /** The face-up card; the hole card is index 0. */
    public Card upCard() {
        return hand.hand.get(1);
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
