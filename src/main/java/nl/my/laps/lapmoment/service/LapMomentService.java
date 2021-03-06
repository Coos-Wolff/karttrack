package nl.my.laps.lapmoment.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.lapmoment.interfaces.LapMomentInterface;
import nl.my.laps.lapmoment.model.LapMoment;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.simulatedlaptime.interfaces.SimulatedLapTimeInterface;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service("lapMomentService")
public class LapMomentService implements LapMomentInterface {

    @Autowired
    private SimulatedLapTimeInterface simulatedLapTimeInterFace;

    @Override
    public LapMoment getFastestLapOfAllKarts(Integer amountLaps, Integer amountKarts) {
        Map<Integer, List<LapMoment>> mapKartsLapNumbersLapMoments = getKartsLapNumbersLapMoments(amountLaps, amountKarts);
        Map<Integer, List<LapMoment>> mapFastestLapAndLapNumberAllKarts = getFastestLapsAndLapNumberOfAllKarts(mapKartsLapNumbersLapMoments);
        String totalRaceTime = getTotalRaceTime(mapKartsLapNumbersLapMoments);
        LapMoment lapMoment = getFastestLapMoment(mapFastestLapAndLapNumberAllKarts);
        lapMoment.setTotalRaceTime(totalRaceTime);
        return lapMoment;
    }

    private Map<Integer, List<LapMoment>> getKartsLapNumbersLapMoments(Integer amountLaps, Integer amountKarts) {
        Set<Kart> karts = simulatedLapTimeInterFace.getLapTimesPerKart(amountLaps, amountKarts);
        Map<Integer, List<LapMoment>> mapOfKartsWithLapTimes = new HashMap<>();
        for (Kart kart : karts) {
            List<LapMoment> lapMoments = new LinkedList<>();
            for (LapTime lapTime : kart.getLapTimes()) {
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

    private List<LapMoment> getListFastestLapMoments(Map<Integer, List<LapMoment>> fastestLap) {
        List<LapMoment> lapMoments = new LinkedList<>();
        for (Map.Entry<Integer, List<LapMoment>> moment : fastestLap.entrySet()) {
            for (LapMoment entry : moment.getValue()) {
                String lapMomentFormat = convertDoubleToTimeFormat(entry.getLapMoment());
                LapMoment lapMoment = new LapMoment();
                lapMoment.setLapNumber(entry.getLapNumber());
                lapMoment.setLapMoment(entry.getLapMoment());
                lapMoment.setKart(moment.getKey());
                lapMoment.setLapMomentFormat(lapMomentFormat);
                lapMoments.add(lapMoment);
            }
        }
        return lapMoments;
    }

    private List<Double> getTotalRaceTimeOfAllKarts(Map<Integer, List<LapMoment>> allKartsLapTimesLapNumbers) {
        List<Double> lapTimes = new LinkedList<>();
        for (List<LapMoment> lapMoments : allKartsLapTimesLapNumbers.values()) {
            Double sum = 0.0;
            for (LapMoment lapMoment : lapMoments) {
                sum += lapMoment.getLapMoment();
            }
            lapTimes.add(sum);
        }
        return lapTimes;
    }

    @Override
    public String getTotalRaceTime(Map<Integer, List<LapMoment>> mapKartsLapNumbersLapMoments) {
        List<Double> totalLapTimes = getTotalRaceTimeOfAllKarts(mapKartsLapNumbersLapMoments);
        Double totalRaceTimeInSeconds = Collections.max(totalLapTimes);
        return convertDoubleToTimeFormat(totalRaceTimeInSeconds);
    }

    private String convertDoubleToTimeFormat(Double seconds) {
        DecimalFormat format = new DecimalFormat("0.000");
        Double formatted = new Double(format.format(seconds));
        String secondsToMillisString = String.valueOf(formatted);
        String removeDot = secondsToMillisString.replace(".", "");
        Long parsedMilliseconds = Long.parseLong(removeDot);
        return DurationFormatUtils.formatDuration(parsedMilliseconds, "HH:mm:ss.SSS");
    }
}
