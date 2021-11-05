package com.meli.quasar.domain.entities;

public class Position {
	private float X;
	private float y;
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public void setXY(float[] xy) {
		if (xy.length > 0) {
			this.X = xy[0];
			this.y = xy[1];
		}
	}
}
