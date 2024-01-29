package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;

import java.util.Set;

public interface GameDao {
    Set<Person> getPlayers();

    void newGame();

    void hit(String name);

    GameState getGameState();

    public void addPlayer(String name);
}