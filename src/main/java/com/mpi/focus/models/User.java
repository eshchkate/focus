package com.mpi.focus.models;

import com.mpi.focus.domain.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data

@Entity
@Table(name = "use")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
