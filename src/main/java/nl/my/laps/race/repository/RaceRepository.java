package nl.my.laps.race.repository;

import nl.my.laps.race.model.Race;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepository extends CrudRepository<Race, Long> {
}
