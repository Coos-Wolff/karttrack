package nl.my.laps.laptime.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.lapmoment.interfaces.LapMomentInterface;
import nl.my.laps.lapmoment.model.LapMoment;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.laptime.repository.LapTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("lapTimeService")
public class LapTimeService {

    @Autowired
    private LapTimeRepository lapTimeRepository;

    @Autowired
    private LapMomentInterface lapMomentInterface;

//    public Set<LapTime> saveLapTime(List<LapMoment> lapMoments) {
//        Set<LapTime> lapTimes = new HashSet<>();
//        for (LapMoment lapMoment: lapMoments) {
//            LapTime lapTime = new LapTime();
//            lapTime.setLabNumber(lapMoment.getLapNumber());
//            lapTime.setTimeLap(lapMoment.getLapMoment());
//            lapTime.setTotalRaceTime(lapMomentInterface.getTotalRaceTime());
//            lapTimes.add(lapTime);
//            lapTimeRepository.save(lapTime);
//        }
//        return lapTimes;
//    }
}
