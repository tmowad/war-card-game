import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestWarDeck {
  @Test
  public void cannotDealBeforeCreating() {
    Deck deck = new WarDeck();
    try {
      deck.deal();
      assertNull("Should have thrown exception", new Object());
    } catch (Exception e) {
    }
  }

  @Test
  public void createSmallDeck() {
    Deck deck = new WarDeck();
    deck.create(1, 1);
  }
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("TestWarDeck");
  }
}
