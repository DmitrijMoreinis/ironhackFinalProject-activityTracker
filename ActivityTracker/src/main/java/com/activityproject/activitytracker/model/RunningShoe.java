package com.activityproject.activitytracker.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;


@Data
public class RunningShoe {
    private String brand;
    private String type;

    private int maxKm;

    private String user;
    private ZonedDateTime usedSince;
}
