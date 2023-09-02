package com.activityproject.activitytracker.mapper;

import com.activityproject.activitytracker.dto.ActivityDto;
import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.model.Activity;
import com.activityproject.activitytracker.model.Running;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface RunningMapper {
    RunningDto toDto(Running running);
    Running toEntity(RunningDto dto);
}

