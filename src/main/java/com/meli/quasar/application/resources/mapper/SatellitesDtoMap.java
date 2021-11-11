package com.meli.quasar.application.resources.mapper;


import org.springframework.stereotype.Component;

import com.meli.quasar.application.resources.SatelliteDto;
import com.meli.quasar.application.resources.SatellitesDto;

import com.meli.quasar.domain.entities.Satellites;


/**

* Esta clase mapea una lista de SatellitesDto a devolviendo una lista de Satellites

*/

@Component
public class SatellitesDtoMap {
	
	SatelliteDtoMap satelliteDtoMap;
	public SatellitesDtoMap(SatelliteDtoMap satelliteDtoMap) {
		this.satelliteDtoMap = satelliteDtoMap;
		
	}
	public Satellites SatellitesDtoMapping(SatellitesDto satellitesDto) {
		Satellites satellites = new Satellites();
		
		for (SatelliteDto satelliteDto : satellitesDto.getSatellites()) {
			satellites.addSatellite(satelliteDtoMap.SatelliteDtoMapping(satelliteDto));
		}
		
		return satellites;
			
	};



}





