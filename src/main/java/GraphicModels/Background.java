package main.java.GraphicModels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Background extends SpriteModel {
    public Background(int x, int y) {
        super(x, y,0,0);

        URL url = getClass().getResource("/main/resources/background.png");
        if (url != null) {
            this.setImage(new ImageIcon(url).getImage());
        }
    }
}