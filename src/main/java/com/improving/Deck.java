package com.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();
    private final Random random = new Random();

    public Deck() {
        for (var suit : Suits.values()) {
            for (var face : Faces.values()) {
                cards.add(new Card(face, suit));
            }
        }
    }

    public Card draw() {
        var randomIndex = random.nextInt(cards.size());
        var card = cards.get(randomIndex);
        cards.remove(randomIndex);
        discard.add(card);
        return card;
    }
}
