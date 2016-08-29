class WarSuit implements Suit {
    private String suitName;

    WarSuit(String name) {
        this.suitName = name;
    }

    public String getName() {
        return suitName;
    }
    public boolean equals(Object o) {
        Suit otherSuit = (Suit) o;
        return suitName.equals(otherSuit.getName());
    }
    public String toString() {
        return suitName;
    }
}