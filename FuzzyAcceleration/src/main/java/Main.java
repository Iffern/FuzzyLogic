import Controller.Controller;
import Visualisation.CarAnimation;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Controller controller = new Controller("fuzzy_acceleration.fcl");
        EventQueue.invokeLater(() -> {
            JFrame ex = new CarAnimation(controller);
            ex.setVisible(true);
        });
        /*Controller.Controller controller = new Controller.Controller("fuzzy_acceleration.fcl");
        while (true){
            controller.iterate();
            Thread.sleep(2000);
        }*/
    }

}
