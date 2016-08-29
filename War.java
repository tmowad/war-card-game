public class War {
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new WarDeck();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();
        divideCards(deck, numberOfPlayers);
    }

    WarPlayer[] divideCards(Deck deck, int numberOfPlayers) {
        WarPlayer[] players = new WarPlayer[numberOfPlayers];
        for (int i=0; i<players.length; i++) {
            players[i] = new WarPlayer();
        }
        Card card;
        int playerCounter = 0;
        while ((card = deck.deal()) != null) {
            players[playerCounter].addCard(card);
            playerCounter = (playerCounter+1) % numberOfPlayers;
        }
        return players;
    }
}