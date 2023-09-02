package com.activityproject.activitytracker.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Entity
@Data
public class Running extends Activity{

    private int sprints;

   @ManyToOne
    @JoinColumn(name="name",nullable = false)
    private User user;

}
