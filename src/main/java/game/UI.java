package main.java.game;

import javax.swing.*;

import java.awt.*;

import static main.java.game.Utils.GameConstants.WINDOW_HEIGHT;
import static main.java.game.Utils.GameConstants.WINDOW_WIDTH;

public class UI extends JFrame {
    private Game gamePanel;

    public UI(Player player) {
        gamePanel = new Game(player);

        setupGamePanel();
        setupGameFrame();
    }

    public void startGame(){
        setVisible(true);
    }

    private void setupGamePanel() {
        gamePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gamePanel.setDoubleBuffered(true);
        gamePanel.setFocusable(true);

        setupGameListener();
    }

    private void setupGameListener() {
        gamePanel.addKeyListener(gamePanel);
    }

    private void setupGameFrame() {
        this.add(gamePanel);
        this.setTitle("Parachutist Catcher");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
