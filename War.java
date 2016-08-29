public class War {
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new WarDeck();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();
        
        WarPlayer[] players = divideCards(deck, numberOfPlayers);
        
        while (multipleNonEmptyPlayers(players)) {
            playOneRound(players);
        }
    }

    /**
     * This method will return true if there is more than one player in the 
     * array of players.
     *   
     * @param players A list of players.  
     * @return true, if more than one player has cards
     *         false, if at most one player has cards
     */
    boolean multipleNonEmptyPlayers(WarPlayer[] players) {
        throw new RuntimeException("not implemented");
    }

    /**
     * This method will modify the given list of players and the cards in their
     * hands to simulate one round of War.   
     *
     * @param players A list of players.
     */
    void playOneRound(WarPlayer[] players) {
        Card[] oneFromEach = new Card[players.length];
        for (int i=0; i<players.length; i++) {
            if (players[i].hasCards()) {
                oneFromEach[i] = players[i].takeCard();
            } else {
                oneFromEach[i] = null;
            }
        }

        int winningestIndex = 0;
        Card winningestCard = oneFromEach[0];

        for (int i=1; i<oneFromEach.length; i++) {
            int compareResult = oneFromEach[i].compareTo(winningestCard);
            if (compareResult > 0) {
                winningestCard = oneFromEach[i];
                winningestIndex = i;
            }
        }

        players[winningestIndex].addCards(oneFromEach);
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