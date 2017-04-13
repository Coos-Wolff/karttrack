package nl.my.laps.simulatedlaptime.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.race.RaceRepository;
import nl.my.laps.race.model.Race;
import nl.my.laps.simulatedlaptime.interfaces.SimulatedLapTimeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service("simulatedLapTimeService")
public class SimulatedLapTimeService implements SimulatedLapTimeInterface {

    @Autowired
    RaceRepository raceRepository;

    private Double getSimulatedLapTime() {
        Double lapTime = ThreadLocalRandom.current().nextDouble(40.0, 50.0);
        return Math.round(lapTime * 1000.0) / 1000.0;
    }

    public Set<Kart> getLapTimesPerKart(Integer amountLaps, Integer amountKarts) {
        Set<Kart> karts = new HashSet<>();
        Race race = new Race();
        for (int i = 0; i < amountKarts; i++) {
            Kart kart = new Kart();
            Set<LapTime> lapTimes = new HashSet<>();
            kart.setKartNumber(i + 1);
            kart.setLapTimes(lapTimes);
            kart.setRace(race);
            karts.add(kart);
            for (int j = 0; j < amountLaps; j++) {
                LapTime lapTime = new LapTime();
                lapTime.setLabNumber(j + 1);
                lapTime.setTimeLap(getSimulatedLapTime());
                lapTime.setKart(kart);
                lapTimes.add(lapTime);
            }
        }
        return karts;
    }
}
