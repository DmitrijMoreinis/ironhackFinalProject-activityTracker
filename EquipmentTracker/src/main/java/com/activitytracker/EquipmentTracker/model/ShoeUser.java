package com.activitytracker.EquipmentTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ShoeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Username;
    @ManyToMany
    List<RunningShoe> shoeList;

    @OneToMany
    @JoinTable(name = "actualShoe")
    List<ShoeUser> actualUsers;

}
