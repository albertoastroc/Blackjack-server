package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import com.berto.Blackjack.server.model.Player;

import java.util.List;

public interface GameDao {
    List<Person> getPlayers();

    GameState initializeGameState();
}