package org.example.gamelogic;

import org.example.player.Player;
import org.example.ui.CommandLineUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final String PLAYER_1 = "Player 1";
    private final String PLAYER_2 = "Player 2";

    @Test
    public void gameHappyScenarioPlayer1_40_0() {

        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Game game = new Game(player1, player2);

        // Creating test dummy array
        List<Player> happyScenarioPlayer1 = new ArrayList<>();
        happyScenarioPlayer1.add(player1); // ( 15 - LOVE )
        happyScenarioPlayer1.add(player1); // ( 30 - LOVE )
        happyScenarioPlayer1.add(player1); // ( 40 - LOVE )
        happyScenarioPlayer1.add(player1); // Win

        // For each player in happy scenario player list, increment game score and show game score
        for (Player player : happyScenarioPlayer1) {
            game.incrementGameScoreForPlayer(player, commandLineUI);
            game.showGameScore(commandLineUI);
        }

        // Assert that Player1 wins the game
        try {
            assertEquals(player1, game.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }

    @Test
    public void gameHappyScenarioPlayer2_0_40() {  // Sought game result ( 0 - 40 )

        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Game game = new Game(player1, player2);

        List<Player> happyScenarioPlayer2 = new ArrayList<>();
        happyScenarioPlayer2.add(player2); // ( LOVE - 15 )
        happyScenarioPlayer2.add(player2); // ( LOVE - 30 )
        happyScenarioPlayer2.add(player2); // ( LOVE - 40 )
        happyScenarioPlayer2.add(player2); // Win

        for (Player player : happyScenarioPlayer2) {
            game.incrementGameScoreForPlayer(player, commandLineUI);
            game.showGameScore(commandLineUI);
        }

        // Assert that Player2 wins the game
        try {
            assertEquals(player2, game.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }
}