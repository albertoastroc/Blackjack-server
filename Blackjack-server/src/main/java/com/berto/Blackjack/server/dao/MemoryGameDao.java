package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.Person;
import com.berto.Blackjack.server.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryGameDao implements GameDao {

    private List<Player> players = new ArrayList<>();

    public MemoryGameDao() { initializeTestData(); }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    public void initializeTestData() {

        players.add(new Person("Brian"));

        players.add(new Person("Reggie"));

    }

}