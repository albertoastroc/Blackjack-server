package com.berto.Blackjack.server;

import com.berto.Blackjack.server.dao.GameDao;
import com.berto.Blackjack.server.model.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class GameController {

    private GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @RequestMapping(path = "/players", method = RequestMethod.GET)
    public List<Player> getPlayers() {
        return gameDao.getPlayers();
    }
}