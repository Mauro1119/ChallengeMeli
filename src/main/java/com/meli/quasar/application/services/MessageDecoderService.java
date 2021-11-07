package com.meli.quasar.application.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

/*Decodificador del mensaje.*/

public class MessageDecoderService {

	public static String GetMessage(String[]... messages) {

		int largo = 0;

		for (int y = 0; y < messages.length; y++) {
			if (y == 0) {
				largo = messages[y].length;
			} else {
				if (largo > messages[y].length) {
					largo = messages[y].length;
				}
			}
		}

		for (int y = 0; y < messages.length; y++) {
			if (messages[y].length > largo) {
				for (int x = 0; x < messages[y].length - largo; x++) {
					if (messages[y][x] != "") {
						return null;
					}
				}
			}
		}

		ArrayList<String> mensaje = new ArrayList<String>();
		boolean noEncontro;
		for (int l = 0; l < largo; l++) {
			noEncontro = true;
			for (int y = 0; y < messages.length; y++) {

				if (largo < messages[y].length) {
					if (messages[y][l + (messages[y].length - largo)] != "") {
						noEncontro = false;
						mensaje.add(messages[y][l + messages[y].length - largo]);
						break;
					}
				} else {
					if (messages[y][l] != "") {
						noEncontro = false;
						mensaje.add(messages[y][l]);
						break;
					}
				}

			}
			if (noEncontro) {
				return null;
			}
		}
		return String.join(" ", mensaje);
	}

}
