package com.improving;

public enum PokerHands {
    HighCard(0),
    Pair(1),
    ThreeOfAKind(2),
    TwoPair(3),
    Straight(4),
    Flush(5),
    FullHouse(6),
    FourOfAKind(7),
    StraightFlush(8),
    RoyalFlush(9);

    private final int value;

    PokerHands(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
