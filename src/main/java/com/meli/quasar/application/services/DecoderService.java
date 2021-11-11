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

		
		Satellites sat = satellitesMap.SatellitesDtoMapping(satellites);
		 
		// var respuesta responseDecoded
		if (sat.getSatellites().size() == 3) {	
		
			EnemyCharger enemyCharger = new EnemyCharger();
			enemyCharger.setPosition(new Position(PositionDecoderService.GetLocation(sat.getPosition(),sat.getDistances())));

			if (enemyCharger.getPosition() != null) {
				enemyCharger.setMessage(MessageDecoderService.GetMessage(sat.getMessages()));
				if (enemyCharger.getMessage() != null) {
					return responseDecodedMap.ResponseDecodedMapping(enemyCharger);
				}
				
			}

		}
		return null;


	}
}
