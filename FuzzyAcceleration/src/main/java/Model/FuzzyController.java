package Model;


import net.sourceforge.jFuzzyLogic.FIS;

public class FuzzyController {
    public FIS fis;

    public FuzzyController(String fileName){
        this.fis = FIS.load(fileName,false);
    }

    public double getSpeedChange(int currentSpeed, int distanceFromVehicle, double rainfallIntensity){
        fis.setVariable("current_speed", currentSpeed);
        fis.setVariable("distance_from_vehicle", distanceFromVehicle);
        fis.setVariable("rainfall_intensity", rainfallIntensity);
        fis.evaluate();
        return fis.getVariable("speed_change").defuzzify();
    }
}
