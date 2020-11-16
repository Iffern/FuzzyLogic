package Visualisation;

public class Iterator extends Thread{
    Board board;

    Iterator(Board board){
        this.board=board;
    }

    @Override
    public void run() {
        while(true) {
            try {
                board.iterate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
