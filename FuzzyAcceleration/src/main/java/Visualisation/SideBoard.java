package Visualisation;

import Controller.Controller;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

import javax.swing.*;
import java.awt.*;

public class SideBoard extends JPanel {
    private final int HEIGHT = 600;
    private final int WIDTH = 350;
    private Controller controller;

    public SideBoard(Controller controller){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.controller = controller;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        JFuzzyChart.get().draw((Graphics2D) g,new Rectangle(0,0,WIDTH,HEIGHT/4),
                controller.fuzzyController.fis.getVariable("current_speed"));
        JFuzzyChart.get().draw((Graphics2D) g,new Rectangle(0,HEIGHT/4,WIDTH,HEIGHT/4),
                controller.fuzzyController.fis.getVariable("distance_from_vehicle"));
        JFuzzyChart.get().draw((Graphics2D) g,new Rectangle(0,HEIGHT/2,WIDTH,HEIGHT/4),
                controller.fuzzyController.fis.getVariable("rainfall_intensity"));
        JFuzzyChart.get().draw((Graphics2D) g,new Rectangle(0,3*HEIGHT/4,WIDTH,HEIGHT/4),
                controller.fuzzyController.fis.getVariable("speed_change"));
    }
}
