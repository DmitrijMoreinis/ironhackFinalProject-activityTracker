package com.activityproject.activitytracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Data
@NoArgsConstructor
public class CyclingDto extends ActivityDto{

    private float averageCadency;
    private float averagePower;

}