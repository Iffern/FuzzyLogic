package Visualisation;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Board extends JPanel {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    final static int CAR_WIDTH = 100;
    final static int CAR_HEIGHT = 70;
    private final int NUM_OF_ITERATIONS = 100;
    private Thread animator;
    Controller controller;
    private boolean isAnimated = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    CarVisualisation cars;
    Tree trees = new Tree();
    Road road = new Road(WIDTH);
    WeatherVisualisation weather = new WeatherVisualisation();

    public Board(Controller controller) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.controller = controller;
        this.cars = new CarVisualisation(getRedCarPosition(), getYellowCarPosition());
        int[] treesXes = {100, 290, 510, 690, 920};
        int treesY = 200;
        for(int i=0;i<treesXes.length;i++){
            trees.addTree(treesXes[i], treesY);
        }
        this.setBackground(new Color(128, 191, 255));
    }

    int getRedCarPosition() {
        return WIDTH / 2 - controller.getDistanceBetweenCars() - CAR_WIDTH;
    }

    int getYellowCarPosition() {
        return WIDTH / 2 + controller.getDistanceBetweenCars();
    }

    private void loadImages() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        weather.drawWeather(g, this, controller.getWeather().getCurrentRainfall());
        trees.drawTrees(g,this);
        road.paintRoad(g,this);
        Image grass = new ImageIcon("resources/grass.png").getImage();
        g.drawImage(grass,0,410,WIDTH,200, this);
        g.drawImage(grass,0,440,WIDTH,200, this);
        g.drawImage(grass,0,470,WIDTH,200, this);
        cars.drawCars(g, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Iterator iterator= new Iterator(this);
        Animator animator = new Animator(this);
        iterator.start();
        animator.start();
    }

    public void updateCarMovement(double yellowInterval, double redInterval, double treeInterval, boolean isLastFrame) throws InterruptedException{
        lock.lock();
        while(isAnimated){
            condition.await();
        }
        cars.moveCars(yellowInterval, redInterval);
        trees.moveTrees(treeInterval, WIDTH);
        road.moveRoad(treeInterval*2);
        repaint();
        if(isLastFrame) {
            isAnimated = true;
            condition.signalAll();
        }
        lock.unlock();
    }

    public void iterate() throws InterruptedException {
        controller.iterate();
        lock.lock();
        while(!isAnimated){
            condition.await();
        }
        isAnimated = false;
        condition.signalAll();
        lock.unlock();
    }
}
