package org.example.gamelogic;


import lombok.Getter;
import lombok.Setter;
import org.example.player.Player;
import org.example.ui.CommandLineUI;

@Getter
@Setter
public class Set {
    private Player player1;
    private Player player2;
    private Game currentGame;
    private Player winner;

    private static final Integer TWO = 2;
    private static final Integer FOUR = 4;
    private static final Integer FIVE = 5;
    private static final Integer SIX = 6;
    private static final Integer SEVEN = 7;

    private Integer player1SetScore;
    private Integer player1TieBreakScore;
    private Integer player2SetScore;
    private Integer player2TieBreakScore;

    // Constructor for our Set object to later instantiate it
    public Set(Match match) {
        this.player1 = match.getPlayer1();
        this.player2 = match.getPlayer2();
        player1SetScore = 0;
        player1TieBreakScore = 0;
        player2SetScore = 0;
        player2TieBreakScore = 0;
        currentGame = null;
        winner = null;
    }

    // Constructor for tests
    public Set(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1SetScore = 0;
        player1TieBreakScore = 0;
        player2SetScore = 0;
        player2TieBreakScore = 0;
        currentGame = null;
        winner = null;
    }

    // To play the set we instantiate the game with current match and play the game, incrementing set scores and displaying them
    void playSet (CommandLineUI commandLineUI) {
        do {
            currentGame = new Game(this);
            currentGame.playGame(commandLineUI);
            incrementSetScoreForPlayer(currentGame.getWinner());
            showSetScore(commandLineUI);
        } while (winner == null);

    }

    private void incrementSetScore(Player player) {
        if (player.equals(player1)) {
            player1SetScore++;
        } else {
            player2SetScore++;
        }
    }

    // Logic of conditions for incrementing the score for particular player - application of tennis rules
    void incrementSetScoreForPlayer(Player player) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // Set Score is ( 5 - 4 ) or ( 4 - 5 ) leading to ( 4 - 6 ) or ( 6 - 4 )
        // Action -> Increment Set Scores & Designate a Winner
        if ((player1SetScore.equals(FIVE) && player2SetScore <= FOUR && player1Scoring)
                || (player2SetScore.equals(FIVE) && player1SetScore <= FOUR && player2Scoring)) {
            incrementSetScore(player);
            designateWinner(player);
            // Set Score is ( 6 - 6 )
            // Action -> Activate tie-break rule
        } else if ((player2SetScore.equals(SIX) && player1SetScore.equals(SIX))) {
            activateTieBreak(player);
            // Set Score is ( 5 - 6 ) or ( 6 - 5 ) leading to ( 5 - 7 ) or ( 7 - 5 )
            // Action -> Increment Set Scores & Designate a Winner
        } else if ((player1SetScore.equals(SIX) && player2SetScore <= FIVE && player1Scoring)
                || (player2SetScore.equals(SIX) && player1SetScore <= FIVE && player2Scoring)) {
            incrementSetScore(player);
            designateWinner(player);
            // All other cases
            // Action -> Increment Set scores
        } else {
            incrementSetScore(player);
        }

    }

    // Tie Break logic
    private void activateTieBreak(Player player) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // Increment Tie-Break Score
        incrementTieBreakScore(player);
        // Tie-Break score is at least 7 + 2 Tie break points difference
        // Action -> Increment Set Scores & designate a winner
        if ((player1TieBreakScore >= SEVEN && (player1TieBreakScore >= (player2TieBreakScore + TWO)) && player1Scoring)
                || (player2TieBreakScore >= SEVEN && (player2TieBreakScore >= (player1TieBreakScore + TWO))) && player2Scoring) {
            incrementSetScore(player);
            designateWinner(player);
        }
    }

    // Basic logic for incrementing Tie-Break score
    private void incrementTieBreakScore(Player player) {
        if (player.equals(player1)) {
            player1TieBreakScore++;
        } else {
            player2TieBreakScore++;
        }
    }

    // Designate the winner. Assign the winner value to player, who won.
    private void designateWinner(Player player) {
        if (player1.equals(player)) {
            winner = player1;
        } else {
            winner = player2;
        }
    }

    // As Set has tie breaks, we implement the tie-break score display as well.
    private void showSetScore(CommandLineUI commandLineUI) {
        commandLineUI.showSetScore(player1SetScore, player2SetScore);

        if (player1TieBreakScore != 0 || player2TieBreakScore != 0) {
            commandLineUI.showTieBreakScore(player1TieBreakScore, player2TieBreakScore);
        }

        if (winner != null) {
            announceWinner(commandLineUI);
        }
    }

    // Simple announcement of the winner.
    private void announceWinner(CommandLineUI commandLineUI) {
        if (winner != null) {
            commandLineUI.showSetWinner(winner);
        }
    }

}