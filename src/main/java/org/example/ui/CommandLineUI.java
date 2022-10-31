package org.example.ui;

import org.example.player.Player;

public class CommandLineUI {
    public void showExerciseBanner() {
        System.out.println("////////////////////////////////////////////////////////////");
        System.out.println("/////////////////// Tennis Test Exercise ///////////////////");
        System.out.println("////////////////////////////////////////////////////////////\n");
    }

    public void showMatchStartBanner() {
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@ Let's Start the Match! @@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public void showPlayerScorePoint(Player player)
    {
        System.out.println("Player '" + player.getName() + "' scored 1 point!");
    }

    public void showGameScore(String player1GameScore, String player2GameScore) {
        System.out.println("               The Game Score is: (" + player1GameScore + " - " + player2GameScore + ")");
    }

    public void showGameWinner(Player player) {
        System.out.println("************************************************************");
        System.out.println("               The Winner of the Game is: " + player.getName());
        System.out.println("************************************************************");
    }

    public void showSetScore(Integer player1SetScore, Integer player2SetScore) {
        System.out.println("                 The Set Score is: (" + player1SetScore + " - " + player2SetScore + ")");
        System.out.println("************************************************************");
    }

    public void showSetWinner(Player player) {
        System.out.println("               The Winner of the Set is: " + player.getName());
        System.out.println("************************************************************");
    }

    public void showDeuceRule() {
        System.out.println("************************************************************");
        System.out.println("                    Deuce! Rule applied.");
        System.out.println("************************************************************");
    }

    public void showTieBreakScore(Integer player1TieBreakScore, Integer player2TieBreakScore) {
        System.out.println("            The Tie Break Score is: (" + player1TieBreakScore + " - " + player2TieBreakScore + ")");
        System.out.println("************************************************************");
    }

    public void showMatchScore(Integer player1MatchScore, Integer player2MatchScore) {
        System.out.println("               The Match Score is: (" + player1MatchScore + " - " + player2MatchScore + ")");
        System.out.println("************************************************************");
    }

    public void showMatchWinner(Player player) {
        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("            The Winner of the Match is: " + player.getName());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    public void showTheEndBanner() {
        System.out.println("\n////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////// The End /////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////\n");
    }
}
