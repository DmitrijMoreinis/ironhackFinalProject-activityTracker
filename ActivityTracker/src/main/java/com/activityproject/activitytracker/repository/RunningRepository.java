package com.activityproject.activitytracker.repository;

import com.activityproject.activitytracker.model.Running;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RunningRepository extends JpaRepository<Running, UUID> {
}
