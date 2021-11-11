package com.meli.quasar.application.resources;

import org.springframework.stereotype.Component;

import com.meli.quasar.domain.entities.Position;
/**

* Representa la respuesta con el mensaje y posición decodificada.

*/
@Component
public class ResponseDecoded {	
	private Position position;
	private String message;

	
	public Position getPos() {
		return position;
	}
	public void setPos(Position pos) {
		this.position = pos;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}		
}
