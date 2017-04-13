package nl.my.laps.lapmoment.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.lapmoment.interfaces.LapMomentInterface;
import nl.my.laps.lapmoment.model.LapMoment;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.simulatedlaptime.interfaces.SimulatedLapTimeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.*;

@Service("lapMomentService")
public class LapMomentService implements LapMomentInterface {

    @Autowired
    private SimulatedLapTimeInterface simulatedLapTimeInterface;

    // TODO get rid of System.out.println()
    @Override
    public LapMoment getFastestLapOfAllKarts() {
        Map<Integer, List<LapMoment>> mapFastestLapMoments = getAllKartsWithLapNumbersAndLapTimes();
        Map<Integer, List<LapMoment>> mapFastestLapAndLapNumberAllKarts = getFastestLapsAndLapNumberOfAllKarts(mapFastestLapMoments);
        return getFastestLapMoment(mapFastestLapAndLapNumberAllKarts);
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
                lapMoment.setLapMoment(lapTime.getTimeLap());
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
            if (list1.getLapMoment() < list2.getLapMoment()) {
                return -1;
            }
            if (list1.getLapMoment() > list2.getLapMoment()) {
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
                lapMoment.setLapMoment(entry.getLapMoment());
                lapMoment.setKart(moment.getKey());
                lapMoments.add(lapMoment);
            }
        }
        return lapMoments;
    }

    private List<Double> getTotalRaceTimeOfAllKarts() {
        Map<Integer, List<LapMoment>> allKartsLapTimesLapNumbers = getAllKartsWithLapNumbersAndLapTimes();
        List<Double> lapTimes = new LinkedList<>();
        for (List<LapMoment> lapMoments: allKartsLapTimesLapNumbers.values()) {
            Double sum = 0.0;
            for (LapMoment lapMoment: lapMoments) {
                sum += lapMoment.getLapMoment();
            }
            lapTimes.add(sum);
        }
        return lapTimes;
    }

    @Override
    public String convertDoubleToTime(Double lapMoment) {
        Date date = new Date((long)(lapMoment * 1000));
        return new SimpleDateFormat("mm:ss.SSS").format(date);
    }

    @Override
    public String getTotalRaceTime() {
        List<Double> totalLapTimes = getTotalRaceTimeOfAllKarts();
        System.out.println(totalLapTimes);
        Double totalRaceTimeInSeconds = Collections.max(totalLapTimes);
        Date date = new Date((long)(totalRaceTimeInSeconds * 1000));
        return new SimpleDateFormat("mm:ss.SSS").format(date);
    }
}
