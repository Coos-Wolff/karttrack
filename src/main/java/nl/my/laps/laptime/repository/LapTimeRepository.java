package nl.my.laps.laptime.repository;

import nl.my.laps.laptime.model.LapTime;
import org.springframework.data.repository.CrudRepository;

public interface LapTimeRepository extends CrudRepository<LapTime, Long> {

}
