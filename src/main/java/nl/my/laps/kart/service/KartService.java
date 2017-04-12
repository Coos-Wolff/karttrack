package nl.my.laps.kart.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.simulatedlaptime.service.SimulatedLapTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by CoosW on 11/04/2017.
 */
@Service("kartService")
public class KartService {

    @Autowired
    private SimulatedLapTimeService simulatedLapTimeService;

    public Map<Integer, Map<Integer, Double>> getFastestLapOfAllKarts() {
        return getMapOfKartsWithLapNumberOfAndFastestLap(getKartNumbersWithLapNumbersAndLapTimes());
    }

    private Map<Integer, Map<Integer, Double>> getKartNumbersWithLapNumbersAndLapTimes() {
        Set<Kart> karts = simulatedLapTimeService.getLapTimesPerKart(5, 5);
        Map<Integer, Map<Integer, Double>> mapOfKartsWithLapTimes = new HashMap<>();
        for (Kart kart: karts) {
            Map<Integer, Double> lapTimes = new HashMap<>();
            for(LapTime lapTime: kart.getLapTimes()) {
                lapTimes.put(lapTime.getLabNumber(), lapTime.getTimeLap());
            }
            mapOfKartsWithLapTimes.put(kart.getKartNumber(), lapTimes);
        }
        return mapOfKartsWithLapTimes;
    }

    private Map<Integer, Map<Integer, Double>> getMapOfKartsWithLapNumberOfAndFastestLap(Map<Integer, Map<Integer, Double>> mapOfKartNumbersWithAMapOfLapNumbersAndLapTimes) {
        Map<Integer, Map<Integer, Double>> mapKartsLapNumberLapTimes = new HashMap<>();
        Map<Integer, Double> mapOfKartsWithLapNumberAndFastestLap = new HashMap<>();
        Map<Integer, Map<Integer, Double>> mapKartsWithLapNumberAndFastestLap = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Double>> map : mapOfKartNumbersWithAMapOfLapNumbersAndLapTimes.entrySet
                ()) {
            mapKartsLapNumberLapTimes.put(map.getKey(), map.getValue());
            for (Map<Integer, Double> lapTimesInMap : mapKartsLapNumberLapTimes.values()) {
                Map<Integer, Double> mapLapNumberAndFastestLap = new HashMap<>();

                Integer key = getKeyOfLapTimesInMap(lapTimesInMap);
                Double value = getValueOfLapTimesInMap(lapTimesInMap);
                mapLapNumberAndFastestLap.put(key, value);
                mapOfKartsWithLapNumberAndFastestLap = mapLapNumberAndFastestLap;
            }
            mapKartsWithLapNumberAndFastestLap.put(map.getKey(), mapOfKartsWithLapNumberAndFastestLap);
        }
        return mapKartsWithLapNumberAndFastestLap;
    }


    private Integer getKeyOfLapTimesInMap(Map<Integer, Double> lapTimesInMap) {
        return Collections.min(lapTimesInMap.entrySet(), new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue().intValue() - o2.getValue().intValue();
            }
        })
        .getKey();
    }

    private Double getValueOfLapTimesInMap(Map<Integer, Double> lapTimesInMap) {
        return Collections.min(lapTimesInMap.entrySet(), new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue().intValue() - o2.getValue().intValue();
            }
        })
        .getValue();
    }
}