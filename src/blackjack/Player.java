package blackjack;

public class Player {
    public Hand hand = new Hand();
    public Chip chip = new Chip();
    public Bet bet = new Bet();
    private Deck deck;

    Player(Deck deck) {
        this.deck = deck;
    }

    public void clear() {
        hand.muck();
    }

    public void deal(){
        hand.deal(deck.draw());
    }

    @Override
    public String toString() {
        return "(" + hand.total + ") "+ "[" + hand.hand.get(0).face.name + hand.hand.get(0).suit.value + "]" + hand.toString() ;
    }
}
