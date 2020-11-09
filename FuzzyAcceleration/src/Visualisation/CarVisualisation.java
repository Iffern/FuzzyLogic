package Visualisation;

import java.awt.*;

public class CarVisualisation {
    private Image image;
    public int x;
    public final int Y = 400;

    public CarVisualisation(int x){
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

}
