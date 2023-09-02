package com.activitytracker.EquipmentTracker.repository;

import com.activitytracker.EquipmentTracker.model.RunningShoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RunningShoeRepository extends JpaRepository<RunningShoe, Integer> {
}
