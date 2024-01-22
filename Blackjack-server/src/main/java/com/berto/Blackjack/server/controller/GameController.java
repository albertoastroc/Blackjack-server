package com.berto.Blackjack.server.controller;

import com.berto.Blackjack.server.dao.GameDao;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GameController {

    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping("/players")
    public List<Person> getPlayers() {
        return gameDao.getPlayers();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Test endpoint response");
    }

    @GetMapping("/state")
    public GameState getGameState() {
        System.out.println("Reading the controller");
        return gameDao.initializeGameState();
    }
}