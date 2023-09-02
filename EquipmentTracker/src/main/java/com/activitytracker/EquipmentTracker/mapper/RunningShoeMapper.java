package com.activitytracker.EquipmentTracker.mapper;

import com.activitytracker.EquipmentTracker.dto.RunningShoeDto;
import com.activitytracker.EquipmentTracker.model.RunningShoe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RunningShoeMapper {
        RunningShoeDto toDto(RunningShoe running);

        RunningShoe toEntity(RunningShoeDto dto);
    }

