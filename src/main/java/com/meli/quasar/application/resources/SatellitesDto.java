package com.meli.quasar.application.resources;

import com.meli.quasar.domain.entities.Satellite;

public class SatellitesDto {
	private Satellite[] satellites;

	public Satellite[] getSatellites() {
		return satellites;
	}

	public void setSatellites(Satellite[] satellites) {
		this.satellites = satellites;
	}

}
