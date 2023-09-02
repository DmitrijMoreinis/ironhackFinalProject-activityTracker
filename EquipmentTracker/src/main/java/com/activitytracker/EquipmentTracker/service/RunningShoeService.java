package com.activitytracker.EquipmentTracker.service;

import com.activitytracker.EquipmentTracker.dto.RunningShoeDto;
import com.activitytracker.EquipmentTracker.mapper.RunningShoeMapper;
import com.activitytracker.EquipmentTracker.model.RunningShoe;
import com.activitytracker.EquipmentTracker.repository.RunningShoeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RunningShoeService {
    private final RunningShoeRepository runningShoeRepository;
    private final RunningShoeMapper mapper;
    
    public RunningShoeDto createShoe (RunningShoeDto runningShoeDto) {
        var entity = mapper.toEntity(runningShoeDto);
        runningShoeRepository.save(entity);
        return mapper.toDto(entity);
    }

    public List<RunningShoeDto> findAll() {
        return runningShoeRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public RunningShoeDto findById(Integer id) {
        return mapper.toDto( runningShoeRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"no such element found")));
    }

    public RunningShoeDto updateShoe(Integer id, RunningShoeDto runningShoeDto) {
        if (!runningShoeRepository.existsById(id)) {
            throw new EntityNotFoundException("Activity" + id+ " not found");
        }
        RunningShoe entity = mapper.toEntity(runningShoeDto);
        entity.setId(id);
        runningShoeRepository.save(entity);
        return mapper.toDto(entity);
    }

    public RunningShoeDto deleteShoe(Integer id) {

        Optional<RunningShoe> re = runningShoeRepository.findById(id);
        if(re.isPresent()){
            runningShoeRepository.deleteById(id);
            return mapper.toDto(re.get()) ;}
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "activity not found-database_error");
    }
}
