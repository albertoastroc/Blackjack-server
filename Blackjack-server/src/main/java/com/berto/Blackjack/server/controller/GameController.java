package com.berto.Blackjack.server.controller;

import com.berto.Blackjack.server.dao.GameDao;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/game")
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

//    @GetMapping("/state")
//    public GameState getGameState() {
//        System.out.println("Reading the controller");
//        return gameDao.initializeGameState();
//    }

    @PostMapping("/hit")
    public ResponseEntity<String> hit() {

        gameDao.hit();

        return ResponseEntity.ok("Player hits " + "state is " + gameDao.getGameState());
    }

    @GetMapping("/state")
    public GameState startGame() {
        System.out.println("Reading the controller");
        return gameDao.setUpGameState();
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestBody Person person) {

        gameDao.addPlayer(person.getName());

        return ResponseEntity.ok("Name received name is " + person.getName() + "state is " + gameDao.getGameState());
    }

}

//function changeName(){
//        const nameField = document.getElementById("playerName");
//    nameField.innerText = gameState.player.name;
//
//}
//
//    const addNameButton = document.getElementById("addNameButton");
//        addnameButton.addEventListener("click", changeName);