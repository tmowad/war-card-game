public class WarDeck implements Deck {
  boolean isInitialized = false;

  public void create( int numberOfSuits, int numberOfRanks ) {
  }

  public void shuffle() {
    throw new RuntimeException("not implemented");
  }

  public Card deal() {
    throw new RuntimeException("not implemented");
  }
}
