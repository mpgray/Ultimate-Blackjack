package blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void numberCardsAreWorthTheirPips() {
        assertEquals(2, Cards.card(Card.Face.TWO).value);
        assertEquals(5, Cards.card(Card.Face.FIVE).value);
        assertEquals(9, Cards.card(Card.Face.NINE).value);
        assertEquals(10, Cards.card(Card.Face.TEN).value);
    }

    @ParameterizedTest
    @EnumSource(value = Card.Face.class, names = {"JACK", "QUEEN", "KING"})
    void faceCardsAreWorthTen(Card.Face face) {
        assertEquals(10, Cards.card(face).value);
    }

    @Test
    void aceStartsAtEleven() {
        assertEquals(11, Cards.card(Card.Face.ACE).value);
    }

    @Test
    void imageNamesMatchTheBundledArt() {
        Card card = new Card(Card.Face.QUEEN, Card.Suit.HEARTS);
        String path = "cards/" + card.face.getName() + "-" + card.suit.getName() + ".png";
        assertEquals("cards/Q-heart.png", path);
    }
}
