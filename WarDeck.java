import java.util.ArrayList;

public class WarDeck implements Deck {
  private boolean isInitialized = false;

  private ArrayList<Card> cards = new ArrayList<Card>();

  public void create( int numberOfSuits, int numberOfRanks ) {
    isInitialized = true;

    for (int i=0; i<numberOfSuits; i++) {
      final String suitName = "suit_" + i;
      Suit suit = new Suit() {
        public String getName() {
          return suitName;
        }
      };
      for (int j=0; j<numberOfRanks; j++) {
        cards.add(new Card(new Rank() {}, suit));
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
    if (cards.size() == 0) {
      throw new RuntimeException("cannot deal card from empty deck");
    }
    return cards.remove(0);
  }
}
