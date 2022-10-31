package org.example.gamelogic;

import lombok.Getter;
import lombok.Setter;
import org.example.player.Player;
import org.example.ui.CommandLineUI;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Game {
    private Player player1;
    private Player player2;
    private Player winner;
    private Integer player1GameScore;
    private Integer player2GameScore;
    public static final List<String> points = Arrays.asList("LOVE", "15", "30", "40", "ADV");
    private static final Integer FORTY_SCORE = 3;
    private static final Integer ADVANTAGE_SCORE = 4;

    public Game(Set set) {
        this.player1 = set.getPlayer1();
        this.player2 = set.getPlayer2();
        player1GameScore = 0;
        player2GameScore = 0;
        winner = null;
    }

    // Constructor for Tests
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1GameScore = 0;
        player2GameScore = 0;
        winner = null;
    }

    // We play the game until the winner is determined.
    void playGame(CommandLineUI commandLineUI) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~ Game Start! ~~~~~~~~~~~~~~~~~~~~~~~");
        do {
            // Get random player to score in current game.
            Player player = retrieveRandomPlayer(this);
            // Increment his points.
            incrementGameScoreForPlayer(player, commandLineUI);
            // Show current game score.
            showGameScore(commandLineUI);
        } while (winner == null);
    }

    // Get random player. If value is more than 0.5, we take player 1 - else player 2.
    // Math.random() returns pseudo-random number that's greater than or equal to 0 and less than 1.
    private Player retrieveRandomPlayer(Game game) {
        return (Math.random() < 0.5) ? game.getPlayer1() : game.getPlayer2();
    }

    // Add Score to the player who won.
    // We translate the incremented Game Scores into points via List declared above.
    private void incrementGameScore(Player player, CommandLineUI commandLineUI) {
        // We show who scored a point.
        commandLineUI.showPlayerScorePoint(player);
        // ... and increment the game score for the player, who meets the condition.
        if (player1.equals(player)) {
            player1GameScore++;
        } else {
            player2GameScore++;
        }
    }

    // Logic of which conditions the score is being incremented.
    // Pretty much, we increment the scores all the time, but not when game score is 40-40 or when one of players scores after having 40 points.
    void incrementGameScoreForPlayer(Player player, CommandLineUI commandLineUI) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // Designate winner who scores after 40.
        if ((player1GameScore.equals(FORTY_SCORE) && player2GameScore < FORTY_SCORE && player1Scoring)
           || (player2GameScore.equals(FORTY_SCORE) && player1GameScore < FORTY_SCORE && player2Scoring)) {
                designateWinner(player, commandLineUI);
        }

        // If Game Score is ( 40 - 40 ) or above, we activate the deuce rule.
        else if (player1GameScore >= FORTY_SCORE && player2GameScore >= FORTY_SCORE) {
            activateDeuceRule(player, commandLineUI);
        }

        // In all other cases we increment the game score for player by default.
        else {
            incrementGameScore(player, commandLineUI);
        }
    }

    // Deuce rule logic.
    private void activateDeuceRule(Player player, CommandLineUI commandLineUI) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // If Game Score is ( 40 - 40 ), we increment score to ADV.
        if (player1GameScore.equals(FORTY_SCORE) && player2GameScore.equals(FORTY_SCORE)) {
            incrementGameScore(player, commandLineUI);
        }

        // If Game Score is ( ADV - 40 ) or ( 40 - ADV ), leading to score above ADV.
        // Action -> Designate a winner.
        else if ((player1GameScore.equals(ADVANTAGE_SCORE) && player2GameScore.equals(FORTY_SCORE) && player1Scoring)
                || (player2GameScore.equals(ADVANTAGE_SCORE) && player1GameScore.equals(FORTY_SCORE) && player2Scoring)) {
                    designateWinner(player, commandLineUI);
        }

        // If Game Score is ( ADV - 40 ) or ( 40 - ADV ), leading to score ( ADV - ADV ).
        // Action -> Increment & reset the scores, starting Deuce rule once again.
        else if ((player1GameScore.equals(FORTY_SCORE) && player2GameScore.equals(ADVANTAGE_SCORE) && player1Scoring)
                || (player2GameScore.equals(FORTY_SCORE) && player1GameScore.equals(ADVANTAGE_SCORE) && player2Scoring)) {
                    incrementGameScore(player, commandLineUI);
                    resetGameScoresDeuce(commandLineUI);
        }
    }

    // Method to reset the scores to 40 during Deuce rule.
    private void resetGameScoresDeuce(CommandLineUI commandLineUI) {
        commandLineUI.showDeuceRule();
        setPlayer1GameScore(FORTY_SCORE);
        setPlayer2GameScore(FORTY_SCORE);
    }

    // Designate the winner. Assign the winner value to player, who won.
    private void designateWinner(Player player, CommandLineUI commandLineUI) {
        if (player1.equals(player)) {
            winner = player1;
            commandLineUI.showPlayerScorePoint(winner);

        } else {
            winner = player2;
            commandLineUI.showPlayerScorePoint(winner);
        }
        resetGameScores();
    }

    // Reset Game score to start over.
    private void resetGameScores() {
        setPlayer1GameScore(0);
        setPlayer2GameScore(0);
    }

    // We show Game Score by gathering descriptions for both players of their scores.
    // If we have a winner, then we announce him.
    void showGameScore(CommandLineUI commandLineUI) {
        if (winner == null) {
            commandLineUI.showGameScore(getScoreDescription(player1GameScore), getScoreDescription(player2GameScore));
        } else announceWinner(commandLineUI);
    }

    // Get Score Description from the list declared above.
    private String getScoreDescription(Integer gameScore) {
        return points.get(gameScore);
    }

    // Simple announcement of the winner.
    private void announceWinner(CommandLineUI commandLineUI) {
        if (winner != null) {
            commandLineUI.showGameWinner(winner);
        }
    }
}