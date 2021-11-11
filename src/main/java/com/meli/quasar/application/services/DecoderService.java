package com.meli.quasar.application.services;

import org.springframework.stereotype.Component;

import com.meli.quasar.application.resources.ResponseDecoded;
import com.meli.quasar.application.resources.SatellitesDto;
import com.meli.quasar.application.resources.mapper.ResponseDecodedMap;
import com.meli.quasar.application.resources.mapper.SatellitesDtoMap;
import com.meli.quasar.domain.entities.*;


@Component
public class DecoderService {
	
	private SatellitesDtoMap satellitesMap;
	private ResponseDecodedMap responseDecodedMap;
	
	
	public DecoderService(SatellitesDtoMap satellitesDtoMap, ResponseDecodedMap responseDecodesMap) {
		this.satellitesMap = satellitesDtoMap;
		this.responseDecodedMap = responseDecodesMap;
	}
	
	
	public ResponseDecoded ProcesarInfo(SatellitesDto satellites) {
		//map
		
		Satellites sat = satellitesMap.SatellitesDtoMapping(satellites);
		 
//		Satellites sat = satellites;		

		//ResponseDecoded resp = new ResponseDecoded();
		//String[][] mensajes;
		
//		int err = 0;

		// var respuesta responseDecoded
			dist = Find(sat.getSatellites(), "Skywalker");
		if (sat.getSatellites().size() == 3) {
	//		err = 1; 
		
		
			EnemyCharger enemyCharger = new EnemyCharger();
			//if (err == 0) {			
			enemyCharger.setPosition(new Position(PositionDecoderService.GetLocation(sat.getPosition(),sat.getDistances())));

			if (enemyCharger.getPosition() != null) {
				enemyCharger.setMessage(MessageDecoderService.GetMessage(sat.getMessages()));
				if (enemyCharger.getMessage() != null) {
					return responseDecodedMap.ResponseDecodedMapping(enemyCharger);
				}
				
			}
			/*
				if (enemyCharger.getPosition() == null) {
					err = 1;
				} else {
					enemyCharger.setMessage(MessageDecoderService.GetMessage(sat.getMessages()));
					if (enemyCharger.getMessage() == null) {
						err = 1;
					} 
				}*/
	//		} 
		}
		return null;
		
		/*if (err != 1) {
			//map responsocode
			return responseDecodedMap.ResponseDecodedMapping(enemyCharger);
			//return resp;
		} else {
			return null;
		} */

	}
}
