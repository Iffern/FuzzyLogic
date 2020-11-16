package Visualisation;

import java.awt.*;

public class Tree {
    int x;
    int y;
    private Image image;

    public Tree(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
