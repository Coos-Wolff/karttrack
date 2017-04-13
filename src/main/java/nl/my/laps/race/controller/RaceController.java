package nl.my.laps.race.controller;

import nl.my.laps.lapmoment.interfaces.LapMomentInterface;
import nl.my.laps.lapmoment.model.LapMoment;
import nl.my.laps.race.model.RaceInputForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan
public class RaceController {

    @Autowired
    private LapMomentInterface lapMomentInterface;

    @GetMapping("/start")
    public ModelAndView race(RaceInputForm raceInputForm, ModelAndView modelAndView) {
        modelAndView.setViewName("start");
        modelAndView.addObject("raceInputForm", raceInputForm);
        return modelAndView;
    }

    @PostMapping("/race")
    public ModelAndView start(RaceInputForm raceInputForm) {
        LapMoment fastestLapOfAllKarts = lapMomentInterface.getFastestLapOfAllKarts(raceInputForm.getAmountLaps(), raceInputForm.getAmountKarts());
        String totalRaceTime = lapMomentInterface.getTotalRaceTime(raceInputForm.getAmountLaps(), raceInputForm.getAmountKarts());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("kart", fastestLapOfAllKarts.getKart());
        modelAndView.addObject("lap", fastestLapOfAllKarts.getLapNumber());
        modelAndView.addObject("time",fastestLapOfAllKarts.getLapMomentFormat());
        modelAndView.addObject("totalRaceTime", totalRaceTime);
        return modelAndView;
    }
}
