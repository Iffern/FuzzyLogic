package Visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Road{
    int x1, x2, x3;
    Image image;
    int width;

    public Road(int width){
        this.x1=-width;
        this.x2=0;
        this.x3=width;
        this.width=width;
        image = new ImageIcon("resources/road.jpg").getImage();
    }

    public void paintRoad(Graphics g, ImageObserver observer){
        g.drawImage(image,x1,430,width,90,observer);
        g.drawImage(image,x2,430,width,90,observer);
        g.drawImage(image,x3,430,width,90,observer);
    }

    public void moveRoad(double interval){
        x1 = (int) Math.floor(x1-interval);
        if(x1<-width) x1 = width-1;
        x2 = (int) Math.floor(x2-interval);
        if(x2<-width) x2 = width-1;
        x3 = (int) Math.floor(x3-interval);
        if(x3<-width) x3 = width-1;
    }
}