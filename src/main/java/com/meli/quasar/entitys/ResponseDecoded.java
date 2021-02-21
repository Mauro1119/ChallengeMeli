package com.meli.quasar.entitys;


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
