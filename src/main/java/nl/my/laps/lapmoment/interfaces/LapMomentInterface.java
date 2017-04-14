package nl.my.laps.lapmoment.interfaces;

import nl.my.laps.lapmoment.model.LapMoment;

import java.util.List;
import java.util.Map;

public interface LapMomentInterface {
    LapMoment getFastestLapOfAllKarts(Integer amountLaps, Integer amountKarts);

    String getTotalRaceTime(Map<Integer, List<LapMoment>> mapKartsLapNumbersLapMoments);
}
