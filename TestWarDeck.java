import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestWarDeck {
  @Test
  public void cannotDealBeforeCreating() {
    WarDeck deck = new WarDeck();
    try {
      deck.deal();
      assertNull("Should have thrown exception", new Object());
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("TestWarDeck");
  }
}
