import java.util.LinkedList;

class WarPlayer {
    private LinkedList<Card> hand = new LinkedList<Card>();

    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * TODO: we may want to use another data structure (a Collection instead 
     * of an array) to avoid for-loop here, but...it works for now.  
     *
     * NOTE: We are checking for potential null cards in the array.  
     */
    public void addCards(Card[] cards) {
        for (int i=0; i<cards.length; i++) {
            if (cards[i] != null) {
                hand.add(cards[i]);
            }
        }
    }

    public Card takeCard() {
        return hand.removeFirst();
    }

    public int handSize() {
        return hand.size();
    }

    public boolean hasCards() {
        return hand.size() > 0;
    }

    public String toString() {
        String rv = hand.get(0).toString();
        for (int i=1; i<hand.size(); i++) {
            rv += "," + hand.get(i).toString();
        }
        return rv;
    }
}