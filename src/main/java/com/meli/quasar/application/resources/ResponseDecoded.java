package com.meli.quasar.application.resources;

import com.meli.quasar.domain.entities.Position;

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
