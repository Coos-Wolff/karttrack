package nl.my.laps.kart.service;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.kart.repository.KartRepository;
import nl.my.laps.laptime.service.LapTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("kartService")
public class KartService {

    @Autowired
    private LapTimeService lapTimeService;

    @Autowired
    private KartRepository kartRepository;

    public void saveKart(Kart kart) {
        kartRepository.save(kart);
    }
}
