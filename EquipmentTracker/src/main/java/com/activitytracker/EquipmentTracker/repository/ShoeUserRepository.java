package com.activitytracker.EquipmentTracker.repository;

import com.activitytracker.EquipmentTracker.model.RunningShoe;
import com.activitytracker.EquipmentTracker.model.ShoeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoeUserRepository extends JpaRepository<ShoeUser,Integer> {


     Optional<RunningShoe> findByName(String name);
}
