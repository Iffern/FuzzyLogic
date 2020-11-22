package Visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class CarVisualisation {
    private Image redImage;
    private Image yellowImage;
    public int redX, yellowX;
    public final int Y = 400;

    public CarVisualisation(int redX, int yellowX){
        ImageIcon yellowCarIcon = new ImageIcon("resources/yellow_car.png");
        this.yellowImage = yellowCarIcon.getImage();
        ImageIcon redCarIcon = new ImageIcon("resources/red_car.png");
        this.redImage = redCarIcon.getImage();
        this.redX=redX;
        this.yellowX=yellowX;
    }


    public void drawCars(Graphics g, ImageObserver observer) {
        g.drawImage(yellowImage, yellowX, Y - 8, Board.CAR_WIDTH + 20, Board.CAR_HEIGHT, observer);
        g.drawImage(redImage, redX, Y, Board.CAR_WIDTH, Board.CAR_HEIGHT, observer);
    }

    public void moveCars(double yellowInterval, double redInterval){
        yellowX += yellowInterval;
        redX += redInterval;
    }
}
