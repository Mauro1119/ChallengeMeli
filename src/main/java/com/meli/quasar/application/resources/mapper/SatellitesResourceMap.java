package com.meli.quasar.application.resources.mapper;

import org.springframework.stereotype.Component;

import com.meli.quasar.application.resources.SatelliteDto;
import com.meli.quasar.application.resources.SatellitesDto;

import com.meli.quasar.domain.entities.Satellites;
@Component
public class SatellitesResourceMap {
	
	private Satellites satellites;
	public SatellitesResourceMap(Satellites satellites)
	{
		this.satellites = satellites;
		
	}
	
	
	public SatellitesDto SatelliteResourceMapping() {
		SatellitesDto satellitesDto = new SatellitesDto();
		SatelliteDto satelliteDto = new SatelliteDto();
		
		for(int i = 0; i < satellites.getSatellites().size(); i++){
			satelliteDto.setName(satellites.getSatellites().get(i).getName());
			satelliteDto.setDistance(satellites.getSatellites().get(i).getDistance());
			satelliteDto.setMessage(satellites.getSatellites().get(i).getMessage());
			satellitesDto.addSatellite(satelliteDto);
		}
		
		return satellitesDto;
			
	};
}
