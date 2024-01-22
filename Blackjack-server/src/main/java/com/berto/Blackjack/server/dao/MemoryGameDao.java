package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.Dealer;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import com.berto.Blackjack.server.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryGameDao implements GameDao {

    private List<Person> players = new ArrayList<>();

    private Dealer dealer = new Dealer();

    public MemoryGameDao() { initializeTestData(); }

    @Override
    public List<Person> getPlayers() {
        return null;
    }

    @Override
    public GameState initializeGameState() {

        GameState gameState = new GameState();

        gameState.setDealer(dealer);

        gameState.setPersonList(players);

        return gameState;
    }

    public void initializeTestData() {

        players.add(new Person("Brian"));

        players.add(new Person("Reggie"));

    }

}