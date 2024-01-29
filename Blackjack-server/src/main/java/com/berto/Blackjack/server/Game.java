package com.berto.Blackjack.server;

import com.berto.Blackjack.server.dao.OpenAiService;
import com.berto.Blackjack.server.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.berto.Blackjack.server.Constants.*;

public class Game {
    private final Dealer dealer = new Dealer();

    private Deck deck = Deck.getInstance();
    private final OpenAiService openAiService = new OpenAiService();
    //Used for payout and compare hands
    Set<Player> setOfPlayersNotBust = new HashSet<>();
    //Used to remove bets from their balances
    Set<Player> setOfPlayersBust = new HashSet<>();
    private Set<Person> setOfPlayers = new HashSet<>();
    private Map<String, Integer> betsMap = new HashMap<>();
    private boolean useBots = true;

    public Dealer getDealer() {
        return dealer;
    }



    public Set<Person> getSetOfPlayers() {
        return setOfPlayers;
    }

    public void setSetOfPlayers(Set<Person> setOfPlayers) {
        this.setOfPlayers = setOfPlayers;
    }

//    public void addBots() {
//
//        while (setOfPlayers.size() < 5) {
//
//            OpenAIBot bot = new OpenAIBot(openAiService);
//
//            setOfPlayers.add(bot);
//
//        }
//    }

//    public void addPlayerFunds(String playerName) {
//
//        //Filters for Player that has the matching name
//        if (setOfPlayers.contains(new Person(playerName))) {
//
//            while (true) {
//                try {
//                    System.out.println("How much should be added?");
//                    String amount = scanner.nextLine();
//
//                    Player person = setOfPlayers.stream().filter(p -> p.getName().equals(playerName))
//                            .collect(Collectors.toList()).get(0);
//                    person.addToBalance(Integer.parseInt(amount));
//
//                    break;
//
//                } catch (NumberFormatException numberFormatException) {
//
//                    System.out.println("Invalid amount");
//                }
//            }
//
//        } else {
//
//            System.out.println(playerName + " doesn't exist");
//
//        }
//    }

    public void addPlayers(String inputPlayers) {

        String[] playerNames = inputPlayers.split(",");

        for (String playerName : playerNames) {
            //Player names must be unique
            Player newPerson = new Person(playerName.trim());

//            if (!newPerson.getName().isBlank()) {
//
//                boolean added = setOfPlayers.add(newPerson);
//                if (!added) {
//                    System.out.println("Player with the name " + playerName.trim() + " already exists");
//                }
//            }
        }
    }

    public void loadNewDeck() {

        deck.loadDeck();

    }

    public void payBets() {

        System.out.println("********* Results *********");
        System.out.println();

        removeFromBustedPlayersBalance(setOfPlayersBust);
        System.out.println();

        if (setOfPlayersNotBust.isEmpty()) {
            System.out.println("All players busted");

        } else {
            System.out.println("Dealer score " + dealer.getHandScore());
            System.out.println();

            for (Player person : setOfPlayersNotBust) {
                //If dealer busted everyone still in it gets paid
                if (dealer.getHandScore() > HIGHEST_POSSIBLE_SCORE) {
                    payoutWhenDealerBusts(person);

                } else {
                    payoutWhenDealerNotBustAtLeastOnePlayerNotBust(person);

                }
            }
        }

        System.out.println();
        //Reset the map for new round of betting
        betsMap = new HashMap<>();

    }

    public void playRound() {

        //reset lists for new round
        setOfPlayersNotBust = new HashSet<>();

        setOfPlayersBust = new HashSet<>();

//        if (useBots) {
//
//            addBots();
//        }

        //Load a new deck if deck is running out of cards
        if (deck.getDeckSize() < MIN_PLAYABLE_DECK_SIZE) {
            System.out.println("Loading new deck");
            loadNewDeck();

        }

        //getBets();

        dealStartingHands();

        //Ask the player if they want to keep hitting unless they have 21
        runPlayPhase();

        //Player choice is over, reveal dealer hidden card
        runDealerPhase();

        printHands();

        //Pays bets and prints results
        payBets();

        System.out.println("Play another round? y/n");
//        String answer = scanner.nextLine().toLowerCase();
//
//        if (answer.equals("y")) {
//            playRound();
//
//        }
    }

