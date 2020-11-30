package Model;

import java.util.concurrent.ThreadLocalRandom;

public class AutomaticCar extends Car{
    double acceleration;
    int maximumSpeed = 140;
    public AutomaticCar(int speed) {
        super(speed);
        this.acceleration = 0.0;
    }

    public void setAcceleration() {
        this.acceleration = randomAcceleration();
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void changeSpeed(int seconds){
        /*if(this.speed<=10) {
            this.speed += (int) Math.abs(accelerationInKMH2(acceleration)) * seconds / 3600;
        }
        else{
            this.speed += (int) accelerationInKMH2(acceleration) * seconds / 3600;
        }*/
        if(this.speed>=maximumSpeed) this.speed=maximumSpeed;
        if(this.speed<=0){
            this.speed = 0;}
    }

    private double randomAcceleration(){
        /*double random = ThreadLocalRandom.current().nextDouble();
        double acceleration;
        if(random<0.5) acceleration = ThreadLocalRandom.current().nextDouble(0,3);
        else acceleration = ThreadLocalRandom.current().nextDouble(-3,0);
        if(speed<=0) acceleration = Math.abs(acceleration);
        return acceleration;*/
        return 0;
    }
}
