package Model;

import java.util.concurrent.ThreadLocalRandom;

public class Weather {
    double currentRainfall;

    public Weather(){
        this.currentRainfall = 0.0;
    }

    public double getCurrentRainfall() {
        return currentRainfall;
    }

    public void setRandomWeather(){
        double randomDouble = ThreadLocalRandom.current().nextDouble();
        double rainfallChange;
        if(randomDouble<0.1) rainfallChange= 0.0;
        else if (randomDouble<0.35) rainfallChange = -ThreadLocalRandom.current().nextDouble(0.0,1.0);
        else rainfallChange = ThreadLocalRandom.current().nextDouble(0.0,1.0);
        currentRainfall +=rainfallChange;
        if(currentRainfall<0) currentRainfall=0.0;
    }
}
