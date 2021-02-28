package com.meli.quasar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.meli.quasar.Descifrador;
import com.meli.quasar.entitys.ResponseDecoded;
import com.meli.quasar.entitys.Satellite;
import com.meli.quasar.entitys.Satellites;
import com.meli.quasar.entitys.UnicoSatellite;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/")
public class MensajesController {
	
	@Autowired
	private Satellites sat;	
	
	private String AccessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ik1hdXJvIE1hcm96emkiLCJpYXQiOjE1MTYyMzkwMjIsInByb2ZpbGUiOiJ0b2tlbiBwYXJhIENoYWxsZW5nZSB0ZWNuaWNvIn0.qcX9aLZfEqylFZMkW3CSCZAVBH3qsHIo0owxHHRqyqU";
	
	@RequestMapping(value="topsecret", method = RequestMethod.GET)
	@ApiOperation(produces="application/json", 
					value = "Devuelve los mensajes recibidos.", 
					httpMethod="GET", notes = "<br>Este servicio devuelve todos los mensajes recibidos.", 
					response = Satellites.class)
	public ResponseEntity<Satellites> getSatellites(@RequestHeader("accesstoken") String token) {
		if (!AccessToken.equals(token)) {
			return ResponseEntity.status(403).build();
		}
		
		return ResponseEntity.ok(sat);
	}
	
	
	@RequestMapping (value="topsecret", method = RequestMethod.POST)
	@ApiOperation(produces="application/json", 
					value = "Procesa todos los mensajes a la vez.", 
					httpMethod="POST", 
					notes = "<br>Este servicio acepta todos los mensajes juntos y devuelve la información decodificada.", 
					response = ResponseDecoded.class)
	public ResponseEntity<ResponseDecoded> createSatellites(@RequestBody Satellites satellites, @RequestHeader("accesstoken") String token) {		
		if (!AccessToken.equals(token)) {
			return ResponseEntity.status(403).build();
		}
		
		sat = satellites;		
		
		ResponseDecoded resp = Descifrador.ProcesarInfo(sat);
		
		if (resp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(resp); //retornar posicion y mensaje
	}
	
	
	@RequestMapping (value="topsecret_split", method = RequestMethod.GET)
	@ApiOperation(produces="application/json", 
					value = "Obtiene la información decodificada.", 
					httpMethod="GET", 
					notes = "<br>Este servicio devuelve la información decodificada, si es posible.", 
					response = ResponseDecoded.class)
	public ResponseEntity<ResponseDecoded> getResponseDecoded(@RequestHeader("accesstoken") String token) {	
		
		if (!AccessToken.equals(token)) {
			return ResponseEntity.status(403).build();
		}
		//por si no se cargaron mensajes.
		if (sat.getSatellites() == null ) {
			return ResponseEntity.notFound().build();
		}
		ResponseDecoded resp =  Descifrador.ProcesarInfo(sat);
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
	public ResponseEntity<Void> createSatellite(@PathVariable("satelliteName") String satelliteName, @RequestBody UnicoSatellite satellite,@RequestHeader("accesstoken") String token) {		
		if (!AccessToken.equals(token)) {
			return ResponseEntity.status(403).build();
		}
		Satellite satUnico = new Satellite();
		satUnico.setName(satelliteName);	
		satUnico.setDistance(satellite.getDistance());
		satUnico.setMessage(satellite.getMessage());
		
		if (!sat.addSatellite(satUnico)) {
			//throw new SatelliteNotFoundException("Demasiados satelites");
			return ResponseEntity.unprocessableEntity().build();
		};				
		//Obtener posicion y mensaje		
		//throw new SatelliteNotFoundException("Demasiados satelites");
		return ResponseEntity.ok(null); 
	}
	
	
	@RequestMapping (value="topsecret", method = RequestMethod.DELETE)
	@ApiOperation(produces="application/json", 
					value = "Limpia todos los mensajes.", 
					httpMethod="DELETE", notes = "<br>Este servicio elimina todos los mensajes guardados.")
	public ResponseEntity<Void> cleanSatellites(@RequestHeader("accesstoken") String token) {		
		if (!AccessToken.equals(token)) {
			return ResponseEntity.status(403).build();
		}
		//Elimina todos los mensajes.
		sat.setSatellites(null);
		
		return ResponseEntity.noContent().build(); //retornar posicion y mensaje
	}
	
}
