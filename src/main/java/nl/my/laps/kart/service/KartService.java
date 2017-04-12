package nl.my.laps.kart.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.models.*;
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
        Map<Integer, Map<Integer, Double>> mapAllOfKarts = getAllKartsWithLapNumbersAndLapTimes();
        Map<Integer, Map<Integer, Double>> mapFastestLapAndLapNumberAllKarts = getLapNumberAndFastestLapOfAllKarts(mapAllOfKarts);
        System.out.println(mapFastestLapAndLapNumberAllKarts);

        LapMoment fastestLap = getFastestLapMoment(mapFastestLapAndLapNumberAllKarts);
        System.out.println("Fastest lap: " + fastestLap.getTime().toString() + "Lap number: " + fastestLap.getLapNumber() + " Kart: " + fastestLap.getKart());

        return  mapFastestLapAndLapNumberAllKarts;
    }

    private LapMoment getFastestLapMoment(Map<Integer, Map<Integer, Double>> fastestLap) {
        List<LapMoment> listOfLapMoments = getFirstChildMapFromMap(fastestLap);
        return Collections.min(listOfLapMoments, (p1, p2) -> {
            if (p1.getTime() < p2.getTime()) {
                return -1;
            }
            if (p1.getTime() > p2.getTime()) {
                return 1;
            }
            return 0;
        });
    }

    private List<LapMoment> getFirstChildMapFromMap(Map<Integer, Map<Integer, Double>> fastestLap){
        List<LapMoment> lapMoments = new LinkedList<>();
        for(Map.Entry<Integer, Map<Integer, Double>> moment : fastestLap.entrySet()){
            for(Map.Entry<Integer, Double> entry : moment.getValue().entrySet()) {
                LapMoment lapMoment = new LapMoment();
                lapMoment.setLapNumber(entry.getKey());
                lapMoment.setTime(entry.getValue());
                lapMoment.setKart(moment.getKey());
                lapMoments.add(lapMoment);
                // forces to continue after the first entry.
                continue;
            }
        }
        return lapMoments;
    }

    private Map<Integer, Map<Integer, Double>> getAllKartsWithLapNumbersAndLapTimes() {
        Set<Kart> karts = simulatedLapTimeService.getLapTimesPerKart(5, 5);
        Map<Integer, Map<Integer, Double>> mapOfKartsWithLapTimes = new HashMap<>();
        for (Kart kart: karts) {
            Map<Integer, Double> lapTimes = new HashMap<>();
            for(LapTime lapTime: kart.getLapTimes()) {
                lapTimes.put(lapTime.getLabNumber(), lapTime.getTimeLap());
            }
            mapOfKartsWithLapTimes.put(kart.getKartNumber(), lapTimes);
        }
        System.out.println("Map Of Karts With LapTime:" + mapOfKartsWithLapTimes);
        return mapOfKartsWithLapTimes;
    }

    private Map<Integer, Map<Integer, Double>> getLapNumberAndFastestLapOfAllKarts(Map<Integer, Map<Integer, Double>> mapAllOfKarts) {
        Map<Integer, Map<Integer, Double>> mapKartsLapNumberLapTimes = new HashMap<>();
        Map<Integer, Double> mapOfKartsWithLapNumberAndFastestLap;
        Map<Integer, Map<Integer, Double>> mapKartsWithLapNumberAndFastestLap = new HashMap<>();

        for (Map.Entry<Integer, Map<Integer, Double>> map : mapAllOfKarts.entrySet()) {
            mapKartsLapNumberLapTimes.put(map.getKey(), map.getValue());
            mapOfKartsWithLapNumberAndFastestLap = getKeyValueOfSmallestValueInMap(mapKartsLapNumberLapTimes);
            mapKartsWithLapNumberAndFastestLap.put(map.getKey(), mapOfKartsWithLapNumberAndFastestLap);
        }
        System.out.println("Map Karts With LapNumbers And Fastest Lap" + mapKartsWithLapNumberAndFastestLap);
        return mapKartsWithLapNumberAndFastestLap;
    }

    private Map<Integer, Double> getKeyValueOfSmallestValueInMap(Map<Integer, Map<Integer, Double>> map) {
        Map<Integer, Double> mapOfKartsWithLapNumberAndFastestLap = new HashMap<>();
        for (Map<Integer, Double> lapTimesInMap : map.values()) {
            Map<Integer, Double> mapLapNumberAndFastestLap = new HashMap<>();
            Integer key = getKeyOfMap(lapTimesInMap);
            Double value = getValueOfMap(lapTimesInMap);
            mapLapNumberAndFastestLap.put(key, value);
            mapOfKartsWithLapNumberAndFastestLap = mapLapNumberAndFastestLap;
        }
        System.out.println("Karts with lapnumber and fastest lap" + mapOfKartsWithLapNumberAndFastestLap);
        return mapOfKartsWithLapNumberAndFastestLap;
    }


    private Integer getKeyOfMap(Map<Integer, Double> map) {
        return Collections.min(map.entrySet(), new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue().intValue() - o2.getValue().intValue();
            }
        })
        .getKey();
    }

    private Double getValueOfMap(Map<Integer, Double> map) {
        return Collections.min(map.entrySet(), new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue().intValue() - o2.getValue().intValue();
            }
        })
        .getValue();
    }
}