package Controller;

import Model.AutomaticCar;
import Model.ControlledCar;
import Model.FuzzyController;
import Model.Weather;

public class Controller {
    ControlledCar controlledCar;
    AutomaticCar automaticCar;
    Weather weather;
    FuzzyController fuzzyController;
    int accelerationFlag=0;

    public Controller(String fuzzyFile){
        this.controlledCar = new ControlledCar(50,20);
        this.automaticCar = new AutomaticCar(50);
        this.weather = new Weather();
        this.fuzzyController = new FuzzyController(fuzzyFile);
    }

    public void iterate(){
        printStatus();
        double speedChange = fuzzyController.getSpeedChange(controlledCar.getSpeed(),
                controlledCar.getDistanceFromCarAhead(), weather.getCurrentRainfall());
        System.out.println(speedChange);
        controlledCar.changeSpeedAndDistance(speedChange,1,automaticCar.getSpeed(),automaticCar.getAcceleration());
        automaticCar.changeSpeed(1);
        if(accelerationFlag==0){
            automaticCar.setAcceleration();
            weather.setRandomWeather();
        }
        accelerationFlag = (accelerationFlag+1)%4;
        printStatus();
    }

    public int getDistanceBetweenCars(){
        return controlledCar.getDistanceFromCarAhead();
    }

    private void printStatus(){
        System.out.println("Controlled car speed: "+controlledCar.getSpeed()+" Automatic car speed: "+automaticCar.getSpeed()
        +" Distance between cars: "+controlledCar.getDistanceFromCarAhead()+" Rainfall: "+weather.getCurrentRainfall());
    }

    public double getControlledCarSpeedInMS() {
        return automaticCar.speedInMS();
    }
}
