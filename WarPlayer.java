import java.util.LinkedList;

class WarPlayer {
    private LinkedList<Card> hand = new LinkedList<Card>();

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCard() {
        return hand.removeFirst();
    }

    public int handSize() {
        return hand.size();
    }
}