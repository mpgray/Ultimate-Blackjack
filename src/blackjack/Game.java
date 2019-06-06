package blackjack;

public class Game {

    private Deck deck = new Deck();
    public Hand dealer = new Hand();
    public Hand player = new Hand();

    public Game() {

    }

    public void start(){
        deck.shuffle();
        dealer.muck();
        player.muck();

        // dealer gets 2 cards
        dealer.deal(deck.draw());
        dealer.deal(deck.draw());

        // player gets 2 cards
        player.deal(deck.draw());
        player.deal(deck.draw());
    }

    public void hit() {
        player.deal(deck.draw());
    }

}
