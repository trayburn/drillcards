package com.improving;

public class Application {
    public static void main(String[] args) {
        var deck = new Deck();
        for (int i = 0; i < 7; i++) {
            System.out.println(deck.draw().toString());
        }
        System.out.println("--- Complete ---");
    }
}
