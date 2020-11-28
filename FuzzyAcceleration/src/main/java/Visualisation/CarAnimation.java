package Visualisation;

import javax.swing.*;
import Controller.Controller;

public class CarAnimation extends JFrame {

    public CarAnimation(Controller controller){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        SideBoard sideBoard = new SideBoard(controller);
        container.add(new Board(controller, sideBoard));
        container.add(sideBoard);
        add(container);
        setResizable(false);
        pack();

        setTitle("Fuzzy Logic Car Speed Alteration Program");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
