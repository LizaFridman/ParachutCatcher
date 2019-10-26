package main.java.GraphicModels;

import javax.swing.*;
import java.net.URL;

public class Plane extends SpriteModel {
    private int originalX;
    private int minX = -50;
    public Plane(int x, int y, int horizontalSpeed) {
        super(x, y, horizontalSpeed, 0);
        this.originalX = x;

        URL url = getClass().getResource("/main/resources/plane.png");
        if (url != null) {
            this.setImage(new ImageIcon(url).getImage());
        }
    }

    public Parachutist dropParachutist(int parachutistSpeed, int score) {
        int parachutistX = getX()+(getImageWidth()/2) - 20;
        int parachutistY = getY()+(getImageWidth()/2);

        return new Parachutist(parachutistX, parachutistY, parachutistSpeed, score);
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        if (getX() < minX) {
            setX(originalX);
        }
    }

    // Updates the plane in relation to itself
    public void update(){
        moveLeft();
    }
}
