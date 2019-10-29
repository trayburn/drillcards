package com.improving;

public class Card {
    private final Faces face;
    private final Suits suit;

    public Card(Faces face, Suits suit) {
        this.face = face;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "" + face.toString() + " of " + suit.toString();
    }

    public Faces getFace() {
        return face;
    }

    public Suits getSuit() {
        return suit;
    }
}
