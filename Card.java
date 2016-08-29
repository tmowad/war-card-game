/**
 * Representation of a card.  
 * 
 * We could represent rank and suit as simple ints, but since different card 
 * games may have different semantics, we can try and rep these fields as 
 * interfaces that are implemented per-game.
 *
 * We will figure out the details of what these interfaces need to do as we
 * solve this problem.  (Ideally you can decide the interface up front, but
 * I'll assume that I'm working on this in a non-shared/released branch for
 * now.)
 */
public class Card {
  private Rank rank;
  private Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public boolean equals(Object other) {
    Card otherCard = (Card) other;
    return this.rank.equals(otherCard.rank) && this.suit.equals(otherCard.suit);
  }

  public String toString() {
    return "Card<" + rank.toString() + " of " + suit.toString() + ">";
  }
}
