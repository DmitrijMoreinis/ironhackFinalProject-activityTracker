package com.activityproject.activitytracker.repository;

import com.activityproject.activitytracker.model.Running;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.hibernate.sql.ast.Clause.WHERE;

@Repository
public interface RunningRepository extends JpaRepository<Running, UUID> {
    /**
     * returns the sum of runned kilometers since the shoe was used for a special user
     * @param user name of the user using the equipment
     * @param usedSince the date a shoe was started to use
     * @return
     */
    @Query(value= "SELECT SUM(distance) FROM Running WHERE user=user AND createdAt>usedSince", nativeQuery=true )
    int runnedKmSinceCreationShoe(String user, ZonedDateTime usedSince);
}
