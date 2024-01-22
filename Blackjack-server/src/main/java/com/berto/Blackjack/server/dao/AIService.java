package com.berto.Blackjack.server.dao;

import com.berto.Blackjack.server.model.Hand;

public interface AIService {

    int getBetAmount(int chipBalance);

    String askDoubleUp();

    String askHitOrStay(Hand botHand, Hand dealerHand);

    String askSplitHand();

}