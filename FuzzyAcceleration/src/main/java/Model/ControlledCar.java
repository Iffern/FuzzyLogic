package Model;

public class ControlledCar extends Car{
    int distanceFromCarAhead;
    int maximumSpeed = 150;

    public ControlledCar(int speed, int distanceFromCarAhead) {
        super(speed);
        this.distanceFromCarAhead = distanceFromCarAhead;
    }

    public int getDistanceFromCarAhead() {
        return distanceFromCarAhead;
    }

    public void changeSpeedAndDistance(double accelerationInMS2, int seconds, int carAheadSpeed, double carAheadAccelerationInMS2) {
        System.out.println(accelerationInMS2+" "+speed+" "+carAheadAccelerationInMS2+" "+carAheadSpeed+" "+distanceFromCarAhead);
        speed += accelerationInKMH2(accelerationInMS2)*seconds/3600;
        if(this.speed>0){
            distanceFromCarAhead += (carAheadSpeed - this.speed)*1000*seconds/3600 +
                    (carAheadAccelerationInMS2-accelerationInMS2)*Math.pow(seconds,2)/2;
        }
        if(speed>maximumSpeed) speed=maximumSpeed;
        if(speed<=0) speed=0;
    }
}
