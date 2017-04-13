package nl.my.laps.kart.service;

import nl.my.laps.kart.interfaces.KartInterface;
import nl.my.laps.kart.model.Kart;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.models.*;
import nl.my.laps.simulatedlaptime.interfaces.SimulatedLapTimeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service("kartService")
public class KartService implements KartInterface{

    @Autowired
    private SimulatedLapTimeInterface simulatedLapTimeInterface;

    // TODO get rid of System.out.println()
    public LapMoment getFastestLapOfAllKarts() {
        Map<Integer, List<LapMoment>> mapFastestLapMoments = getAllKartsWithLapNumbersAndLapTimes();
        for (List<LapMoment> lapMoments: mapFastestLapMoments.values())
            for (LapMoment lapMoment: lapMoments) {
                System.out.println("Kart Number: " + lapMoment.getKart());
                System.out.println("Lap Number: " + lapMoment.getLapNumber());
                System.out.println("Lap Time: " + lapMoment.getTime());
                System.out.println("\n");

            }
        Map<Integer, List<LapMoment>> mapFastestLapAndLapNumberAllKarts = getFastestLapsAndLapNumberOfAllKarts(mapFastestLapMoments);
        LapMoment fastestLap = getFastestLapMoment(mapFastestLapAndLapNumberAllKarts);
        System.out.println("Fastest lap: " + fastestLap.getTime().toString() + "\nLap number: " + fastestLap.getLapNumber() + "\nKart: " + fastestLap.getKart());
        return fastestLap;
    }

    private Map<Integer, List<LapMoment>> getAllKartsWithLapNumbersAndLapTimes() {
        Set<Kart> karts = simulatedLapTimeInterface.getLapTimesPerKart(5, 5);
        Map<Integer, List<LapMoment>> mapOfKartsWithLapTimes = new HashMap<>();
        for (Kart kart: karts) {
            List<LapMoment> lapMoments = new LinkedList<>();
            for(LapTime lapTime: kart.getLapTimes()) {
                LapMoment lapMoment = new LapMoment();
                lapMoment.setKart(lapTime.getKart().getKartNumber());
                lapMoment.setLapNumber(lapTime.getLabNumber());
                lapMoment.setTime(lapTime.getTimeLap());
                lapMoments.add(lapMoment);
            }
            mapOfKartsWithLapTimes.put(kart.getKartNumber(), lapMoments);
        }
        return mapOfKartsWithLapTimes;
    }

    private Map<Integer, List<LapMoment>> getFastestLapsAndLapNumberOfAllKarts(Map<Integer, List<LapMoment>> mapAllOfKarts) {
        Map<Integer, List<LapMoment>> mapKartsLapNumberLapTimes = new HashMap<>();
        List<LapMoment> listOfFastestLapMoments;
        Map<Integer, List<LapMoment>> mapKartsWithLapNumberAndFastestLap = new HashMap<>();

        for (Map.Entry<Integer, List<LapMoment>> map : mapAllOfKarts.entrySet()) {
            mapKartsLapNumberLapTimes.put(map.getKey(), map.getValue());
            listOfFastestLapMoments = getListFastestLapMoments(mapKartsLapNumberLapTimes);
            mapKartsWithLapNumberAndFastestLap.put(map.getKey(), listOfFastestLapMoments);
        }
        return mapKartsWithLapNumberAndFastestLap;
    }

    private LapMoment getFastestLapMoment(Map<Integer, List<LapMoment>> fastestLap) {
        List<LapMoment> listOfLapMoments = getListFastestLapMoments(fastestLap);
        return Collections.min(listOfLapMoments, (list1, list2) -> {
            if (list1.getTime() < list2.getTime()) {
                return -1;
            }
            if (list1.getTime() > list2.getTime()) {
                return 1;
            }
            return 0;
        });
    }

    private List<LapMoment> getListFastestLapMoments(Map<Integer, List<LapMoment>> fastestLap){
        List<LapMoment> lapMoments = new LinkedList<>();
        for(Map.Entry<Integer, List<LapMoment>> moment : fastestLap.entrySet()){
            for(LapMoment entry : moment.getValue()) {
                LapMoment lapMoment = new LapMoment();
                lapMoment.setLapNumber(entry.getLapNumber());
                lapMoment.setTime(entry.getTime());
                lapMoment.setKart(moment.getKey());
                lapMoments.add(lapMoment);
            }
        }
        return lapMoments;
    }
}