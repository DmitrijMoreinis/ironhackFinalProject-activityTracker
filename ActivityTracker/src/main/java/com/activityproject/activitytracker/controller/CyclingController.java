package com.activityproject.activitytracker.controller;

import com.activityproject.activitytracker.dto.CyclingDto;
import com.activityproject.activitytracker.service.CyclingService;
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
@RequestMapping ("/api/v1/cycling")
public class CyclingController {

    private final CyclingService cyclingService;

    /**
     * a test-endpoint 
     * @return a hardcoded string
     */
    @GetMapping("/test")
    public String testResponse(){return "chesterfield";}

    /**
     * endpoint for saving an activity
     * @param cyclingDto gets an Dto object wich is build from the Json what comes from the frontend 
     * and passes it to the serviceobject
     * @return the created object from the service
     */
    @PostMapping
    public ResponseEntity<CyclingDto> createActivity(@RequestBody @Valid CyclingDto cyclingDto) {
        CyclingDto reDto = cyclingService.createActivity(cyclingDto);
        log.info("endpoint creating is called");
        return new ResponseEntity<>(reDto, HttpStatus.CREATED);
    }

    /**
     * @return all the activity objects in the database
     */
    @GetMapping
    public List<CyclingDto> findAll() {
        log.info("endpoint findall is called");
        return cyclingService.findAll();
    }

    /**
     * endpoint for returning one special activity
     * @param id getting the id for the activity
     * @return the activity object with one special id
     */
    @GetMapping("/{id}")
    public CyclingDto findById(@PathVariable UUID id) {
        log.info("endpoint findbyid is called with" + id);
        return cyclingService.findById(id);
    }

    /**
     * endpoint for updating an object in the dtabase
     * @param id getting the id of the object that needs to be changed
     * @param cyclingDto getting the updatet object
     * @return returning the changed object
     */
    @PutMapping("/{id}")
    public CyclingDto updateCycling(@PathVariable UUID id, @RequestBody @Valid CyclingDto cyclingDto) {
        log.info("endpoint update ist called with"+id);
        return cyclingService.updateCycling(id, cyclingDto);
    }

    /**
     * endpoint for deleting an object
     * @param id getting the id from the object that should be deleted 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        log.info("endpoint delete is called with"+id);
        cyclingService.deleteCycling(id);
    }

}
