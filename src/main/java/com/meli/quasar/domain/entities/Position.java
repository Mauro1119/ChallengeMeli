package com.meli.quasar.domain.entities;

public class Position {
	private double X;
	private double y;
	
	public Position(double[] xy) {
		if (xy.length > 0) {
			this.X = xy[0];
			this.y = xy[1];
		}
	}
	
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}	

}
