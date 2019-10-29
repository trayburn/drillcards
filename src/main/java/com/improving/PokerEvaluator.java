package com.improving;

import java.util.*;

public class PokerEvaluator {

    public PokerHands evaluate(Card[] cards) {
        var cardsList = Arrays.asList(cards);
        var isFlush = isFlush(cardsList);
        var isStraight = isStraight(cardsList);
        var maxValue = cardsList.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);

        if (maxValue == 14 && isFlush && isStraight) return PokerHands.RoyalFlush;
        if (isFlush && isStraight) return PokerHands.StraightFlush;
        if (isFourOfAKind(cardsList)) return PokerHands.FourOfAKind;
        if (isFullHouse(cardsList)) return PokerHands.FullHouse;
        if (isFlush) return PokerHands.Flush;
        if (isStraight) return PokerHands.Straight;
        if (isTwoPair(cardsList)) return PokerHands.TwoPair;
        if (isThreeOfAKind(cardsList)) return PokerHands.ThreeOfAKind;
        if (isPair(cardsList)) return PokerHands.Pair;

        return PokerHands.HighCard;
    }

    private boolean isFullHouse(List<Card> cards) {
        return isThreeOfAKind(cards) && isPair(cards);
    }

    private boolean isFourOfAKind(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.anyMatch(k -> map.get(k) == 4);
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.anyMatch(k -> map.get(k) == 3);
    }

    private boolean isPair(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.anyMatch(k -> map.get(k) == 2);
    }

    private boolean isTwoPair(List<Card> cards) {
        Map<Faces, Integer> map = mapHand(cards);
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.filter(k -> map.get(k) == 2).count() == 2;
    }

    private Map<Faces, Integer> mapHand(List<Card> cards) {
        Map<Faces, Integer> map = new HashMap<>();
        for (var card : cards) {
            if (map.containsKey(card.getFace())) {
                map.put(card.getFace(), map.get(card.getFace()) + 1);
            } else {
                map.put(card.getFace(),1);
            }
        }
        return map;
    }

    private boolean isStraight(List<Card> cards) {
        var minValue = cards.stream().map(c -> c.getFace().getValue()).min(Integer::compare).orElse(0);
        var maxValue = cards.stream().map(c -> c.getFace().getValue()).max(Integer::compare).orElse(0);
        var distinctCount = cards.stream().map(c -> c.getFace()).distinct().count();
        return (distinctCount == 5 && (maxValue - minValue) == 4);
    }

    private boolean isFlush(List<Card> cards) {
        var suit = cards.get(0).getSuit();
        return cards.stream().allMatch(e -> e.getSuit().equals(suit));
    }

}
