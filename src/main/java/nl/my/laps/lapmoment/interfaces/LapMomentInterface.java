package nl.my.laps.lapmoment.interfaces;

import nl.my.laps.lapmoment.model.LapMoment;

/**
 * Created by CoosW on 13/04/2017.
 */
public interface LapMomentInterface {
    LapMoment getFastestLapOfAllKarts();
    String getTotalRaceTime();
    String convertDoubleToTime(Double lapMoment);
}
