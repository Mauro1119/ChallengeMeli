package com.meli.quasar.domain.entities;

public class Satellite extends Spaceship {
	private String name;
	private double distance;
	private String[] message;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double d) {
		this.distance = d;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	
	
	
}