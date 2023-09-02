package com.activityproject.activitytracker.mapper;

import com.activityproject.activitytracker.dto.ActivityDto;
import com.activityproject.activitytracker.dto.CyclingDto;
import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.model.Activity;
import com.activityproject.activitytracker.model.Cycling;
import com.activityproject.activitytracker.model.Running;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.SubclassMapping;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface CyclingMapper {

         CyclingDto toDto(Cycling entity);

         Cycling toEntity(CyclingDto cyclingDto);

    }

