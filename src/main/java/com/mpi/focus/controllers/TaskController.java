package com.mpi.focus.controllers;

import com.mpi.focus.models.Task;
import com.mpi.focus.models.Template;
import com.mpi.focus.models.User;
import com.mpi.focus.repos.TaskRepository;
import com.mpi.focus.repos.TemplateRepository;
import com.mpi.focus.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/task")
    public String home(@AuthenticationPrincipal User specialist,
                       Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("specialist", specialist);
        return "task";
    }

    @GetMapping("/task/{task}")
    public String taskEdit(@PathVariable(value = "task") Long id,
                           Model model) {
        Task task = taskRepository.getById(id);

        //List<User> users = userRepository.findAll();
        //  User user = userRepository.getById(2L);
        model.addAttribute(task);
        //  model.addAttribute(users);
        return "taskedit";
    }

    @PostMapping("/task/{task}")
    public String taskEditForm(@PathVariable(value = "task") Long id,
                               @RequestParam String taskName,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStop,
                               @RequestParam String description,
                               @RequestParam String place,
                               @RequestParam String specialist
    ) {
        Task task = taskRepository.getById(id);
        User user = userRepository.findByUsername(specialist);
        task.setTaskName(taskName);
        task.setTimeStart(timeStart);
        task.setTimeStop(timeStop);
        task.setDescription(description);
        task.setPlace(place);
        task.setSpecialist(user);
        taskRepository.save(task);

        return "redirect:/task/{task}";
    }

    @GetMapping("/template/newtask/{template}")
    public String task(@PathVariable(value = "template") Long id,
                       Model model) {
        Template template = templateRepository.getById(id);
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("template", template);
        model.addAttribute("tasks", tasks);
        return "createTask";
    }

    @PostMapping("/template/newtask/{template}")
    public String taskCreate(@PathVariable(value = "template") Long id,
                             @RequestParam String taskName,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStop,
                             @RequestParam String description,
                             @RequestParam String place,
                             @RequestParam String specialist
    ) {
        Template template = templateRepository.getById(id);
        User user = userRepository.findByUsername(specialist);
        Long UserID = user.getUserID();
        Task task = new Task(taskName, timeStart, timeStop, description, place, template, user);
        taskRepository.save(task);
        return "redirect:/template/{template}";
    }
/*
    @PostMapping("task/delete/{task}")
    public String deleteTask(@PathVariable("task") long taskID, Model model) {
    //    Task task = taskRepository.getById(taskID);
       // Long templateID = task.getTemplate().getTemplateID();
        taskRepository.deleteById(taskID);
      //  taskRepository.delete(task);
        return "redirect:/plan";
    }*/

    @GetMapping("task/delete/{task}")
    public String deletTask(@PathVariable("task") long taskID, Model model) {
        Task task = taskRepository.getById(taskID);
        // Long templateID = task.getTemplate().getTemplateID();
        taskRepository.deleteById(taskID);
        //  taskRepository.delete(task);
        return "redirect:/plan";
    }

}
