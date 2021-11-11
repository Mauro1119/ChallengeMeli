package com.meli.quasar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meli.quasar.application.resources.mapper.SatellitesDtoMap;

@SpringBootApplication
public class ChallengeApplication {
	
	
	@Autowired
	SatellitesDtoMap satellitesMap;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
