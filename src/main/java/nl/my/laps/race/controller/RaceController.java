package nl.my.laps.race.controller;

import nl.my.laps.lapmoment.interfaces.LapMomentInterface;
import nl.my.laps.lapmoment.model.LapMoment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CoosW on 12/04/2017.
 */
@Controller
@ComponentScan
public class RaceController {

    @Autowired
    private LapMomentInterface lapMomentInterface;

    @Deprecated
    @GetMapping("/race")
    public ModelAndView race() {
        LapMoment fastestLapOfAllKarts = lapMomentInterface.getFastestLapOfAllKarts();
        String totalRaceTime = lapMomentInterface.getTotalRaceTime();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("race");
        modelAndView.addObject("kart", fastestLapOfAllKarts.getKart());
        modelAndView.addObject("lap", fastestLapOfAllKarts.getLapNumber());
        modelAndView.addObject("time", lapMomentInterface.convertDoubleToTime(fastestLapOfAllKarts.getLapMoment()));
        modelAndView.addObject("totalRaceTime", totalRaceTime);
        return modelAndView;
    }
}
