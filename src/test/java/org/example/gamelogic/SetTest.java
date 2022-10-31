package org.example.gamelogic;

import org.example.player.Player;
import org.example.ui.CommandLineUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    private final String PLAYER_1 = "Player 1";
    private final String PLAYER_2 = "Player 2";

    @Test
    public void setHappyScenarioPlayer1_6_0() {
        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Set set = new Set(player1, player2);
        Match match = new Match(player1, player2);

        // Creating test dummy array
        List<Player> happyScenarioPlayer1 = new ArrayList<>();
        happyScenarioPlayer1.add(player1); // ( 15 - LOVE )
        happyScenarioPlayer1.add(player1); // ( 30 - LOVE )
        happyScenarioPlayer1.add(player1); // ( 40 - LOVE )
        happyScenarioPlayer1.add(player1); // Win

        // While winner is not determined, we increment game score by array instructions, incrementing the set score
        while (set.getWinner() == null) {
            set.setCurrentGame(new Game(player1,player2));
            for (Player player : happyScenarioPlayer1) {
                set.getCurrentGame().incrementGameScoreForPlayer(player, commandLineUI);
                set.getCurrentGame().showGameScore(commandLineUI);
            }
            set.incrementSetScoreForPlayer(set.getCurrentGame().getWinner());
            commandLineUI.showSetScore(set.getPlayer1SetScore(),set.getPlayer2SetScore());
        }

        // Assert that Player1 wins the Set
        try {
            assertEquals(player1, set.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }

    @Test
    public void setHappyScenarioPlayer2_0_6() {
        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Set set = new Set(player1, player2);

        // Creating test dummy array
        List<Player> happyScenarioPlayer2 = new ArrayList<>();
        happyScenarioPlayer2.add(player2); // ( LOVE - 15 )
        happyScenarioPlayer2.add(player2); // ( LOVE - 30 )
        happyScenarioPlayer2.add(player2); // ( LOVE - 40 )
        happyScenarioPlayer2.add(player2); // Win

        // While winner is not determined, we increment game score by array instructions, incrementing the set score
        while (set.getWinner() == null) {
            set.setCurrentGame(new Game(player1,player2));
            for (Player player : happyScenarioPlayer2) {
                set.getCurrentGame().incrementGameScoreForPlayer(player, commandLineUI);
                set.getCurrentGame().showGameScore(commandLineUI);
            }
            set.incrementSetScoreForPlayer(set.getCurrentGame().getWinner());
            commandLineUI.showSetScore(set.getPlayer1SetScore(),set.getPlayer2SetScore());
        }

        // Assert that Player2 wins the Set
        try {
            assertEquals(player2, set.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }
}