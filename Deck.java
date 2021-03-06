/**
 * Interface for a Deck as supplied by the assignment.
 */
public interface Deck {
  /* Create the deck of cards */
  public void create(int numberOfSuits, int numberOfRanks);

  /* Shuffle the deck */
  public void shuffle();

  /* deal a card from the deck */
  public Card deal();

  /* return the number of cards in a deck */
  public int numCards();
}
