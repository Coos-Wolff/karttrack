package nl.my.laps.race.service;

import nl.my.laps.race.interfaces.RaceInterface;
import nl.my.laps.race.model.Race;
import nl.my.laps.race.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("raceService")
public class RaceService implements RaceInterface {

    @Autowired
    private RaceRepository raceRepository;
    @Override
    public void saveRace(Race race) {
        raceRepository.save(race);
    }
}
