package com.activityproject.activitytracker.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class for representing a Role in the database
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    /**
     * The primary key for the Role table
     */
    @Id
    /**
     * The id is generated automatically by the database
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;



    /**
     * The name of the role
     */
    @Column
    private String name;

    public Role(String name) {
        this.name = name;
    }
}