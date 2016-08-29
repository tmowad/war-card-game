import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestWar {
    @Test
    public void testDivideCards() {
        War game = new War();
        Deck deck = new WarDeck();
        deck.create(4, 13);
        WarPlayer[] players = game.divideCards(deck, 2);
        assertEquals(players.length, 2);
        assertNotNull(players[0]);
        assertEquals(players[0].handSize(), 26);
        assertNotNull(players[1]);
        assertEquals(players[1].handSize(), 26);
    }
    public static void main(String[] args) {
      org.junit.runner.JUnitCore.main("TestWar");
    }
}