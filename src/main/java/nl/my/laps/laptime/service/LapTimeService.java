package nl.my.laps.laptime.service;

import nl.my.laps.laptime.interfaces.LapTimeInterface;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.laptime.repository.LapTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("LapTimeService")
public class LapTimeService implements LapTimeInterface{

    @Autowired
    private LapTimeRepository lapTimeRepository;

    @Override
    public void saveLapTime(Set<LapTime> lapTime) {
        lapTimeRepository.save(lapTime);
    }
}
