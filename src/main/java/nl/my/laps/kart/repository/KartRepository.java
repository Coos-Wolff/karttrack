package nl.my.laps.kart.repository;

import nl.my.laps.kart.model.Kart;
import org.springframework.data.repository.CrudRepository;

public interface KartRepository extends CrudRepository<Kart, Long> {
}
