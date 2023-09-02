package com.activityproject.activitytracker.dto;

import lombok.Data;

/**
 * Data transfer object for passing information for adding a role to a user
 */
@Data
public class RoleToUserDto {
    /**
     * The username of the user
     */
    private String username;
    /**
     * The name of the role
     */
    private String roleName;
}