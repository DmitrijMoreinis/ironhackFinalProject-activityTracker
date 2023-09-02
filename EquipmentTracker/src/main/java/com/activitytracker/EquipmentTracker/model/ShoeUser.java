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
    private String name;
    @ManyToMany
    List<RunningShoe> shoeList;

    @ManyToOne
    RunningShoe actualShoe;

}
