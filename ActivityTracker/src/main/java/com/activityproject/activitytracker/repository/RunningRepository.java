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

    @Query(value= "SELECT SUM(distance) FROM Running WHERE user=user AND createdAt>usedSince", nativeQuery=true )
    int runnedKmSinceCreationShoe(String user, ZonedDateTime usedSince);
}
