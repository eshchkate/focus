package com.mpi.focus.controllers;

import com.mpi.focus.models.Plan;
import com.mpi.focus.models.Template;
import com.mpi.focus.repos.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TemplateController {

    @Autowired
    TemplateRepository templateRepository;

    @GetMapping("/template")
    public String home(Model model) {
        List<Template> templates = templateRepository.findAll();
        model.addAttribute("templates", templates);
        return "template";
    }

    @GetMapping("/newtemplate")
    public String template(Model model) {
        List<Template> templates = templateRepository.findAll();
        model.addAttribute("plans", templates);
        return "createTemplate";
    }

    @GetMapping("/template/{template}")
    public String templateEdit(@PathVariable Template template,
                               Model model) {
        model.addAttribute("template", template);
        return "templateedit";
    }

    @PostMapping("/template/{template}")
    public String planEditForm(@PathVariable(value = "template") Long id) {
        Template template = templateRepository.getById(id);

        return "redirect:";
    }

}
