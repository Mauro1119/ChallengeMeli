package com.meli.quasar.application.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SatelliteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SatelliteNotFoundException(String message) {
		 super(message);

	}

}
