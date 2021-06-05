package com.mpi.focus.controllers;


import com.mpi.focus.models.Legend;
import com.mpi.focus.repos.LegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LegendController {

    @Autowired
    private LegendRepository legendRepository;

    @GetMapping("/legend")
    public String legendMain(Model model) {
        Iterable<Legend> legends = legendRepository.findAll();
        model.addAttribute("legends", legends);
        return "legend-main";
    }

    @PostMapping("/legend")
    public String addLegend(@RequestParam String fakeName, @RequestParam String origin, @RequestParam String purpose, Model model) {
        Legend legend = new Legend(fakeName, origin, purpose);
        legendRepository.save(legend);

        Iterable<Legend> legends = legendRepository.findAll();
        model.addAttribute("legends", legends);
        return "legend-main";
    }
}
