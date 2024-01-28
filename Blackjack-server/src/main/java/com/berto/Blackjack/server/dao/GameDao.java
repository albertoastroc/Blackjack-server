package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;

import java.util.List;

public interface GameDao {
    List<Person> getPlayers();

    void newGame();

    void hit();

    GameState getGameState();

    public void addPlayer(String name);
}