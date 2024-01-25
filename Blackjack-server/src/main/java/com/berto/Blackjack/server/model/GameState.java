package com.berto.Blackjack.server.model;

import org.springframework.stereotype.Component;

@Component
public class GameState {

    private Player player;

    private Dealer dealer;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + player +
                ", dealer=" + dealer +
                '}';
    }
}