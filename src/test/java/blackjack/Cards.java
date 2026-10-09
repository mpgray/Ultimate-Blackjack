package blackjack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/** Test helpers for building hands and stacked decks without Swing. */
final class Cards {
    private Cards() {
    }

    static Card card(Card.Face face) {
        return new Card(face, Card.Suit.SPADES);
    }

    static Hand hand(Card.Face... faces) {
        Hand hand = new Hand();
        for (Card.Face face : faces) {
            hand.deal(card(face));
        }
        return hand;
    }

    /** A deck that deals the given faces in order, so dealer behaviour is deterministic. */
    static Deck stacked(Card.Face... faces) {
        Deque<Card> order = new ArrayDeque<>();
        for (Card.Face face : faces) {
            order.add(card(face));
        }
        return new Deck() {
            @Override
            public Card draw() {
                if (order.isEmpty()) {
                    throw new AssertionError("stacked deck ran out: the dealer drew more cards than the test expected");
                }
                return order.poll();
            }
        };
    }

    static List<Card.Face> faces(Hand hand) {
        return hand.hand.stream().map(c -> c.face).toList();
    }
}
