package main.java.GraphicModels;

import javax.swing.*;
import java.net.URL;

public class Sea extends SpriteModel {
    public Sea(int x, int y) {
        super(x, y,0,0);

        URL url = getClass().getResource("/main/resources/sea.png");
        if (url != null) {
            this.setImage(new ImageIcon(url).getImage());
        }
    }
}
