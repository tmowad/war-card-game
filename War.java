import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Contains a lot of the top level functionality of the War card game.  
 */
public class War {
    private boolean printStuff = false;

    public War() {
    }

    public War(boolean printStuff) {
        this.printStuff = printStuff;
    }

    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new WarDeck();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();
        
        WarPlayer[] players = divideCards(deck, numberOfPlayers);

        int roundNumber = 0;
        while (multipleNonEmptyPlayers(players)) {
            System.out.println(generateRoundString(roundNumber++, players));
            playOneRound(players);
        }
        System.out.println(generateRoundString(roundNumber++, players));
    }

    private String generateRoundString(int roundNumber, WarPlayer[] players) {
        String roundString = "round " + roundNumber + ": p1[" + players[0].handSize() + " cards]";
        for (int i=1; i<players.length; i++) {
            roundString += ", p" + (i+1) + "[" + players[i].handSize() + " cards]";
        }
        return roundString;
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

            if (printStuff) {
                String cRound = "current round: [0 -> " + currentSubRound[0];
                for (int i=1; i<currentSubRound.length; i++) {
                    if (currentSubRound[i] != null) {
                        cRound += " ; " + i + " -> " + currentSubRound[i];
                    }
                    cRound += "]";
                }
                System.out.println(cRound);
            }
            // add all Cards from currentSubRound to thePot
            for (int i=0; i<currentSubRound.length; i++) {
                if (currentSubRound[i] != null) {
                    thePot.add(currentSubRound[i]);
                }
            }

            if (winningestPlayers.size() > 1) {
                // add the 'face down' cards to the pot
                if (printStuff) {
                    System.out.println("*round of cards played face down*");
                }
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
            throw new DrawGameException("Draw card game: extremely unlikely unless on purpose or tiny deck");
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

    // int numberOfSuits, int numberOfRanks, int numberOfPlayers
    public static void main(String[] args) {
        int numberOfSuits;
        int numberOfRanks;
        int numberOfPlayers;

        if (args.length == 3) {
            numberOfSuits = Integer.parseInt(args[0]);
            numberOfRanks = Integer.parseInt(args[1]);
            numberOfPlayers = Integer.parseInt(args[2]);
        } else {
            numberOfSuits = 4;
            numberOfRanks = 13;
            numberOfPlayers = 2;
        }
        System.out.println("Playing with " + numberOfSuits + " suits, " + numberOfRanks + " ranks, and " + numberOfPlayers + " players.");

        War game = new War(true);
        game.play(numberOfSuits, numberOfRanks, numberOfPlayers);
    }
}