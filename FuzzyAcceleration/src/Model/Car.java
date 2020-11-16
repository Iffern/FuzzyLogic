package Model;

public abstract class Car {
    int speed;

    public Car(int speed){
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    double accelerationInKMH2(double accelerationInMS2){
        return accelerationInMS2*Math.pow(3600,2)/1000;
    }

    public double speedInMS(){ return speed*1000./3600;}
}
