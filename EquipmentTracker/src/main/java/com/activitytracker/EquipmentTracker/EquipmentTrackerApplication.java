package com.activitytracker.EquipmentTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EquipmentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentTrackerApplication.class, args);
	}

}
