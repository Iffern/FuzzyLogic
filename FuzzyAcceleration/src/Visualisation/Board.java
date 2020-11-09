package Visualisation;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel implements Runnable {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    private final int CAR_WIDTH = 100;
    private final int CAR_HEIGHT = 70;
    private final int NUM_OF_ITERATIONS = 100;
    private Thread animator;
    private Controller controller;

    private CarVisualisation redCar;
    private CarVisualisation yellowCar;

    public Board(Controller controller){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.controller = controller;
        this.redCar = new CarVisualisation(getRedCarPosition());
        this.yellowCar = new CarVisualisation(getYellowCarPosition());
        loadCarImages();
    }

    private int getRedCarPosition(){
        return WIDTH/2-controller.getDistanceBetweenCars()-CAR_WIDTH;
    }

    private int getYellowCarPosition(){
        return WIDTH/2+controller.getDistanceBetweenCars();
    }

    private void loadCarImages(){
        ImageIcon yellowCarIcon = new ImageIcon("resources/yellow_car.png");
        this.yellowCar.setImage(yellowCarIcon.getImage());
        ImageIcon redCarIcon = new ImageIcon("resources/red_car.png");
        this.redCar.setImage(redCarIcon.getImage());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCars(g);
    }

    private void drawCars(Graphics g) {
        g.drawImage(yellowCar.getImage(), yellowCar.x, yellowCar.Y-8, CAR_WIDTH+20,CAR_HEIGHT, this);
        g.drawImage(redCar.getImage(), redCar.x, redCar.Y, CAR_WIDTH,CAR_HEIGHT,this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    private void updateCarMovement() throws InterruptedException {
        /*int yellowCarPosition = getYellowCarPosition();
        int redCarPosition = getRedCarPosition();
        int redDistanceToDrive = redCarPosition - redCar.x;
        int yellowDistanceToDrive = yellowCarPosition - yellowCar.x;
        for(int i=0; i<NUM_OF_ITERATIONS;i++){
            redCar.x += redDistanceToDrive/NUM_OF_ITERATIONS;
            yellowCar.x += yellowDistanceToDrive/NUM_OF_ITERATIONS;
            Thread.sleep(10);
        }*/
        yellowCar.x = getYellowCarPosition();
        redCar.x = getRedCarPosition();
    }

    @Override
    public void run() {
        while(true){
            try {
                controller.iterate();
                updateCarMovement();
                repaint();
                Thread.sleep(1000);
            }catch (InterruptedException e){e.printStackTrace();}
        }
    }
}
