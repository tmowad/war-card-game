import java.util.ArrayList;
import java.util.Collections;

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
    Collections.shuffle(this.cards);
  }

  /**
   * Since the given interface definition did not define the semantics of the deal() method, and
   * there is no way to determine the number of cards in the deck (nor whether the deck has cards)
   * we will return null if the deck is out of cards.  
   */
  public Card deal() {
    if (this.cards == null) {
      throw new RuntimeException("cannot deal a card before initialization");
    }
    if (cards.size() == 0) {
      return null;
    }
    return cards.remove(0);
  }
}
