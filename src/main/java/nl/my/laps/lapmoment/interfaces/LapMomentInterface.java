package nl.my.laps.lapmoment.interfaces;

import nl.my.laps.lapmoment.model.LapMoment;

public interface LapMomentInterface {
    LapMoment getFastestLapOfAllKarts(Integer amountLaps, Integer amountKarts);
    String getTotalRaceTime(Integer amountLaps, Integer amountKarts);
}
