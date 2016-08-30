import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

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
        int numNonEmpty = 0;
        for (int i=0; i<players.length; i++) {
            if (players[i].handSize() > 0) {
                numNonEmpty ++;
            }
        }
        return numNonEmpty > 1;
    }

    /**
     * This method will modify the given list of players and the cards in their
     * hands to simulate one round of War.   
     *
     * @param players A list of players.
     */
    void playOneRound(WarPlayer[] players) {
        // A set that keeps track of how many players (by their index in 
        // players) array are tied in the current sub-round.  
        Set<Integer> winningestPlayers = new HashSet<Integer>();

        Set<Card> thePot = new HashSet<Card>();

        while (true) {
            // List of ( Card || null ) corresponding to the players array
            Card[] currentSubRound = new Card[players.length];
            for (int i=0; i<players.length; i++) {
                if (players[i].hasCards()) {
                    currentSubRound[i] = players[i].takeCard();
                } else {
                    currentSubRound[i] = null;
                }
            }

            winningestPlayers.clear();
            Card winningestCard = null;

            for (int i=0; i<currentSubRound.length; i++) {
                if (currentSubRound[i] == null) {
                    continue;
                }

                if (winningestCard == null) {
                    winningestCard = currentSubRound[i];
                    winningestPlayers.add(i);
                    continue;
                }

                int compareResult = currentSubRound[i].compareTo(winningestCard);
                if (compareResult > 0) {
                    winningestCard = currentSubRound[i];
                    winningestPlayers.clear();
                    winningestPlayers.add(i);
                } else if (compareResult == 0) {
                    winningestPlayers.add(i);
                }
            }

            // add all Cards from currentSubRound to thePot
            for (int i=0; i<currentSubRound.length; i++) {
                if (currentSubRound[i] != null) {
                    thePot.add(currentSubRound[i]);
                }
            }

            if (winningestPlayers.size() > 1) {
                // add the 'face down' cards to the pot
                for (int i=0; i<players.length; i++) {
                if (players[i].hasCards()) {
                    thePot.add(players[i].takeCard());
                }
            }
            } else {
                break;
            }
        }

        if (winningestPlayers.size() == 0) {
            // I believe that this case is possible, but not statistically 
            // likely in a large deck.  With two players, it could happen if we
            // had the exact same ordering of 26 (equal rank) cards in a row.  
            throw new RuntimeException("Draw card game: extremely unlikely unless on purpose or tiny deck");
        }

        players[winningestPlayers.iterator().next()].addCards(thePot);
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

    public static void main(String[] args) {
        Deck deck = new WarDeck();
        deck.create(4, 13);
        deck.shuffle();

        War game = new War();
        WarPlayer[] players = game.divideCards(deck, 2);
        
        int roundNumber = 0;
        while (game.multipleNonEmptyPlayers(players)) {
            System.out.println("round " + roundNumber++ + ": p1[" + players[0].handSize() + "], p2[" + players[1].handSize() + "]");
            game.playOneRound(players);
        }
        System.out.println("round " + roundNumber++ + ": p1[" + players[0].handSize() + "], p2[" + players[1].handSize() + "]");

    }
}