package nl.my.laps.race.controller;

import nl.my.laps.kart.service.KartService;
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
    private KartService kartService;

    @Deprecated
    @GetMapping("/race")
    public ModelAndView test() {

        // TODO -> Map in map, omzetten naar entiteiten(modellen)
        Map<Integer, Map<Integer, Double>> tests = kartService.getFastestLapOfAllKarts();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("race");
        modelAndView.addObject("lapTimes", tests);
        return modelAndView;
    }
}