    public void printPlayersAndBalances() {

        for (Player person : setOfPlayers) {

            System.out.print(person.getName() + "'s balance is : ");
            System.out.println(person.getBalance());

        }
    }

    public void removePlayers(String inputPlayers) {

        String[] playerNames = inputPlayers.split(",");

        for (String playerName : playerNames) {

            setOfPlayers = setOfPlayers.stream()
                    .filter(person -> !person.getName().equals(playerName.trim())).collect(Collectors.toSet());

        }
    }

//    public void toggleBots() {
//
//        String userChoice = scanner.nextLine();
//        useBots = userChoice.equalsIgnoreCase("y");
//        if (useBots) {
//            System.out.println("Bots are turned on");
//        } else System.out.println("Bots are turned off");
//
//
//    }

    /**
     * @return -1 for loss, 0 for tie, 1 for win
     */
    private int compareHandsGetResult(Player person, Dealer dealer) {

        for (Hand hand : person.getHands()) {

            return hand.compareTo(dealer.getHand());
        }

        return 0;

    }

    private void dealStartingHands() {

        dealer.setUpStartingHand(deck);

        for (Player person : setOfPlayers) {
            person.setUpStartingHand(deck);

        }
    }

//    private void getBets() {
//
//        for (Player currentPerson : setOfPlayers) {
//
//            if (currentPerson instanceof OpenAIBot) {
//
//                int betAmount = ((OpenAIBot) currentPerson).getBetAmount();
//                betsMap.put(currentPerson.getName(), betAmount);
//
//            } else {
//
//            int playerBalance = currentPerson.getBalance();
//
//            while (true) {
//
//                try {
//
//                    int betAmount = 0;
//
//                    while (betAmount < 200) {
//
//                        System.out.println(currentPerson.getName() + " you have " + currentPerson.getBalance()
//                                + " chips how many chips will you bet? (200 minimum)");
//                        betAmount = Integer.parseInt(scanner.nextLine());
//                    }
//
//                    if (betAmount <= playerBalance) {
//                        betsMap.put(currentPerson.getName(), betAmount);
//                        System.out.println("Bet is placed");
//                    }
//
//                    break;
//
//                } catch (NumberFormatException numberFormatException) {
//                    System.out.println("Invalid amount");
//
//                }
//            }
//            }
//        }
//    }

    private void payoutWhenDealerBusts(Player person) {

        for (Hand hand : person.getHands()) {

        //Checks if a person got blackjack, so they can get paid 1.5
        if (hand.getHandSize() == 2
                && hand.getHandScore() == HIGHEST_POSSIBLE_SCORE) {

            System.out.println(
                    person.getName() + " wins with Blackjack! +" + (int) (betsMap.get(person.getName()) * 1.5));
            person.addToBalance(
                    (int) (betsMap.get(person.getName()) * 1.5));

            //Pay 1 to 1 to players that didn't bust
        } else {

            System.out.println("Dealer busts " + person.getName() + " wins!" + " +" + betsMap.get(
                    person.getName()));

            person.addToBalance(betsMap.get(person.getName()));

        }
        }
    }

    private void payoutWhenDealerNotBustAtLeastOnePlayerNotBust(Player person) {

        int result = compareHandsGetResult(person, dealer);

        //Calculate remaining players hand vs dealer

        for (Hand hand : person.getHands()) {

        if (result == -1 && dealer.getHand().getHandScore() == HIGHEST_POSSIBLE_SCORE && dealer.getHand().getHandSize() == 2) {
            System.out.println(
                    person.getName() + " loses with " + hand.getHandScore() + " against dealer Blackjack -" + betsMap.get(
                            person.getName()));
            person.subtractFromBalance(betsMap.get(person.getName()));

        } else if (result == -1) {
            System.out.println(
                    person.getName() + " loses with " + hand.getHandScore() + " -" + betsMap.get(
                            person.getName()));
            person.subtractFromBalance(betsMap.get(person.getName()));

        } else if (result == 0) {
            System.out.println(
                    person.getName() + " ties with " + hand.getHandScore());

            //Check if person wins with Blackjack
        } else if (result == 1 && hand.getHandSize() == 2
                && hand.getHandScore() == HIGHEST_POSSIBLE_SCORE) {

            System.out.println(
                    person.getName() + " wins with Blackjack! +" + (int) (betsMap.get(person.getName()) * 1.5));
            person.addToBalance(
                    (int) (betsMap.get(person.getName()) * 1.5));

        } else if (result == 1) {

            System.out.println(
                    person.getName() + " wins with " + hand.getHandScore() + " +" + betsMap.get(
                            person.getName()));
            person.addToBalance(betsMap.get(person.getName()));

        }

        }
    }

