package nl.my.laps.kart.repository;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.race.model.Race;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by CoosW on 12/04/2017.
 */
public interface KartRepository extends CrudRepository<Kart, Long> {
    List<Kart> findAllByRace(Race race);
    // TODO
//    kart findByKartNumber(Integer kartNumber);
}
