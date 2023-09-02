package com.activityproject.activitytracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {

    private UUID user_id;

    private String username;

    private String name;

    private String password;

    private Date birthDate;

    private float weight;

    private Date createdAt;

    private Date updatedAt;

}
