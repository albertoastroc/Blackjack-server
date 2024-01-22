package com.berto.Blackjack.server.model;

import com.berto.Blackjack.server.dao.AIService;
import com.berto.Blackjack.server.dao.OpenAiService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OpenAIBot extends Player {

    private final AIService openAIDao;

    Random random = new Random();

    public OpenAIBot(OpenAiService openAIDao) {



        this.setName("Bot " + random.nextInt(100));
        this.openAIDao = openAIDao;
    }

    public int getBetAmount() {

        return openAIDao.getBetAmount(getBalance());

    }

    public String hitOrStay(Hand dealerHand) {

        for (Hand hand : getHands()) {

            return openAIDao.askHitOrStay(hand, dealerHand);

        }

        return "Error in hitOrStay";
    }
}