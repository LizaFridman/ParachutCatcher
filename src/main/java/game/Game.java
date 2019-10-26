package main.java.game;

import main.java.GraphicModels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.game.Utils.GameConstants.*;

public class Game extends JPanel implements Runnable, KeyListener {
    private Player player;
    private boolean isGameOver;

    private Thread gameThread;
    private Random randomizer = new Random();

    private Background background;
    private Plane plane;
    private Boat boat;
    private Sea sea;

    private List<Parachutist> parachutists;
    private int maxActiveParachutists = 3;
    private long lastDropped;
    private int dropSpeed = 3;
    private double parachutistPerSecond = 0.5;
    private int parachutistScore = 10;

    public Game(Player player) {
        this.player = player;
        background = new Background(0, 0);
        boat = new Boat(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 200,10);
        sea = new Sea(0, WINDOW_HEIGHT - 100);
        plane = new Plane(WINDOW_WIDTH-110, 10,3);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(Game.this);
        }
        gameThread.start();
    }

    @Override
    public void run() {
        resetGame();
        requestFocusInWindow();

        while(!isGameOver){
            long beforeUpdate = System.currentTimeMillis();

            updateGame();
            renderGame();

            long updateDuration = System.currentTimeMillis() - beforeUpdate;
            try {
                Thread.sleep(FRAME_TIME - updateDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(isGameOver){
            GameOver();
        }
    }

    private void resetGame() {
        isGameOver = false;
        parachutists = new ArrayList<>(maxActiveParachutists);
    }

    private void GameOver() {
        System.out.println("Game Over!");
    }

    private void updateGame(){
        updatePlane();
        updateParachutists();
    }

    // Updates the plane in relation to the game
    private void updatePlane() {
        plane.update();
    }

    // Updates the parachutists in relation to the game
    private void updateParachutists() {
        if(shouldDrop()) {
            dropParachutist();
        }

        for (int pIdx=0; pIdx< parachutists.size(); pIdx++) {
            Parachutist p = parachutists.get(pIdx);

            p.update();

            if(p.isCollided(sea)){
                missedParachutist(p);
            }else if(p.isCollided(boat)){
                catchedParachutist(p);
            }
        }
    }

    private void dropParachutist() {
        int randomSpeed = randomizer.nextInt(2) - 1;
        int parachutistSpeed = dropSpeed + randomSpeed;

        Parachutist droppedParachutist = plane.dropParachutist(parachutistSpeed, parachutistScore);

        parachutists.add(droppedParachutist);
        lastDropped = System.currentTimeMillis();
    }

    private boolean shouldDrop(){
        boolean exceededMaxParachutists = parachutists.size() >= maxActiveParachutists;

        long timePassedDropped = System.currentTimeMillis() - lastDropped;
        boolean timeToDrop = timePassedDropped > (1000 / parachutistPerSecond);

        boolean randomDrop = randomizer.nextBoolean();

        return !exceededMaxParachutists && timeToDrop && randomDrop;
    }

    private void catchedParachutist(Parachutist p) {
        player.addToScore(p.getScore());
        deactivateParachutist(p);
    }

    private void missedParachutist(Parachutist p) {
        isGameOver = player.loseLife() < 1;
        deactivateParachutist(p);
    }

    private void deactivateParachutist(Parachutist p) {
        parachutists.remove(p);
    }

    private void renderGame(){
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw((Graphics2D) graphics);
    }

    private void draw(Graphics2D graphics) {
        drawGameElements(graphics);
        drawScoreBoard(graphics);

        if (isGameOver) {
            drawGameOver(graphics);
        }
    }

    private void drawGameOver(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
        graphics.drawString("Game Over!", WINDOW_WIDTH/3 - 10, WINDOW_HEIGHT/3);
        graphics.drawString("Final Score: "+player.getScore(), WINDOW_WIDTH/3 - 30, WINDOW_HEIGHT/3 + 50);
    }

    private void drawGameElements(Graphics2D graphics){
        background.draw(graphics);

        sea.draw(graphics);
        boat.draw(graphics);
        plane.draw(graphics);

        for (Parachutist p : parachutists) {
            if(p.isActive()) {
                p.draw(graphics);
            }
        }
    }

    private void drawScoreBoard(Graphics2D graphics){
        graphics.setColor(Color.black);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        drawLivesString(graphics);
        drawScoreString(graphics);
    }

    private void drawLivesString(Graphics2D graphics) {
        graphics.drawString("Life: ", 10, 30);
        graphics.drawString(player.getLives() + "", 85, 30);
    }

    private void drawScoreString(Graphics2D graphics) {
        graphics.drawString("Score: ", 10, 50);
        graphics.drawString(player.getScore() + "", 85, 50);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                boat.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                boat.moveRight();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) { }
}
