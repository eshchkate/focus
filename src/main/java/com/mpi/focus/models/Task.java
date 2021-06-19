package com.mpi.focus.models;

import lombok.Data;
import org.aspectj.apache.bcel.classfile.Module;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
public class Task {


    public Task() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long taskID;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "time_stop")
    private LocalDateTime timeStop;

    private String description;

    private String place;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tasks", fetch = FetchType.EAGER)
    private List<User> users;
}
