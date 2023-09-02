package com.activityproject.activitytracker.service;

import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.feignclient.EquipmentClient;
import com.activityproject.activitytracker.feignclient.FullShoeResponse;
import com.activityproject.activitytracker.mapper.RunningMapper;
import com.activityproject.activitytracker.model.Running;
import com.activityproject.activitytracker.model.RunningShoe;
import com.activityproject.activitytracker.repository.RunningRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RunningService {

    private final RunningRepository runningRepository;
    private final RunningMapper mapper;
    private final EquipmentClient client;

    /**
     *
     * @param runningDto
     * @return
     */
    public RunningDto createActivity(RunningDto runningDto) {
        var entity = mapper.toEntity(runningDto);
        log.info("runningactivity saving");

        runningRepository.save(entity);

        return mapper.toDto(entity);
    }

    public List<RunningDto> findAll() {
        return runningRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public RunningDto findById(UUID id) {
     return mapper.toDto( runningRepository.findById(id)
             .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"no such element found")));
    }

    public RunningDto updateRunning(UUID id, RunningDto runningDto) {
        if (!runningRepository.existsById(id)) {
           throw new EntityNotFoundException("Activity" + id+ " not found");
        }
        Running entity = mapper.toEntity(runningDto);
        entity.setId(id);
        log.info("runningactivity updating");

        runningRepository.save(entity);

        return mapper.toDto(entity);
    }

    public RunningDto deleteRunning(UUID id) {

        Optional<Running> re = runningRepository.findById(id);
        if(re.isPresent()){
            runningRepository.deleteById(id);
            log.info("runningactivity deleted");

            return mapper.toDto(re.get()) ;}
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "activity not found-database_error");
    }

    /**
     * getting the actual shoe from the other service and population the fullrepsonse with the actualshoe and the number of kilometers runned with it
     * @returnthe full response of the used shoe inclusive the runnedkiloemters and
     */
    public FullShoeResponse getActualRunningEquipment() {
        RunningShoe actualShoe = client.findActualShoe(SecurityContextHolder.getContext().getAuthentication().getName());
        FullShoeResponse fs = new FullShoeResponse();
        fs.setRunnedKm(runningRepository.runnedKmSinceCreationShoe(actualShoe.getUser(),actualShoe.getUsedSince()));
        fs.setBrand(actualShoe.getBrand());
        fs.setType(actualShoe.getType());
        fs.setShoeUser(actualShoe.getUser());
        fs.setMaxKm(actualShoe.getMaxKm());
        fs.setUsedSince(fs.getUsedSince());
        fs.setShoeUser(fs.getShoeUser());
        return fs;
    }


}
