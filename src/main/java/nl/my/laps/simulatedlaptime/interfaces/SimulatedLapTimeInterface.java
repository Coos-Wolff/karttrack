package nl.my.laps.simulatedlaptime.interfaces;

import nl.my.laps.kart.model.Kart;

import java.util.Set;

public interface SimulatedLapTimeInterface {
    Set<Kart> getLapTimesPerKart(Integer amountLaps, Integer amountKarts);
}
