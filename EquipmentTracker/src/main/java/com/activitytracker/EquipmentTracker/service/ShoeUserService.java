package com.activitytracker.EquipmentTracker.service;

import com.activitytracker.EquipmentTracker.dto.RunningShoeDto;
import com.activitytracker.EquipmentTracker.mapper.RunningShoeMapper;
import com.activitytracker.EquipmentTracker.model.RunningShoe;
import com.activitytracker.EquipmentTracker.repository.ShoeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ShoeUserService {

    private final ShoeUserRepository shoeUserRepository;
    private final RunningShoeMapper mapper;
    public RunningShoeDto getActualShoe(String name) {
        Optional<RunningShoe> re = shoeUserRepository.findByName(name);
        if(re.isPresent()){
            return mapper.toDto(re.get());}
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "activity not found");
    }

}
