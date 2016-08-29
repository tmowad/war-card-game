import java.util.ArrayList;

public class WarDeck implements Deck {
  private boolean isInitialized = false;

  private ArrayList<Card> cards = new ArrayList<Card>();

  public void create( int numberOfSuits, int numberOfRanks ) {
    isInitialized = true;

    for (int i=0; i<numberOfSuits; i++) {
      for (int j=0; j<numberOfRanks; j++) {
        cards.add(new Card(new Rank() {}, new Suit() {}));
      }
    }
  }

  public void shuffle() {
    throw new RuntimeException("not implemented");
  }

  public Card deal() {
    if (isInitialized == false) {
      throw new RuntimeException("cannot deal a card before initialization");
    }
    return cards.get(0);
  }
}
