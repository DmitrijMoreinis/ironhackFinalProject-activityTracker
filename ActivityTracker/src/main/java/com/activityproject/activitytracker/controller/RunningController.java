package com.activityproject.activitytracker.controller;

import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.feignclient.FullShoeResponse;
import com.activityproject.activitytracker.service.RunningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/running")

public class RunningController {

    private final RunningService runningService;

    /**
     * endpoint for saving an activity
     * @param runningDto gets an Dto object wich is build from the Json what comes from the frontend
     * and passes it to the serviceobject
     * @return the created object from the service
     */
    @PostMapping
    public ResponseEntity<RunningDto> createActivity(@RequestBody @Valid RunningDto runningDto) {
        log.info("endpoint creating is called");
        RunningDto reDto = runningService.createActivity(runningDto);
        return  new ResponseEntity<>(reDto, HttpStatus.CREATED);
    }
     /**
     * @return all the activity objects in the database
     */
    @GetMapping
    public List<RunningDto> findAll() {
        log.info("endpoint findall is called");
        return runningService.findAll();
    }
    /**
     * endpoint for returning one special activity
     * @param id getting the id for the activity
     * @return the activity object with one special id
     */
    @GetMapping("/{id}")
    public RunningDto findById(@PathVariable UUID id) {
        log.info("endpoint findbyid is called with" + id);
        return runningService.findById(id);
    }
     /**
     * endpoint for updating an object in the dtabase
     * @param id getting the id of the object that needs to be changed
     * @param runningDto getting the updatet object
     * @return returning the changed object
     */

    @PutMapping("/{id}")
    public RunningDto updateRunning(@PathVariable UUID id, @RequestBody @Valid RunningDto runningDto) {
        log.info("endpoint update ist called with"+id);
        return runningService.updateRunning(id, runningDto);
    }
    /**
     * endpoint for deleting an object
     * @param id getting the id from the object that should be deleted 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        log.info("endpoint delete is called with"+id);
        runningService.deleteRunning(id);
    }

    /**
     * endpoint for the actual equipment
     *
     */
    @GetMapping("/actualEquipment)")
    public ResponseEntity<FullShoeResponse> getActualRunningEquipment() {

        return ResponseEntity.ok(runningService.getActualRunningEquipment());

    }




}
