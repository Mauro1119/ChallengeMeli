package com.meli.quasar.domain.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


/**

* Representa una lista de satellites.

*/
@Component
public class Satellites {
	private List<Satellite> satellites;
	
	public Satellites() {
 		this.satellites = new ArrayList<Satellite>();
	}
	
	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}

	/**

	* Retorna las posiciones de las naves, ordenadas (KENOBI,SKYWALKER,SATO)

	*/
	public double[][] getPosition(){
		double[][] positions = new double[satellites.size()][2];
		for (int i = 0; i < satellites.size(); i++) {
			if (i==0) {
				Position position = satellites.get(FindSatelliteInArray("Kenobi")).getPosition();
				positions[i][0] = position.getX();
				positions[i][1] = position.getY();
			}
			if (i==1) {
				Position position = satellites.get(FindSatelliteInArray("Skywalker")).getPosition();
				positions[i][0] = position.getX();
				positions[i][1] = position.getY();
			}
			if (i==2) {
				Position position = satellites.get(FindSatelliteInArray("Sato")).getPosition();
				positions[i][0] = position.getX();
				positions[i][1] = position.getY();
			}
			
		}
		return positions;
	}
	
	/**

	* Agrega un satellite a la lista, con su respectiva posición.

	*/
	public boolean addSatellite(Satellite satellite) {		
		//{-500.0,-200.0},{100.0,-100.0},{500.0,100.0}
		if (satellite.getName().toUpperCase().equals("Kenobi".toUpperCase())) {
			double[] position = {-500.0,-200.0};			
			satellite.setPosition(new Position(position));
		}
		if (satellite.getName().toUpperCase().equals("Sato".toUpperCase())) {
			double[] position = {500.0,100.0};			
			satellite.setPosition(new Position(position));
		}
		if (satellite.getName().toUpperCase().equals("Skywalker".toUpperCase())) {
			double[] position = {100.0,-100.0};			
			satellite.setPosition(new Position(position));
		}		
		
		if (!(satellite.getName().toUpperCase().equals("Sato".toUpperCase())) && 
				!(satellite.getName().toUpperCase().equals("Skywalker".toUpperCase())) && 
				!(satellite.getName().toUpperCase().equals("Kenobi".toUpperCase()))) {
			return false;
		}
		if (satellites == null) {			
			satellites.add(satellite);
		} else {

			int pos = FindSatelliteInArray(satellite.getName());
			if (pos == -1) {
				if (this.satellites.size() == 3) {				
					// demasiados satelites
					return false;
				} else {
					// agrega
					satellites.add(satellite);
				}
			} else {
				// guarda en pos
				satellites.add(pos, satellite);
				
			}
		}
		return true;

	}

	
	/**

	* Retorna las distancias a cada satellite de los mensajes enviados
	* ordenadas (KENOBI,SKYWALKER,SATO)

	*/
	public double[] getDistances(){

        double [] distances = new double[satellites.size()];
       
		for (int i = 0; i < satellites.size(); i++) {
			
			if (i==0) {
				distances[i] = satellites.get(FindSatelliteInArray("Kenobi")).getDistance();
			}
			if (i==1) {
				distances[i] = satellites.get(FindSatelliteInArray("Skywalker")).getDistance();
			}
			if (i==2) {
				distances[i] = satellites.get(FindSatelliteInArray("Sato")).getDistance();
			}
			
		}
        	
        return  distances;
    }
    
	/**

	* Retorna los mensajes enviados a cada satellite

	*/
    public String[][] getMessages(){
        String[][] messages = new String[satellites.size()][];
        for(int i = 0; i < satellites.size(); i++){
            messages[i] = satellites.get(i).getMessage();
        }
        return  messages;
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
