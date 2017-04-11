package nl.my.laps.simulatedlaptime.service;

import nl.my.laps.simulatedlaptime.interfaces.SimulatedLapTimeInterface;

import java.sql.Time;
import java.util.Random;

public class SimulatedLapTimeService  implements SimulatedLapTimeInterface {

    @Override
    public Double getSimulatedLapTime() {
        Random random = new Random();
        return random.nextDouble() * 1.0 + (60.0 - 50.0);
    }
}
