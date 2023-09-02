package com.activitytracker.EquipmentTracker.controller;

import com.activitytracker.EquipmentTracker.dto.RunningShoeDto;
import com.activitytracker.EquipmentTracker.model.RunningShoe;
import com.activitytracker.EquipmentTracker.service.ShoeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipmentrunning")

public class ShoeUserController {

    private final ShoeUserService shoeUserService;

    /**
     * endpoint for the activitytrackerservice
     * @param name name of theactualuser
     * @return the  actualshoe
     */
    @GetMapping("/{name}")
    public RunningShoeDto getActualShoe(@PathVariable String name) {
        return shoeUserService.getActualShoe(name);
    }
}
