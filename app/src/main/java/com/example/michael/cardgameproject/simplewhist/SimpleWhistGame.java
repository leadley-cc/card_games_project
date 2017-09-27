package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.*;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistUI;

import java.util.*;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistGame {

    private static final int PLAYER_TURN = 0;
    private static final int DEALER_TURN = 1;

    private Deck deck;
    List<Card> playerHand;
    SimpleWhistHand dealerHand;
    private SimpleWhistAI dealerAI;

    private int dealerTricks;
    private int playerTricks;

    private Random random;
    private CardSuit trumps;
    private int currentTurn;

    private SimpleWhistUI gameUI;

    public SimpleWhistGame(Deck deck, SimpleWhistUI gameUI) {
        this.deck = deck;
        this.gameUI = gameUI;

        playerHand = new ArrayList<>();
        dealerHand = new SimpleWhistHand();
        dealerAI = new SimpleWhistAI(dealerHand);

        dealerTricks = 0;
        playerTricks = 0;

        random = new Random();
    }

    public void playGame() {
        prepare();

        for (int i = 0; i < 7; i++) {
            playTurn();
        }

        GameWinner gameWinner = getWinner();
        gameUI.showGameWinner(gameWinner);
    }

    public void playTurn() {
        Card dealerCard;
        Card playerCard;

        gameUI.showTrumps(trumps);

        if (currentTurn == PLAYER_TURN) {
            playerCard = gameUI.getPlayerCard(playerHand);
            dealerCard = dealerAI.chooseCardSecond(playerCard, trumps);
            gameUI.showDealerCard(dealerCard);
        } else {
            dealerCard = dealerAI.chooseCardFirst();
            gameUI.showDealerCard(dealerCard);
            playerCard = gameUI.getPlayerCard(getPlayableCards(dealerCard));
        }

        playerHand.remove(playerCard);
        dealerHand.remove(dealerCard);

        GameWinner trickWinner = getTrickWinner(dealerCard, playerCard);
        countTrickAndSetTurn(trickWinner);

        gameUI.showTrickWinner(trickWinner);
        gameUI.showScores(dealerTricks, playerTricks);
        gameUI.prepareForNextTurn();
    }

    public void prepare() {
        dealCards();
        trumps = CardSuit.values()[ random.nextInt(4) ];
        currentTurn = random.nextInt(2);
    }

    public GameWinner getWinner() {
        if (dealerTricks > playerTricks) return GameWinner.DEALER;
        return GameWinner.PLAYER;
    }

    private List<Card> getPlayableCards(Card dealerCard) {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : playerHand) {
            if (card.getSuit() == dealerCard.getSuit()) playableCards.add(card);
        }
        if (playableCards.size() == 0) return playerHand;
        return playableCards;
    }

    private GameWinner getTrickWinner(Card dealerCard, Card playerCard) {
        CardSuit currentSuit;
        if (currentTurn == PLAYER_TURN) {
            currentSuit = playerCard.getSuit();
        } else {
            currentSuit = dealerCard.getSuit();
        }

        if (cardValue(dealerCard, currentSuit) > cardValue(playerCard, currentSuit)) {
            return GameWinner.DEALER;
        }
        return GameWinner.PLAYER;
    }

    private int cardValue(Card card, CardSuit currentSuit) {
        int cardValue;
        switch (card.getRank()) {
            case ACE:
                cardValue = 13; break;
            default:
                cardValue = card.getRank().ordinal();
        }
        if (card.getSuit() == trumps) cardValue += 13;
        else if (card.getSuit() != currentSuit) return -1;
        return cardValue;
    }

    private void dealCards() {
        for (int i = 0; i < 7; i++) {
            playerHand.add( deck.deal() );
            dealerHand.add( deck.deal() );
        }
    }

    private void countTrickAndSetTurn(GameWinner trickWinner) {
        switch (trickWinner) {
            case PLAYER:
                playerTricks++;
                currentTurn = PLAYER_TURN;
                break;
            default:
                dealerTricks++;
                currentTurn = DEALER_TURN;
        }
    }
}
