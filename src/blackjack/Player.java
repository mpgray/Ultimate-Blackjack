package blackjack;

public class Player {
    public Hand hand = new Hand();
    private Stake stake = new Stake();


    private Bet bet = new Bet();
    private String userName = "PLAYER";

    private Deck deck;

    Player(Deck deck) {
        this.deck = deck;
    }

    public String getUserName() {
        return userName;
    }

    public void clear() {
        hand.muck();
    }

    public void deal(){
        hand.deal(deck.draw());
    }

    public double getStake() {
        return stake.getTotal();
    }

    public void setStake(double adjustment) {
        this.stake.setTotal(adjustment + this.getStake());
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(double bet) {
        if (this.getStake() >= bet) {
            this.setStake(-bet);

            this.bet.total = bet;
        }
    }

    @Override
    public String toString() {
        if (hand.hand.size() > 0) {
            return "(" + hand.total + ") " + "[" + hand.hand.get(0).face.name + hand.hand.get(0).suit.value + "]" + hand.toString();
        }
        return "";
    }
}
