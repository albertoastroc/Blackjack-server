package com.berto.Blackjack.server.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameState {

    private List<Person> personList;

    private Dealer dealer;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}