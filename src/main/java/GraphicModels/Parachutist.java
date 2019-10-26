package main.java.GraphicModels;

import javax.swing.*;
import java.net.URL;

public class Parachutist extends SpriteModel {
    private boolean isActive;
    private int score;

    public Parachutist(int x, int y, int verticalSpeed, int score) {
        super(x, y,0,verticalSpeed);
        this.isActive = true;
        this.score = score;

        URL url = getClass().getResource("/main/resources/parachutist.png");
        if (url != null) {
            this.setImage(new ImageIcon(url).getImage());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Updates the parachutist in relation to itself
    public void update(){
        moveDown();
    }
}
