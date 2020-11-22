package Visualisation;

import javax.swing.*;
import Controller.Controller;

public class CarAnimation extends JFrame {

    public CarAnimation(Controller controller){
        add(new Board(controller));
        setResizable(false);
        pack();

        setTitle("Fuzzy Logic Car Speed Alteration Program");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
