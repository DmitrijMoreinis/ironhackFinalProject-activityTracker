package com.activityproject.activitytracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cycling extends Activity {


    private float averageCadency;
    private float averagePower;


    @ManyToOne
    @JoinColumn(name = "name")
    private User user;



}
