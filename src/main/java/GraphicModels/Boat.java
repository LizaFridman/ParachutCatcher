package main.java.GraphicModels;

import javax.swing.*;
import java.net.URL;

import static main.java.game.Utils.GameConstants.WINDOW_WIDTH;

public class Boat extends SpriteModel {
    private int maxX = WINDOW_WIDTH-220;
    private int minX = 0;

    public Boat(int x, int y, int horizontalSpeed) {
        super(x, y, horizontalSpeed, 0);

        URL url = getClass().getResource("/main/resources/boat.png");
        if (url != null) {
            this.setImage(new ImageIcon(url).getImage());
        }
    }

    @Override
    public void moveLeft(){
        super.moveLeft();
        if(getX() < minX){
            setX(minX);
        }
    }

    @Override
    public void moveRight(){
        super.moveRight();
        if(getX() > maxX){
            setX(maxX);
        }
    }
}