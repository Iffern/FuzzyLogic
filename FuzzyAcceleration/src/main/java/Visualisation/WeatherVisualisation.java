package Visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class WeatherVisualisation {
    Image sunny, light_rain, moderate_rain, heavy_rain;

    public WeatherVisualisation(){
        sunny= new ImageIcon("resources/sun.png").getImage();
        light_rain = new ImageIcon("resources/light_rain.png").getImage();
        moderate_rain = new ImageIcon("resources/moderate_rain.png").getImage();
        heavy_rain = new ImageIcon("resources/storm.png").getImage();
    }

    public void drawWeather(Graphics g, ImageObserver observer, double rainfall){
        Image currentImage;
        if(rainfall==0.0) currentImage=sunny;
        else if(rainfall<2.5) currentImage=light_rain;
        else if (rainfall<6.0) currentImage=moderate_rain;
        else currentImage= heavy_rain;
        g.drawImage(currentImage,230, 200,100,100,observer);
    }
}