    private void printHands() {
        System.out.println();
        System.out.println("********* Hands *********");
        System.out.println();

        for (Player currentPerson : setOfPlayers) {

            for (Hand hand : currentPerson.getHands()) {

                System.out.println(currentPerson.getName() + "'s hand");
                System.out.println(currentPerson.getHands());
                System.out.println("Hand score " + hand.getHandScore());

                if (hand.getHandScore() > HIGHEST_POSSIBLE_SCORE) {
                    System.out.println(currentPerson.getName() + " busts!");

                }

                System.out.println("------------------------------");
            }

        }

        System.out.println("Dealer's hand");

        // Prints dealers hand and score depending on if they're hiding a card
        if (dealer.isHidingCard()) {
            System.out.println("Hidden card, " + dealer.getHand());

            if (dealer.getHand().getNumberOfAces() > 0) {
                System.out.println("Visible score 1/11");

            } else {
                System.out.println("Visible score " + dealer.getHandScore());

            }

        } else {
            System.out.println(dealer.getHand());
            System.out.println("Dealer score " + dealer.getHandScore());

        }

        System.out.println();

    }

    private void removeFromBustedPlayersBalance(Set<Player> setOfPlayersThatBusted) {
        //Take bet from players that busted
        for (Player person : setOfPlayersThatBusted) {

            for (Hand hand : person.getHands()){

                System.out.println(
                        person.getName() + " busted with " + hand.getHandScore() + " -" + betsMap.get(person.getName()));
                person.subtractFromBalance(betsMap.get(person.getName()));

                if (person.getBalance() == 0) {
                    setOfPlayers.remove(person);
                }

            }



        }
    }

    private void runDealerPhase() {
        dealer.setHidingCard(false);

        if (!setOfPlayersNotBust.isEmpty()) {
            while (dealer.getHandScore() < DEALER_STOP_SCORE) {
                dealer.getHand().addCardToHand(deck.getRandomCard());

            }
        }
    }

    private void runPlayPhase() {

        for (Player currentPerson : setOfPlayers) {

            for (Hand hand : currentPerson.getHands()) {

                if (currentPerson instanceof OpenAIBot) {

                    String botDecision = ((OpenAIBot) currentPerson).hitOrStay(dealer.getHand());

                    while (botDecision.equals("h")) {

                        hand.addCardToHand(deck.getRandomCard());

                        if (hand.getHandScore() > 21) {

                            setOfPlayersBust.add(currentPerson);
                            break;

                        } else {

                            botDecision = ((OpenAIBot) currentPerson).hitOrStay(dealer.getHand());

                        }


                    }

                } else {

                    while (hand.getHandScore() < HIGHEST_POSSIBLE_SCORE) {

                        printHands();

//                        System.out.println(
//                                currentPerson.getName() + " you have " + hand.getHandScore()
//                                        + " hit or stay? (h/s)");
//                        String hitAnswer = scanner.nextLine().toLowerCase();
//
//                        if (hitAnswer.equals("h")) {
//
//                            hand.addCardToHand(deck.getRandomCard());
//
//                        } else {
//                            break;
//                        }
                    }
                }

                if (hand.getHandScore() <= HIGHEST_POSSIBLE_SCORE) {

                    setOfPlayersNotBust.add(currentPerson);

                } else {

                    setOfPlayersBust.add(currentPerson);

                }
            }
        }
    }
}