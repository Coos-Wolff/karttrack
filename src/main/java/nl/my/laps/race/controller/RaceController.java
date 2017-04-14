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

    @GetMapping("/")
    public ModelAndView start(RaceInputForm raceInputForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("raceInputForm", raceInputForm);
        return modelAndView;
    }

    @PostMapping("/start")
    public ModelAndView race(RaceInputForm raceInputForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("race");
        modelAndView.addObject("lap", raceInputForm.getAmountLaps());
        modelAndView.addObject("kart", raceInputForm.getAmountLaps());
        return modelAndView;
    }

    @PostMapping("/finish")
    public ModelAndView show(RaceInputForm raceInputForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("finish");
        modelAndView.addObject("lap", raceInputForm.getAmountLaps());
        modelAndView.addObject("kart", raceInputForm.getAmountLaps());
        return modelAndView;
    }

    @PostMapping("/result")
    public ModelAndView result(RaceInputForm raceInputForm) {
        LapMoment fastestLapOfAllKarts = lapMomentInterface.getFastestLapOfAllKarts(raceInputForm.getAmountLaps(), raceInputForm.getAmountKarts());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("kart", fastestLapOfAllKarts.getKart());
        modelAndView.addObject("lap", fastestLapOfAllKarts.getLapNumber());
        modelAndView.addObject("time",fastestLapOfAllKarts.getLapMomentFormat());
        modelAndView.addObject("totalRaceTime", fastestLapOfAllKarts.getTotalRaceTime());
        return modelAndView;
    }
}
