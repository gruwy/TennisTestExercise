package org.example.gamelogic;

import lombok.Getter;
import lombok.Setter;
import org.example.player.Player;
import org.example.ui.CommandLineUI;

import java.util.Scanner;

@Getter
@Setter
public class Match {
    private static final Integer TWO = 2;
    private static final Integer THREE = 2;
    private Player player1;
    private Player player2;
    private Set currentSet;
    private Integer player1MatchScore;
    private Integer player2MatchScore;
    private Player winner;

    // Constructor for our Match class to instantiate it with the players that we manually set by user input at beginning point.
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1MatchScore = 0;
        player2MatchScore = 0;
        currentSet = null;
    }

    // startMatch method declares both players and instantiates the Match with support of commandLineUI.
    public static void startMatch(CommandLineUI commandLineUI) {
        // Showing the simple starting banner with exercise name.
        commandLineUI.showExerciseBanner();

        // Declaring the players
        Player player1 = declarePlayer1();
        Player player2 = declarePlayer2();

        // Showing banner with match start.
        commandLineUI.showMatchStartBanner();

        // Instantiating the match constructor with our declared players and beginning the play of the match.
        Match match = new Match(player1, player2);
        match.playMatch(commandLineUI);

        // After the match cycle is over, we show the end banner.
        commandLineUI.showTheEndBanner();
    }

    // Declare Player 1 with user input, save the input into String variable and instantiate player1 object with set name.
    public static Player declarePlayer1() {
        Scanner scannerInPlayer1 = new Scanner(System.in);
        System.out.print("Enter Player 1 Name: ");
        String player1name = scannerInPlayer1.nextLine();
        return new Player(player1name);
    }

    // Declare Player 2 with user input, save the input into String variable and instantiate player1 object with set name.
    public static Player declarePlayer2() {
        Scanner scannerInPlayer2 = new Scanner(System.in);
        System.out.print("Enter Player 2 Name: ");
        String player2name = scannerInPlayer2.nextLine();
        return new Player(player2name);
    }
    // We are playing the match by playing the Set. It is a part of whole match subsystem (Match -> Set -> Game).
    // As winner gets designated in sub-method, we announce him.
    public void playMatch(CommandLineUI commandLineUI) {
        do {
        currentSet = new Set(this);
        currentSet.playSet(commandLineUI);
        incrementMatchScoreForPlayer(currentSet.getWinner());
        showMatchScore(commandLineUI);
    } while(winner == null);
 }


    // Logic, where we are using predeclared static Integers to see, when the player wins 3 Match points - wins the Match.
    // Otherwise, match score is incremented for player.
    void incrementMatchScoreForPlayer(Player player) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);
        incrementMatchScore(player);

        if ((player1MatchScore.equals(THREE) && player2MatchScore <= TWO && player1Scoring)
                || (player2MatchScore.equals(THREE) && player1MatchScore <= TWO && player2Scoring)) {
            designateWinner(player);
        }
    }

    // This is a logic for adding Match points to the players in the Match.
    void incrementMatchScore(Player player) {
        if (player.equals(player1)) {
            player1MatchScore++;
        } else {
            player2MatchScore++;
        }
    }

    // Method to designate the winner of the Match.
    private void designateWinner(Player player) {
        if (player1.equals(player)) {
            winner = player1;
        } else {
            winner = player2;
        }
    }

    // Method to display match score.
    // If winner is designated, then we announce winner as well.
    private void showMatchScore(CommandLineUI commandLineUI) {
        commandLineUI.showMatchScore(getPlayer1MatchScore(), getPlayer2MatchScore());

        if (winner != null) {
            announceWinner(commandLineUI);
        }
    }

    // Simple announcement of the winner.
    private void announceWinner(CommandLineUI commandLineUI) {
        if (winner != null) {
            commandLineUI.showMatchWinner(winner);
        }
    }
}