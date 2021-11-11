package com.meli.quasar.application.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**

* Representa un lista de LatellitesDto
*/
@Component
public class SatellitesDto {
	private List<SatelliteDto> satellites;
	
	public SatellitesDto() {
 		this.satellites = new ArrayList<SatelliteDto>();
	}
		
	public List<SatelliteDto> getSatellites() {

		return satellites;
	}

	public void setSatellites(List<SatelliteDto> satellites) {
		this.satellites = satellites;
	}

	/**

	* Agrega un satellite a la lista. Si existe lo reemplaza, de lo contrario lo agrega a al final.
	* Si no lo puede agregar devuelve falso.

	*/
	public boolean addSatellite(SatelliteDto satelliteDto) {
		
		if (satellites == null) {			
			satellites.add(satelliteDto);
		} else {

			int pos = FindSatelliteInArray(satelliteDto.getName());
			if (pos == -1) {
				if (this.satellites.size() == 3) {				
					// demasiados satelites
					return false;
				} else {
					// agrega
					satellites.add(satelliteDto);
				}
			} else {
				// guarda en pos
				satellites.add(pos, satelliteDto);
				
			}
		}
		return true;

	}
	
	private int FindSatelliteInArray(String name) {		
		for (int i = 0; i < satellites.size(); i++) {
			if (satellites.get(i).getName().toUpperCase().equals(name.toUpperCase())){
				return i;
			}
		}
		return -1;
	}

}
