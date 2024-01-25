package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.Game;
import com.berto.Blackjack.server.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class MemoryGameDao implements GameDao {

    private Game game = new Game();
    GameState gameState = new GameState();

    private List<Person> players = new ArrayList<>();

    private Dealer dealer = new Dealer();

    private Person player;

    HashSet<Player> set = new HashSet<>();

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
    public void newGame(){

        game.setSetOfPlayers(set);
        game.loadNewDeck();
    }

    public void hit(){

        player.getHands().get(0).addCardToHand(Deck.getInstance().getRandomCard());
        System.out.println(player.getHands().get(0));

    }

    @Override
    public GameState setUpGameState() {

        set = new HashSet<>();
//        set.add(new Person("Bre"));

        game.setSetOfPlayers(set);

//        gameState.setPlayer(game.getSetOfPlayers().iterator().next());
        gameState.setDealer(game.getDealer());
        return gameState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void addPlayer(String name){

        player = new Person(name);
        player.setUpStartingHand(Deck.getInstance());
        set.add(player);
        gameState.setPlayer(player);


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