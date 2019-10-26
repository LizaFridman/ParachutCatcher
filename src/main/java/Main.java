package main.java;

import main.java.game.Player;
import main.java.game.UI;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(0,3, "Catcher");

        SwingUtilities.invokeLater(() -> {
            UI gameUI = setupUI(player);
            gameUI.startGame();
        });
    }

    public static UI setupUI(Player player){
         return new UI(player);
    }
}
