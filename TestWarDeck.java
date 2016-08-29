import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class TestWarDeck {
  @Test
  public void cannotDealBeforeCreating() {
    Deck deck = new WarDeck();
    try {
      deck.deal();
      assertTrue("Should not get here -- should have thrown exception", false);
    } catch (Exception e) {
    }
  }

  @Test
  public void createSmallDeck() {
    Deck deck = new WarDeck();
    deck.create(1, 1);
  }

  @Test
  public void createSmallDeckAndDealTwo() {
    Deck deck = new WarDeck();
    deck.create(2,2);
    Card cardOne = deck.deal();
    Card cardTwo = deck.deal();
    assertNotNull(cardOne);
    assertNotNull(cardTwo);
    assertNotEquals(cardOne, cardTwo);
  }
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("TestWarDeck");
  }
}
