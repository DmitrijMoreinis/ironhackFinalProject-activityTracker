package com.activityproject.activitytracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.*;
import java.util.UUID;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public abstract class Activity {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
      UUID id;

     ZonedDateTime DoneTimePoint;


      float distance;
      Duration duration;
      float speed;
      float calories;

     @CreationTimestamp
      ZonedDateTime createdAt;


}
