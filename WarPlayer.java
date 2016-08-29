import java.util.LinkedList;

class WarPlayer {
    private LinkedList<Card> hand = new LinkedList<Card>();

    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * TODO: we may want to use another data structure (a Collection instead 
     * of an array) to avoid for-loop here, but...it works for now.  
     */
    public void addCards(Card[] cards) {
        for (int i=0; i<cards.length; i++) {
            hand.add(cards[i]);
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
}