package nl.my.laps.laptime.interfaces;

import nl.my.laps.laptime.model.LapTime;

import java.util.Set;

public interface LapTimeInterface {

    void saveLapTime(Set<LapTime> lapTimes);
}
