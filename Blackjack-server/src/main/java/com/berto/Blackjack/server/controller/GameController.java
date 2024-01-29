package com.berto.Blackjack.server.controller;

import com.berto.Blackjack.server.dao.GameDao;
import com.berto.Blackjack.server.model.GameState;
import com.berto.Blackjack.server.model.Person;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping("/players")
    public Set<Person> getPlayers() {
        return gameDao.getPlayers();
    }

    @GetMapping("/connected")
    public ResponseEntity<String> confirmConnection() {
        return ResponseEntity.ok("Connected");
    }

    @GetMapping("/new")
    public void newGame() {
        System.out.println("Reading the controller");
        gameDao.newGame();
    }

    @PostMapping("/hit/{name}")
    public ResponseEntity<String> hit(@PathVariable String name) {

        gameDao.hit(name);

        return ResponseEntity.ok("Player hits " + "state is " + gameDao.getGameState());
    }

    @GetMapping("/state")
    public GameState getGameState() {
        System.out.println("Reading the controller");
        return gameDao.getGameState();
    }

    @PostMapping("/add-player")
    public ResponseEntity<String> addPlayer(@RequestBody Person person) {
        gameDao.addPlayer(person.getName());

        return ResponseEntity.ok("Name received name is " + person.getName() + " state is " + gameDao.getGameState());
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