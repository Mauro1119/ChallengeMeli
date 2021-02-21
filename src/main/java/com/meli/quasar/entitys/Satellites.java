package com.meli.quasar.entitys;

import org.springframework.stereotype.Component;

@Component
public class Satellites {
	private Satellite[] satellites;

	public Satellite[] getSatellites() {
		return satellites;
	}

	public void setSatellites(Satellite[] satellites) {
		this.satellites = satellites;
	}

	public boolean addSatellite(Satellite satellite) {

		if (satellites == null) {
			satellites = new Satellite[1];
			satellites[0] = satellite;
		} else {

			int pos = FindSatelliteInArray(satellite.getName());
			if (pos == -1) {
				if (this.satellites.length == 3) {
					// demasiados satelites
					return false;
				} else {
					// resize y agrega
					this.satellites = ResizeArray();
					satellites[satellites.length - 1] = satellite;
				}
			} else {
				// guarda en pos
				satellites[pos] = satellite;
			}
		}
		return true;

	}

	private Satellite[] ResizeArray() {
		Satellite[] sat;

		sat = new Satellite[satellites.length + 1];
		System.arraycopy(satellites, 0, sat, 0, satellites.length);

		return sat;
	}

	private int FindSatelliteInArray(String name) {		
		for (int i = 0; i < satellites.length; i++) {
			if (satellites[i].getName().toUpperCase().equals(name.toUpperCase())){
				return i;
			}
		}
		return -1;
	}
	
}
