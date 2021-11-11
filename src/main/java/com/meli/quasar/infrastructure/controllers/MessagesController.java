package com.meli.quasar.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.quasar.application.exceptions.SatelliteNotFoundException;
import com.meli.quasar.application.resources.MessageDto;
import com.meli.quasar.application.resources.ResponseDecoded;
import com.meli.quasar.application.resources.SatelliteDto;
import com.meli.quasar.application.resources.SatellitesDto;

import com.meli.quasar.application.services.*;


import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/")
public class MessagesController {
	
	
	@Autowired
	private SatellitesDto satellitesDto;
	
	ResponseDecoded responseDecoded;
	
	@Autowired
	private DecoderService decoderService;
	
	
	@RequestMapping (value="topsecret", method = RequestMethod.POST)
	@ApiOperation(produces="application/json", 
					value = "Procesa todos los mensajes a la vez.", 
					httpMethod="POST", 
					notes = "<br>Este servicio acepta todos los mensajes juntos y devuelve la información decodificada.", 
					response = ResponseDecoded.class)
	public ResponseEntity<ResponseDecoded> createSatellites(@RequestBody SatellitesDto satellites) {
		ResponseDecoded resp = decoderService.ProcesarInfo(satellites);

		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(resp); //retornar posicion y mensaje
	}
	
	
	@RequestMapping (value="topsecret_split", method = RequestMethod.GET)
	@ApiOperation(produces="application/json", 
					value = "Obtiene la información decodificada.", 
					httpMethod="GET", 
					notes = "<br>Este servicio devuelve la información decodificada, si es posible.", 
					response = ResponseDecoded.class)
	public ResponseEntity<ResponseDecoded> getResponseDecoded() {	
		
		//por si no se cargaron mensajes.
		if (satellitesDto.getSatellites().size() != 3 ) {
			throw new SatelliteNotFoundException("No se puede agregar el satellite");
		}
		ResponseDecoded resp =  decoderService.ProcesarInfo(satellitesDto);
		if (resp == null) {
			return ResponseEntity.notFound().build();
		}
		//Obtener posicion y mensaje
		return ResponseEntity.ok(resp); //retornar posicion y mensaje

	}
	
	@RequestMapping (value="topsecret_split/{satelliteName}", method = RequestMethod.POST)
	@ApiOperation(produces="application/json", 
					value = "Enviar nuevo mensaje a un satelite especifico.", 
					httpMethod="POST", 
					notes = "<br>Este servicio envia la informacion de un satelite especificado y lo agrega a los ya existentes.")
	public ResponseEntity<Void> createSatellite(@PathVariable("satelliteName") String satelliteName, @RequestBody MessageDto messageDto) {		
		
		SatelliteDto satelliteDto = new SatelliteDto();
		satelliteDto.setName(satelliteName);
		satelliteDto.setMessage(messageDto.getMessage());
		satelliteDto.setDistance(messageDto.getDistance());
		
		if (!satellitesDto.addSatellite(satelliteDto)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		};				

		return ResponseEntity.ok(null); 
	}
	

}
