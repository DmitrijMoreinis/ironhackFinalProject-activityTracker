package com.activityproject.activitytracker.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



@Data
@NoArgsConstructor
@Getter
@Setter
public abstract class ActivityDto {

    UUID id;

    ZonedDateTime DoneTimePoint;


   @NotNull
    float distance;

    Duration duration;

    float speed;

    float calories;

   @CreationTimestamp
    ZonedDateTime createdAt;
}
