package main.java.game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;

import static main.java.game.Utils.GameConstants.WINDOW_HEIGHT;
import static main.java.game.Utils.GameConstants.WINDOW_WIDTH;

public class UI extends JFrame {

    public UI(Player player) {
        Game startLevel  = new Game(player);

        setupGamePanel(startLevel);
        setupGameListener(startLevel);
        setupGameFrame(startLevel);
    }

    public void startGame(){
        setVisible(true);
    }

    private void setupGamePanel(Game gamePanel) {
        gamePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gamePanel.setDoubleBuffered(true);
        gamePanel.setFocusable(true);
    }

    private void setupGameListener(Game startLevel) {
        startLevel.addKeyListener(startLevel);
    }

    private void setupGameFrame(Game gamePanel) {
        this.add(gamePanel);
        this.setTitle("Parachutist Catcher");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
