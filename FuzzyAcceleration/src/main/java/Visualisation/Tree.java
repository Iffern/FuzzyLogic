package Visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Tree {
    ArrayList<Integer> xes = new ArrayList<>();
    ArrayList<Integer> yes = new ArrayList<>();
    private ArrayList<Image> images = new ArrayList<>();
    int treeWidth = 150;
    int treeHeight = 250;

    public Tree(){
        ImageIcon tree1 = new ImageIcon("resources/tree1.png");
        ImageIcon tree2 = new ImageIcon("resources/tree2.png");
        ImageIcon tree3 = new ImageIcon("resources/tree3.png");
        images.add(tree1.getImage());
        images.add(tree2.getImage());
        images.add(tree3.getImage());
        images.add(tree2.getImage());
        images.add(tree1.getImage());
    }

    public void addTree(int x, int y){
        this.xes.add(x);
        this.yes.add(y);
    }

    public void drawTrees(Graphics g, ImageObserver observer){
        g.drawImage(images.get(0), xes.get(0), yes.get(0),treeWidth,treeHeight, observer);
        g.drawImage(images.get(1), xes.get(1), yes.get(1),treeWidth,treeHeight, observer);
        g.drawImage(images.get(2), xes.get(2), yes.get(2),treeWidth,treeHeight, observer);
        g.drawImage(images.get(3), xes.get(3), yes.get(3),treeWidth,treeHeight, observer);
        g.drawImage(images.get(4), xes.get(4), yes.get(4),treeWidth,treeHeight, observer);
    }

    public void moveTrees(double treeInterval, int width){
        for(int i=0; i< xes.size(); i++){
            int position = xes.get(i);
            position = (int) Math.floor(position-treeInterval*2);
            if(position<-150) position = width;
            xes.set(i,position);
        }
    }
}
