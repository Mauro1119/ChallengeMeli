package com.meli.quasar.application.resources;

public class MessageDto {
	
	private double distance;
	private String[] message;
	
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}

}
