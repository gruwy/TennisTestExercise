package org.example.gamelogic;

import org.example.player.Player;
import org.example.ui.CommandLineUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private final String PLAYER_1 = "Player 1";
    private final String PLAYER_2 = "Player 2";

    @Test
    public void matchHappyScenarioPlayer1_3_0() {

        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Match match = new Match(player1, player2);
        Integer matchCount = 0;

        // While winner of the match is not determined, we instantiate a new game, play the game until it scores, score until the winner is determined
        // Later we increment the match score and increment the counter of matches
        while (matchCount < 3) {
            match.setCurrentSet(new Set(player1, player2));
            List<Player> happyScenarioPlayer1 = new ArrayList<>();
            happyScenarioPlayer1.add(player1); // ( 15 - LOVE )
            happyScenarioPlayer1.add(player1); // ( 30 - LOVE )
            happyScenarioPlayer1.add(player1); // ( 40 - LOVE )
            happyScenarioPlayer1.add(player1); // Win

            while (match.getCurrentSet().getWinner() == null) {
                match.getCurrentSet().setCurrentGame(new Game(player1, player2));
                for (Player player : happyScenarioPlayer1) {
                    match.getCurrentSet().getCurrentGame().incrementGameScoreForPlayer(player, commandLineUI);
                    match.getCurrentSet().getCurrentGame().showGameScore(commandLineUI);
                }
                match.getCurrentSet().incrementSetScoreForPlayer(match.getCurrentSet().getCurrentGame().getWinner());
                commandLineUI.showSetScore(match.getCurrentSet().getPlayer1SetScore(),match.getCurrentSet().getPlayer2SetScore());
            }
            match.incrementMatchScoreForPlayer(match.getCurrentSet().getWinner());
            commandLineUI.showMatchScore(match.getPlayer1MatchScore(), match.getPlayer2MatchScore());
            matchCount++;

        }
        // Assert that Player1 wins the Match
        try {
            assertEquals(player1, match.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }

    @Test
    public void matchHappyScenarioPlayer2_0_3() {

        CommandLineUI commandLineUI = new CommandLineUI();
        Player player1 = new Player(PLAYER_1);
        Player player2 = new Player(PLAYER_2);
        Match match = new Match(player1, player2);
        Integer matchCount = 0;

        // While winner of the match is not determined, we instantiate a new game, play the game until it scores, score until the winner is determined
        // Later we increment the match score and increment the counter of matches
        while (matchCount < 3) {
            match.setCurrentSet(new Set(player1, player2));
            List<Player> happyScenarioPlayer2 = new ArrayList<>();
            happyScenarioPlayer2.add(player2); // ( LOVE - 15 )
            happyScenarioPlayer2.add(player2); // ( LOVE - 30 )
            happyScenarioPlayer2.add(player2); // ( LOVE - 40 )
            happyScenarioPlayer2.add(player2); // Win

            while (match.getCurrentSet().getWinner() == null) {
                match.getCurrentSet().setCurrentGame(new Game(player1, player2));
                for (Player player : happyScenarioPlayer2) {
                    match.getCurrentSet().getCurrentGame().incrementGameScoreForPlayer(player, commandLineUI);
                    match.getCurrentSet().getCurrentGame().showGameScore(commandLineUI);
                }
                match.getCurrentSet().incrementSetScoreForPlayer(match.getCurrentSet().getCurrentGame().getWinner());
                commandLineUI.showSetScore(match.getCurrentSet().getPlayer1SetScore(),match.getCurrentSet().getPlayer2SetScore());
            }
            match.incrementMatchScoreForPlayer(match.getCurrentSet().getWinner());
            commandLineUI.showMatchScore(match.getPlayer1MatchScore(), match.getPlayer2MatchScore());
            matchCount++;
        }

        // Assert that Player2 wins the Match
        try {
            assertEquals(player2, match.getWinner());
        } catch (AssertionError e) {
            System.out.println("\n Test Result: Failed!");
            throw e;
        }
        System.out.println("\n Test Result: Passed!");
    }
}