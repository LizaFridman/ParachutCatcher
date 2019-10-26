package main.java.GraphicModels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class SpriteModel implements GraphicsModel {
    private int x;
    private int y;
    private int horizontalSpeed;
    private int verticalSpeed;

    private Image image;
    private int imageWidth;
    private int imageHeight;

    public SpriteModel(int x, int y, int horizontalSpeed, int verticalSpeed) {
        this.x = x;
        this.y = y;
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.imageWidth = image != null ? image.getWidth(null) : 0;
        this.imageHeight = image != null ? image.getHeight(null) : 0;
    }

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public int getVerticalSpeed() {
        return verticalSpeed;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void draw(Graphics2D g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    public void moveRight() {
        setX(getX() + this.horizontalSpeed);
    }

    public void moveLeft() {
        setX(getX() - this.horizontalSpeed);
    }

    public void moveUp() {
        setY(getY() - verticalSpeed);
    }

    public void moveDown() {
        setY(getY() + verticalSpeed);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getImageWidth(), getImageHeight());
    }

    public boolean isCollided(SpriteModel other){
        return this.getBounds().intersects(other.getBounds());
    }
}
