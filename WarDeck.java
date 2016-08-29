public class WarDeck implements Deck {
  private boolean isInitialized = false;

  public void create( int numberOfSuits, int numberOfRanks ) {
    isInitialized = true;
  }

  public void shuffle() {
    throw new RuntimeException("not implemented");
  }

  public Card deal() {
    if (isInitialized == false) {
      throw new RuntimeException("cannot deal a card before initialization");
    }
    return null;
  }
}
