package nl.my.laps.race.controller;

import nl.my.laps.kart.model.Kart;
import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.simulatedlaptime.service.SimulatedLapTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by CoosW on 12/04/2017.
 */
@Controller
@ComponentScan
public class RaceController {

    @Autowired
    private SimulatedLapTimeService simulatedLapTimeService;

    @Deprecated
    @GetMapping("/race")
    public ModelAndView test() {
        Set<Kart> karts = simulatedLapTimeService.getLapTimesPerKart(5, 6);
        Map<Integer, Map<Integer, Double>> mapOfKartsWithLapTimes = new HashMap<>();
        for (Kart kart: karts) {
            Map<Integer, Double> lapTimes = new HashMap<>();
            for(LapTime lapTime: kart.getLapTimes()) {
                lapTimes.put(lapTime.getLabNumber(), lapTime.getTimeLap());
            }
            mapOfKartsWithLapTimes.put(kart.getKartNumber(), lapTimes);
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("race");
        modelAndView.addObject("karts", mapOfKartsWithLapTimes);
        return modelAndView;
    }
}
