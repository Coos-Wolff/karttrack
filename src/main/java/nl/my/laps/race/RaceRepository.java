package nl.my.laps.race;

import nl.my.laps.race.model.Race;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CoosW on 14/04/2017.
 */
public interface RaceRepository extends CrudRepository<Race, Long> {
}
