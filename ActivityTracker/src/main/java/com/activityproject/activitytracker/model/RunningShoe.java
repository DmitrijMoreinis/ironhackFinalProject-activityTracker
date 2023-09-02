package com.activityproject.activitytracker.model;

import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

public class RunningShoe {
    private String brand;
    private String type;

    private int maxKm;
    ZonedDateTime createdAt;
}
