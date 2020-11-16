package Visualisation;

public class Animator extends Thread{
    Board board;

    Animator(Board board){
        this.board=board;
    }

    @Override
    public void run() {
        boolean isLastFrame;
        int iterator = 0;
        while(true) {
            double yellowInterval = (board.getYellowCarPosition() - board.yellowCar.x) / 10.;
            double redInterval = (board.getRedCarPosition() - board.redCar.x) / 10.;
            double treeInterval = board.controller.getControlledCarSpeedInMS()*0.1;
            iterator = (iterator+1)%10;
            isLastFrame = iterator == 9;
            try {
                board.updateCarMovement(yellowInterval, redInterval, treeInterval, isLastFrame);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
