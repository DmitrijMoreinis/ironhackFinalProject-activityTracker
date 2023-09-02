package com.activityproject.activitytracker.feignclient;


import com.activityproject.activitytracker.model.RunningShoe;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "equipmenttracker", url = "${application.config.equipmenttracker-url}")

public interface EquipmentClient {

    @GetMapping("/equipment/{name}")
    RunningShoe findActualShoe(@PathVariable String name);

}
