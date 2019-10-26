package main.java;

import main.java.game.Game;
import main.java.game.Player;
import main.java.game.UI;

import javax.swing.SwingUtilities;

import static main.java.game.Utils.GameConstants.*;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(0,3, "Catcher");

        SwingUtilities.invokeLater(() -> {
            UI gameUI = setupFrame(player);
            gameUI.startGame();
        });
    }

    public static UI setupFrame(Player player){
         return new UI(player);
    }
}
