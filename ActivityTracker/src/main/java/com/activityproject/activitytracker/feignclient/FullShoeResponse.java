package com.activityproject.activitytracker.feignclient;


import lombok.Data;

@Data
public class FullShoeResponse {
    private String brand;
    private String type;

    private String shoeUser;

    private int runnedKm;

    private int maxKm;

    private int usedSince;


}
