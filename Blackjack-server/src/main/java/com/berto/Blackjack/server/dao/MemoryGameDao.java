package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.Game;
import com.berto.Blackjack.server.model.Dealer;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import com.berto.Blackjack.server.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class MemoryGameDao implements GameDao {

    private Game game = new Game();

    private List<Person> players = new ArrayList<>();

    private Dealer dealer = new Dealer();

    private Person player;

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
        this.player = player;
    }

    public MemoryGameDao() { initializeTestData(); }

    @Override
    public List<Person> getPlayers() {
        return null;
    }

    @Override
    public GameState startGame() {

        HashSet<Player> set = new HashSet<>();
        set.add(new Person("Bre"));

        game.setSetOfPlayers(set);
        GameState gameState = new GameState();
        gameState.setPlayer(game.getSetOfPlayers().iterator().next());
        gameState.setDealer(game.getDealer());
        return gameState;
    }
    @Override
    public void setPlayers(String name){

        HashSet<Player> set = new HashSet<>();
        set.add(new Person(name));
        game.setSetOfPlayers(set);

    }

    public void playRound(){

        game.playRound();

    }

    @Override
    public GameState initializeGameState() {

        GameState gameState = new GameState();

        gameState.setDealer(dealer);

        gameState.setPlayer(player);

        return gameState;
    }

    public void initializeTestData() {

        players.add(new Person("Brian"));

        players.add(new Person("Reggie"));

    }

}