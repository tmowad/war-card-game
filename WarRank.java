class WarRank implements Rank {
    private int rankValue;

    WarRank(int rank) {
        this.rankValue = rank;
    }

    public int getValue() {
        return rankValue;
    }

    public boolean equals(Object other) {
        Rank otherRank = (Rank) other;
        return rankValue == otherRank.getValue();
    }

    public String toString() {
        return "" + rankValue;
    }

    /**
     * NOTE: In the real game of War, the standard 52-card deck is used, and 
     * Aces are considered high.  Since our version can customize the number of
     * rank values, we will consider the Rank values as natural numbers, 1, 2,
     * ... to [numRankValues], with no concept of an Ace being value 1.  
     *
     * If we wanted, we could provide an implementation of Rank interface 
     * that implemented that standard 52-card deck, where we could take into 
     * account whether or not either card had a '1' value.  
     */
    public int compareTo(Rank other) {
        if (rankValue == other.getValue()) {
            return 0;
        }
        if (rankValue < other.getValue()) {
            return -1;
        }
        return 1;
    }
}