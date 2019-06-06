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
        if (player.total > 21) {
            this.done();
        }
    }

    public void stay() {
        this.done();
    }

    public void onecard() {
        player.deal(deck.draw());
        this.done();
    }

    public void split() {

    }

    public void insurance() {

    }

    private void done() {
        this.dealerTurn();
    }

    private void dealerTurn() {

        while((dealer.total < 17 || (dealer.ace > 0 && dealer.total == 17)) && player.total < 22 ) {
            dealer.deal(deck.draw());
        }

    }

    public String results() {
        String results;
        if(player.total == dealer.total) {
            results = push();
        } else if((player.total > dealer.total && player.total < 22) || dealer.total > 21) {
            results = win();
        } else {
            results = loose();
        }
        return results;
    }

    private String win() {
        return "You win";
    }

    private String loose() {
        return "You lost";
    }

    private String push() {
        return "You push";

    }



}
