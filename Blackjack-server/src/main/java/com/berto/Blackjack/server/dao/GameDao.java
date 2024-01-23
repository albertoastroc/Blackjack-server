package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.Game;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import com.berto.Blackjack.server.model.Player;

import java.util.List;

public interface GameDao {
    List<Person> getPlayers();

    GameState startGame();

    public void setPlayers(String name);

    GameState initializeGameState();
}