package org.example;

import org.example.gamelogic.Match;
import org.example.ui.CommandLineUI;


public class Main {
    public static void main(String[] args) {
        CommandLineUI commandLineUI = new CommandLineUI();
        Match.startMatch(commandLineUI);
    }
}