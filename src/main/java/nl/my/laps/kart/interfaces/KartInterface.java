package nl.my.laps.kart.interfaces;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.race.model.Race;

import java.util.Set;

public interface KartInterface {

    Set<Kart> getAllKartsByRace(Race race);
}
