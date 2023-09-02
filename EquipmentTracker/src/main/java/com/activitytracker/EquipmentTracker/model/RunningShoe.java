package com.activitytracker.EquipmentTracker.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
public class RunningShoe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String brand;
    private String type;

    private int maxKm;

    @CreationTimestamp
    ZonedDateTime createdAt;

    ZonedDateTime usedSince;

    @ManyToMany(mappedBy = "shoeList")
    List<ShoeUser> listOfUsers;

    @OneToMany
    List<ShoeUser> actualUser;

}
