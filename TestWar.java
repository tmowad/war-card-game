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

    @Test
    public void testDivideCardsThreeWays() {
        War game = new War();
        Deck deck = new WarDeck();
        deck.create(4, 13);
        WarPlayer[] players = game.divideCards(deck, 3);
        assertEquals(players.length, 3);
        assertNotNull(players[0]);
        assertEquals(18, players[0].handSize());
        assertNotNull(players[1]);
        assertEquals(17, players[1].handSize());
        assertNotNull(players[2]);
        assertEquals(17, players[2].handSize());

    }

    @Test
    public void testOneSimpleRound() {
        WarPlayer first = new WarPlayer();
        first.addCard(new Card(new WarRank(3), new WarSuit("spades")));
        WarPlayer second = new WarPlayer();
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        assertEquals(1, first.handSize());
        assertEquals(1, second.handSize());

        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second });
        assertEquals(0, first.handSize());
        assertEquals(2, second.handSize());
    }

    @Test
    public void testOneRoundWithAnEmptyPlayer() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();
        second.addCard(new Card(new WarRank(3), new WarSuit("spades")));
        WarPlayer third = new WarPlayer();
        third.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        assertEquals(0, first.handSize());
        assertEquals(1, second.handSize());
        assertEquals(1, third.handSize());

        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second, third });
        assertEquals(0, first.handSize());
        assertEquals(0, second.handSize());
        assertEquals(2, third.handSize());
    }

    @Test
    public void testOneRoundInvolvingATie() {
        WarPlayer first = new WarPlayer();
        WarPlayer second = new WarPlayer();
        first.addCard(new Card(new WarRank(7), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(7), new WarSuit("hearts")));
        first.addCard(new Card(new WarRank(5), new WarSuit("spades")));
        second.addCard(new Card(new WarRank(8), new WarSuit("hearts")));
        assertEquals(2, first.handSize());
        assertEquals(2, second.handSize());
        War game = new War();
        game.playOneRound(new WarPlayer[] { first, second });
        assertEquals(0, first.handSize());
        assertEquals(4, second.handSize());
    }

    // TODO: show a test case that could lead to a draw game, and ensure we get
    // the proper Exception back
    @Test
    public void realDrawTestCase() {
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("TestWar");
    }
}