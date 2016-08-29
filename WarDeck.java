import java.util.ArrayList;

public class WarDeck implements Deck {
  private ArrayList<Card> cards = null;

  public void create( int numberOfSuits, int numberOfRanks ) {
    this.cards = new ArrayList<Card>();

    for (int i=0; i<numberOfSuits; i++) {
      Suit suit = new WarSuit("suit_" + i);
      for (int j=0; j<numberOfRanks; j++) {
        Rank rank = new WarRank(j);
        cards.add(new Card(rank, suit));
      }
    }
  }

  public void shuffle() {
    throw new RuntimeException("not implemented");
  }

  public Card deal() {
    if (this.cards == null) {
      throw new RuntimeException("cannot deal a card before initialization");
    }
    if (cards.size() == 0) {
      throw new RuntimeException("cannot deal card from empty deck");
    }
    return cards.remove(0);
  }
}
