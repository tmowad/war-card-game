import java.util.LinkedList;

class WarPlayer {
    private LinkedList<Card> hand = new LinkedList<Card>();

    public void addCard(Card card) {
        hand.add(card);
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