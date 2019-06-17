package blackjack;

public class Bet {

    private double onecardBet = 0;
    private double total = 0;



    public void clear() {
        this.total = 0;
        this.onecardBet = 0;
    }

    public void clearTotal() {
        this.total = 0;
    }
    public void clearOnecard() {
        this.onecardBet = 0;
    }

    public double all() {
        return onecardBet + total;
    }

    public void setOnecard(double onecard) {
        this.onecardBet = onecard;
    }

    public double getOnecard(){
        return this.onecardBet;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



}

