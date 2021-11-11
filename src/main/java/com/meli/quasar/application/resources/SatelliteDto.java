package com.meli.quasar.application.resources;

/**

* Representa un SatelliteDto, para transferencia con la capa de infraestructura.

*/
public class SatelliteDto extends MessageDto{
	private String name;	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
