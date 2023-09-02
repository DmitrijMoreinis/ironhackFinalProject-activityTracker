package com.activitytracker.EquipmentTracker.controller;


import com.activitytracker.EquipmentTracker.dto.RunningShoeDto;
import com.activitytracker.EquipmentTracker.service.RunningShoeService;
import com.activitytracker.EquipmentTracker.service.ShoeUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/equipmentrunning")

public class RunningShoeController {

        private final RunningShoeService runningShoeService;

        @GetMapping("/test")
        public String testResponse(){return "chesterfield";}


        @PostMapping
        public ResponseEntity<RunningShoeDto> createShoe(@RequestBody @Valid RunningShoeDto runningShoeDto) {
            RunningShoeDto reDto = runningShoeService.createShoe(runningShoeDto);
            return new ResponseEntity<>(reDto, HttpStatus.CREATED);
        }

        @GetMapping
        public List<RunningShoeDto> findAll() {
            return runningShoeService.findAll();
        }

        @GetMapping("/{id}")
        public RunningShoeDto findById(@PathVariable Integer id) {
            return runningShoeService.findById(id);
        }

        @PutMapping("/{id}")
        public RunningShoeDto updateShoe(@PathVariable Integer id, @RequestBody @Valid RunningShoeDto runningShoeDto) {
            return runningShoeService.updateShoe(id, runningShoeDto);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteShoe(@PathVariable Integer id) {
            runningShoeService.deleteShoe(id);
        }

  }



