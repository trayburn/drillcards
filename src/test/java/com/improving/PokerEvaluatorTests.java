package com.improving;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerEvaluatorTests {

    // High Card
    // Pair
    // Three of a Kind
    // Two Pair
    // Straight
    // Flush
    // Full House
    // Four of a Kind
    // Straight Flush
    // Royal Flush

    @Test
    public void Evaluate_Should_Recognize_A_Flush() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.Ten, Suits.Spades),
                new Card(Faces.Two, Suits.Spades),
                new Card(Faces.Three, Suits.Spades),
                new Card(Faces.Five, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.Flush, result);
    }


    @Test
    public void Evaluate_Should_Recognize_A_Straight() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Queen, Suits.Clubs),
                new Card(Faces.Jack, Suits.Spades),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.Straight, result);
    }

    @Test
    public void Evaluate_Should_Recognize_A_StraightFlush() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Nine, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Queen, Suits.Spades),
                new Card(Faces.Jack, Suits.Spades),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.StraightFlush, result);
    }

    @Test
    public void Evaluate_Should_Recognize_A_RoyalFlush() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Queen, Suits.Spades),
                new Card(Faces.Jack, Suits.Spades),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.RoyalFlush, result);
    }


    @Test
    public void Evaluate_Should_Recognize_A_Pair() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Queen, Suits.Spades),
                new Card(Faces.Ace, Suits.Clubs),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.Pair, result);
    }


    @Test
    public void Evaluate_Should_Recognize_A_Two_Pair() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Ten, Suits.Clubs),
                new Card(Faces.Ace, Suits.Clubs),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.TwoPair, result);
    }

    @Test
    public void Evaluate_Should_Recognize_A_Three_Of_A_Kind() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.King, Suits.Spades),
                new Card(Faces.Ten, Suits.Clubs),
                new Card(Faces.Ten, Suits.Diamonds),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.ThreeOfAKind, result);
    }

    @Test
    public void Evaluate_Should_Recognize_A_Four_Of_A_Kind() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.Ten, Suits.Hearts),
                new Card(Faces.Ten, Suits.Clubs),
                new Card(Faces.Ten, Suits.Diamonds),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.FourOfAKind, result);
    }

    @Test
    public void Evaluate_Should_Recognize_A_Full_House() {
        // Arrange
        var cards = new Card[]{
                new Card(Faces.Ace, Suits.Spades),
                new Card(Faces.Ace, Suits.Clubs),
                new Card(Faces.Ten, Suits.Clubs),
                new Card(Faces.Ten, Suits.Diamonds),
                new Card(Faces.Ten, Suits.Spades),
        };

        // Act
        var result = new PokerEvaluator().evaluate(cards);

        // Assert
        assertEquals(PokerHands.FullHouse, result);
    }

}
