package com.improving;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PokerEvaluator {

    public int compare(Card[] x, Card[] y) {
        var xHand = evaluateHand(x);
        var yHand = evaluateHand(y);

        if (xHand.getHandType() == yHand.getHandType()) {
            return Integer.compare(xHand.getHighCardInType().getValue(), yHand.getHighCardInType().getValue());
        }
        return Integer.compare(xHand.getHandType().getValue(), yHand.getHandType().getValue());
    }

    public PokerHands evaluate(Card[] cards) {
        return evaluateHand(cards).getHandType();
    }

    public HandResult evaluateHand(Card[] cards) {
        HandResult result = null;
        var cardsList = Arrays.asList(cards);
        var isFlush = isFlush(cardsList);
        var isStraight = isStraight(cardsList);

        result = isRoyalFlush(cardsList);
        if (result != null) return result;

        result = isStraightFlush(cardsList);
        if (result != null) return result;

        result = isFourOfAKind(cardsList);
        if (result != null) return result;

        result = isFullHouse(cardsList);
        if (result != null) return result;

        result = isFlush(cardsList);
        if (result != null) return result;

        result = isStraight(cardsList);
        if (result != null) return result;

        result = isTwoPair(cardsList);
        if (result != null) return result;

        result = isThreeOfAKind(cardsList);
        if (result != null) return result;

        result = isPair(cardsList);
        if (result != null) return result;

        Faces maxFace = cardsList.stream().map(c -> c.getFace())
                .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                .findFirst().orElseThrow();
        var otherFaces = cardsList.stream()
                .filter(c -> c.getFace() != maxFace)
                .map(c -> c.getFace()).toArray(Faces[]::new);
        return new HandResult(PokerHands.HighCard, maxFace, otherFaces);
    }

    private HandResult isRoyalFlush(List<Card> cards) {
        var maxValue = cards.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);
        var sfResult = isStraightFlush(cards);
        if (maxValue == 14 && sfResult != null) {
            return new HandResult(PokerHands.RoyalFlush, sfResult.getHighCardInType());
        } else return null;
    }

    private HandResult isStraightFlush(List<Card> cards) {
        var straightResult = isStraight(cards);
        var flushResult = isFlush(cards);
        if (straightResult != null && flushResult != null) {
            return new HandResult(PokerHands.StraightFlush, straightResult.getHighCardInType());
        } else return null;
    }

    private HandResult isFullHouse(List<Card> cards) {
        var threeResult = isThreeOfAKind(cards);
        var pairResult = isPair(cards);
        if (threeResult != null && pairResult != null) {
            return new HandResult(PokerHands.FullHouse, threeResult.getHighCardInType());
        } else return null;
    }


    private HandResult isFourOfAKind(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1)
                .collect(Collectors.toList());
        if (multipleCards.stream().anyMatch(k -> map.get(k) == 4)) {
            var highCardInType = multipleCards.stream().filter(k -> map.get(k) == 4)
                    .findFirst().orElseThrow();
            var remainingCards = multipleCards.stream().filter(k -> map.get(k) != 4).toArray(Faces[]::new);
            return new HandResult(PokerHands.FourOfAKind, highCardInType, remainingCards);
        } else return null;
    }

    private HandResult isThreeOfAKind(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1)
                .collect(Collectors.toList());
        if (multipleCards.stream().anyMatch(k -> map.get(k) == 3)) {
            var highCardInType = multipleCards.stream().filter(k -> map.get(k) == 3)
                    .findFirst().orElseThrow();
            var remainingCards = multipleCards.stream().filter(k -> map.get(k) != 3).toArray(Faces[]::new);
            return new HandResult(PokerHands.ThreeOfAKind, highCardInType, remainingCards);
        } else return null;
    }

    private HandResult isPair(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1)
                .collect(Collectors.toList());
        if (multipleCards.stream().anyMatch(k -> map.get(k) == 2)) {
            var highCardInType = multipleCards.stream().filter(k -> map.get(k) == 2)
                    .findFirst().orElseThrow();
            var remainingCards = multipleCards.stream().filter(k -> map.get(k) != 2).toArray(Faces[]::new);
            return new HandResult(PokerHands.Pair, highCardInType, remainingCards);
        } else return null;
    }

    private HandResult isTwoPair(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1)
                .collect(Collectors.toList());
        if (multipleCards.stream().filter(k -> map.get(k) == 2).count() == 2) {
            var highCardInType = multipleCards.stream().filter(k -> map.get(k) == 2)
                    .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                    .findFirst().orElseThrow();
            var remainingCards = multipleCards.stream().filter(k -> map.get(k) != 2).toArray(Faces[]::new);
            return new HandResult(PokerHands.TwoPair, highCardInType, remainingCards);
        } else return null;
    }

    private Map<Faces, Integer> mapHand(List<Card> cards) {
        Map<Faces, Integer> map = new HashMap<>();
        for (var card : cards) {
            if (map.containsKey(card.getFace())) {
                map.put(card.getFace(), map.get(card.getFace()) + 1);
            } else {
                map.put(card.getFace(), 1);
            }
        }
        return map;
    }

    private HandResult isStraight(List<Card> cards) {
        var minValue = cards.stream().map(c -> c.getFace().getValue()).min(Integer::compare).orElse(0);
        var maxValue = cards.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);
        Faces maxFace = cards.stream().map(c -> c.getFace())
                .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                .findFirst().orElseThrow();
        var distinctCount = cards.stream().map(c -> c.getFace()).distinct().count();
        if ((distinctCount == 5 && (maxValue - minValue) == 4)) {
            return new HandResult(PokerHands.Straight, maxFace);
        } else return null;
    }

    private HandResult isFlush(List<Card> cards) {
        var suit = cards.get(0).getSuit();
        Faces maxFace = cards.stream().map(c -> c.getFace())
                .sorted((x, y) -> Integer.compare(y.getValue(), x.getValue()))
                .findFirst().orElseThrow();
        if (cards.stream().allMatch(e -> e.getSuit().equals(suit))) {
            return new HandResult(PokerHands.Flush, maxFace);
        } else return null;
    }

}
