package Visualisation;

import javax.swing.*;
import java.awt.*;

public class WeatherVisualisation {
    Image sunny, light_rain, moderate_rain, heavy_rain;

    public WeatherVisualisation(){
        sunny= new ImageIcon("resources/sun.png").getImage();
        light_rain = new ImageIcon("resources/light_rain.png").getImage();
    }
}
