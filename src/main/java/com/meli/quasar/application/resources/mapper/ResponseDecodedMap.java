package com.meli.quasar.application.resources.mapper;

import org.springframework.stereotype.Component;

import com.meli.quasar.application.resources.ResponseDecoded;
import com.meli.quasar.domain.entities.EnemyCharger;

/**

* Mapea un SatelliteDto devolviendo un Satellite

*/
@Component
public class ResponseDecodedMap {
	public ResponseDecoded ResponseDecodedMapping(EnemyCharger enemyCharger) {
		ResponseDecoded responseDecoded = new ResponseDecoded();
		
		responseDecoded.setMessage(enemyCharger.getMessage());
		responseDecoded.setPos(enemyCharger.getPosition());
		
		return responseDecoded;
			
	};
}
