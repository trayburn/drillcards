package com.improving;

public class HandResult {
    private final PokerHands handType;
    private final Faces highCardInType;
    private final Faces[] unusedCards;

    public HandResult(PokerHands handType, Faces highCardInType, Faces... unusedCards) {
        this.handType = handType;
        this.highCardInType = highCardInType;
        this.unusedCards = unusedCards;
    }

    public PokerHands getHandType() {
        return handType;
    }

    public Faces getHighCardInType() {
        return highCardInType;
    }

    public Faces[] getUnusedCards() {
        return unusedCards;
    }
}
