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
}