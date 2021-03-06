package com.mpi.focus.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Legend {
    public Legend() {
    }

    public Legend(User specialist, String fakeName, String story, String purpose) {
        this.specialist = specialist;
        this.fakeName = fakeName;
        this.story = story;
        this.purpose = purpose;
    }

    public Legend(User specialist, String fakeName, String story, String purpose, String status) {
        this.specialist = specialist;
        this.fakeName = fakeName;
        this.story = story;
        this.purpose = purpose;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legend_id", updatable = false)
    private Long legendID;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User specialist;

    @Column(name = "fake_name")
    private String fakeName;

    private String story;

    private String purpose;

    private String status;


}