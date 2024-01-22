package com.berto.Blackjack.server.model;

import java.util.ArrayList;
import java.util.List;

import static com.berto.Blackjack.server.Constants.STARTING_HAND_SIZE;

public abstract class Player {

    private String name;
    private List<Hand> hands = new ArrayList<>();
    private int balance = 5000;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public void addToBalance(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void addHand(Hand hand){
        hands.add(hand);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Resets and deals new starting hand
     *
     * @param deck Deck to get cards from
     */
    public void setUpStartingHand(Deck deck) {

        hands = new ArrayList<>();
        Hand newHand = new Hand();

        for (int j = 0; j < STARTING_HAND_SIZE; j++) {

            newHand.addCardToHand(deck.getRandomCard());

        }

        hands.add(newHand);
    }

    public void subtractFromBalance(int amount) {
        if (amount > 0) {
            balance -= amount;
        }
    }

}