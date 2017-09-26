package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.*;

/**
 * Created by michael on 26/09/2017.
 */

class SimpleWhistHand {
    private TreeSet<Card> cardSet;
    private Map<CardSuit, TreeSet<Card>> cardMap;

    SimpleWhistHand() {
        cardSet = new TreeSet<>(SimpleWhistCardComparator);
        cardMap = new EnumMap<>(CardSuit.class);
    }

    void add(Card card) {
        cardSet.add(card);

        CardSuit currentSuit = card.getSuit();

        if (cardMap.containsKey(currentSuit)) {
            cardMap.get(currentSuit).add(card);
        } else {
            TreeSet<Card> suitSet = new TreeSet<>(SimpleWhistCardComparator);
            suitSet.add(card);
            cardMap.put(currentSuit, suitSet);
        }
    }

    Set<Card> getHand() {
        return cardSet;
    }

    Set<Card> getSuit(CardSuit currentSuit) {
        if (cardMap.containsKey(currentSuit)) {
            return cardMap.get(currentSuit);
        }
        return Collections.emptySet();
    }

    Card takeHighest() {
        if (cardSet.size() == 0) return null;
        Card highestCard = cardSet.pollLast();
        cardMap.get(highestCard.getSuit()).pollLast();
        return highestCard;
    }

    Card takeLowest() {
        if (cardSet.size() == 0) return null;
        Card lowestCard = cardSet.pollFirst();
        cardMap.get(lowestCard.getSuit()).pollFirst();
        return lowestCard;
    }

    Card takeLowestInSuit(CardSuit currentSuit) {
        if (!cardMap.containsKey(currentSuit)) return null;

        TreeSet<Card> suitSet = cardMap.get(currentSuit);
        if (suitSet.size() == 0) return null;

        Card lowestInSuit = suitSet.pollFirst();
        cardSet.remove(lowestInSuit);
        return lowestInSuit;
    }

    void remove(Card card) {
        if (!cardSet.contains(card)) return;

        cardSet.remove(card);
        cardMap.get(card.getSuit()).remove(card);
    }

    private static final Comparator<Card> SimpleWhistCardComparator = new Comparator<Card>() {
        @Override
        public int compare(Card card1, Card card2) {
            int card1Value = cardValue(card1);
            int card2Value = cardValue(card2);

            if (card1Value > card2Value) {
                return 1;
            } else if (card1Value == card2Value) {
                return 0;
            } else {
                return -1;
            }
        }

        private int cardValue(Card card) {
            switch (card.getRank()) {
                case ACE:
                    return 13;
                default:
                    return card.getRank().ordinal();
            }
        }
    };
}
