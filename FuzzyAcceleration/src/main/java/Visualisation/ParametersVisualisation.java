package Visualisation;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ParametersVisualisation {
    private Font font;

    public ParametersVisualisation(){
        font = new Font("Courier New", Font.ITALIC, 12);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/digital-7.ttf"));
        }catch (FontFormatException | IOException e){e.printStackTrace();}
        font = font.deriveFont(Font.PLAIN,28);
    }

    public void drawSpeed(Graphics g, ImageObserver observer, int redCarSpeedKMH, int yellowCarSpeedKMH) {
        g.setColor(Color.BLACK);
        g.fillRect(150, 100, 100,50);
        g.fillRect(750, 100, 100,50);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(redCarSpeedKMH+" KM/H", 155,135 );
        g.setColor(Color.YELLOW);
        g.drawString(yellowCarSpeedKMH+" KM/H", 755,135 );
        g.setFont(font.deriveFont(Font.PLAIN,20));
        g.setColor(Color.RED);
        g.drawString("Red car speed", 143,95 );
        g.setColor(Color.YELLOW);
        g.drawString("Yellow car speed", 730,95 );
    }

    public void drawDistance(Graphics g, ImageObserver observer, int distanceBetweenCars) {
        g.setColor(Color.BLACK);
        g.fillRect(450, 50, 100,50);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(distanceBetweenCars+" m", 455,85 );
        g.setFont(font.deriveFont(Font.PLAIN,20));
        g.drawString("Distance between cars", 410,45 );
    }
}
