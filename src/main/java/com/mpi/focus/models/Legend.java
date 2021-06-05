package com.mpi.focus.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Legend {
    public Legend() {
    }

    public Legend(String fakeName, String origin, String purpose) {
        this.fakeName = fakeName;
        this.origin = origin;
        this.purpose = purpose;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String specialist;

    private String fakeName;

    private String origin;

    private String purpose;



}
