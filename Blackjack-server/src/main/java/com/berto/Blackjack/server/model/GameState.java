package com.berto.Blackjack.server.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameState {

    private List<Player> player;

    private Dealer dealer;

    public List<Player> getPlayer() {
        return player;
    }

    public void addPlayer(Player player) {
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