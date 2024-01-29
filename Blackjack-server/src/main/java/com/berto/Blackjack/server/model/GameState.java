package com.berto.Blackjack.server.model;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GameState {

    private Set<Person> playersList;

    private Dealer dealer;

    public Set<Person> getPlayersList() {
        return playersList;
    }

    public void setPlayerList(Set<Person> playerList) {
        this.playersList = playerList;
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
                "playersList=" + playersList +
                ", dealer=" + dealer +
                '}';
    }
}