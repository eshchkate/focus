package com.mpi.focus.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Template {

    public Template() {
    }

    public Template(String templateName) {
        this.templateName = templateName;
    }

    public Template(String templateName, Plan plan) {
        this.templateName = templateName;
        this.plan = plan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateID;

    @ManyToOne (cascade = {
            CascadeType.MERGE,CascadeType.PERSIST,

    })
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "template_name")
    private String templateName;

    @OneToMany(cascade = {
           CascadeType.ALL
    }, mappedBy = "template", fetch = FetchType.EAGER)
    private List<Task> tasks;
}
