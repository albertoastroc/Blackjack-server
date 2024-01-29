package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.Game;
import com.berto.Blackjack.server.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MemoryGameDao implements GameDao {

    private Game game = new Game();
    GameState gameState = new GameState();

    private Set<Person> players = new HashSet<>();

    private Dealer dealer = new Dealer();

    HashSet<Person> set = new HashSet<>();

    public MemoryGameDao() {  }

    @Override
    public Set<Person> getPlayers() {
        return players;
    }

    @Override
    public void newGame(){

        set = new HashSet<>();
        game = new Game();
        gameState = new GameState();

        game.setSetOfPlayers(set);
        gameState.setPlayerList(set);
        gameState.setDealer(game.getDealer());
    }

    public void hit(String name){

        //game.getSetOfPlayers().stream().toList().get(0).getHands().get(0).addCardToHand(Deck.getInstance().getRandomCard());
        //System.out.println(game.getSetOfPlayers().stream().toList().get(0).getHands().get(0));

        System.out.println("Print this " + game.getSetOfPlayers().stream().filter((person -> person.getName().equals(name))).findFirst().get().getHands().get(0));
        game.getSetOfPlayers().stream().filter((person -> person.getName().equals(name))).findFirst().get().getHands().get(0).addCardToHand(Deck.getInstance().getRandomCard());


    }

    @Override
    public GameState getGameState() {

        gameState.setPlayerList(game.getSetOfPlayers());
        gameState.setDealer(game.getDealer());
        return gameState;
    }

    @Override
    public void addPlayer(String name){

        Person newPerson = new Person(name);
        newPerson.setUpStartingHand(Deck.getInstance());
        set.add(newPerson);
        game.setSetOfPlayers(set);

    }

    public void playRound(){

        game.playRound();

    }

}