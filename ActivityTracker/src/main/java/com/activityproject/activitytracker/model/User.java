package com.activityproject.activitytracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@RequiredArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;

    private String username;

    private String name;

    private String password;

    private Date birthDate;

    private float weight;

    private float overallDistanceCycling;

    private float overallDistanceRunning;

    private Date createdAt;

    private Date updatedAt;





    @OneToMany(mappedBy = "user")
    private List<Cycling> cyclingList;
    @OneToMany(mappedBy = "user")
    private List<Running> runningList;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public User(String name, String username, String password, Collection<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}



