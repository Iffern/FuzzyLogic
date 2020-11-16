package Visualisation;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Board extends JPanel {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    private final int CAR_WIDTH = 100;
    private final int CAR_HEIGHT = 70;
    private final int NUM_OF_ITERATIONS = 100;
    private Thread animator;
    Controller controller;
    private boolean isAnimated = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    CarVisualisation redCar;
    CarVisualisation yellowCar;
    ArrayList<Tree> trees = new ArrayList<>();

    public Board(Controller controller) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.controller = controller;
        this.redCar = new CarVisualisation(getRedCarPosition());
        this.yellowCar = new CarVisualisation(getYellowCarPosition());
        int[] treesXes = {100, 290, 510, 690, 920};
        int treesY = 200;
        for(int i=0;i<treesXes.length;i++){
            trees.add(new Tree(treesXes[i], treesY));
        }
        loadImages();
        this.setBackground(new Color(128, 191, 255));
    }

    int getRedCarPosition() {
        return WIDTH / 2 - controller.getDistanceBetweenCars() - CAR_WIDTH;
    }

    int getYellowCarPosition() {
        return WIDTH / 2 + controller.getDistanceBetweenCars();
    }

    private void loadImages() {
        ImageIcon yellowCarIcon = new ImageIcon("resources/yellow_car.png");
        this.yellowCar.setImage(yellowCarIcon.getImage());
        ImageIcon redCarIcon = new ImageIcon("resources/red_car.png");
        this.redCar.setImage(redCarIcon.getImage());
        ImageIcon tree1 = new ImageIcon("resources/tree1.png");
        trees.get(0).setImage(tree1.getImage());
        trees.get(4).setImage(tree1.getImage());
        ImageIcon tree2 = new ImageIcon("resources/tree2.png");
        trees.get(1).setImage(tree2.getImage());
        trees.get(3).setImage(tree2.getImage());
        ImageIcon tree3 = new ImageIcon("resources/tree3.png");
        trees.get(2).setImage(tree3.getImage());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTrees(g);
        drawCars(g);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Iterator iterator= new Iterator(this);
        Animator animator = new Animator(this);
        iterator.start();
        animator.start();
    }

    private void drawTrees(Graphics g) {
        int treeWidth = 150;
        int treeHeight = 250;
        g.drawImage(trees.get(0).getImage(),trees.get(0).x,trees.get(0).y,treeWidth,treeHeight, this);
        g.drawImage(trees.get(1).getImage(),trees.get(1).x,trees.get(1).y,treeWidth,treeHeight, this);
        g.drawImage(trees.get(2).getImage(),trees.get(2).x,trees.get(2).y,treeWidth,treeHeight, this);
        g.drawImage(trees.get(3).getImage(),trees.get(3).x,trees.get(3).y,treeWidth,treeHeight, this);
        g.drawImage(trees.get(4).getImage(),trees.get(4).x,trees.get(4).y,treeWidth,treeHeight, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawCars(Graphics g) {
        Image road = new ImageIcon("resources/road.jpg").getImage();
        g.drawImage(road,0,430,WIDTH,90, this);
        Image grass = new ImageIcon("resources/grass.png").getImage();
        g.drawImage(grass,0,410,WIDTH,200, this);
        g.drawImage(grass,0,440,WIDTH,200, this);
        g.drawImage(grass,0,470,WIDTH,200, this);
        g.drawImage(yellowCar.getImage(), yellowCar.x, yellowCar.Y - 8, CAR_WIDTH + 20, CAR_HEIGHT, this);
        g.drawImage(redCar.getImage(), redCar.x, redCar.Y, CAR_WIDTH, CAR_HEIGHT, this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void updateCarMovement(double yellowInterval, double redInterval, double treeInterval, boolean isLastFrame) throws InterruptedException{
        lock.lock();
        while(isAnimated){
            condition.await();
        }
        yellowCar.x += yellowInterval;
        redCar.x += redInterval;
        for(Tree tree:trees){
            tree.x = (int) Math.floor(tree.x-treeInterval*2);
            if(tree.x<-150) tree.x = WIDTH;
        }
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
