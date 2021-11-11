package com.meli.quasar.application.resources.mapper;

import org.springframework.stereotype.Component;

import com.meli.quasar.application.resources.SatelliteDto;
import com.meli.quasar.domain.entities.Satellite;

/**

* Mapea un SatelliteDto devolviendo un Satellite

*/

@Component
public class SatelliteDtoMap {
	public Satellite SatelliteDtoMapping(SatelliteDto satelliteDto) {
		Satellite satellite = new Satellite();
		satellite.setName(satelliteDto.getName());
		satellite.setDistance(satelliteDto.getDistance());
		satellite.setMessage(satelliteDto.getMessage());
		
		return satellite;
			
	};
}
