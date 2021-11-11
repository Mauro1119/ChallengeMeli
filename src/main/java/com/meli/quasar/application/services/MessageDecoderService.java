package com.meli.quasar.application.services;

import java.util.ArrayList;

/*Decodificador del mensaje.*/

public class MessageDecoderService {

	public static String GetMessage(String[]... messages) {

		int messagesLength = 0;

		for (int y = 0; y < messages.length; y++) {
			if (y == 0) {
				messagesLength = messages[y].length;
			} else {
				if (messagesLength > messages[y].length) {
					messagesLength = messages[y].length;
				}
			}
		}

		for (int y = 0; y < messages.length; y++) {
			if (messages[y].length > messagesLength) {
				for (int x = 0; x < messages[y].length - messagesLength; x++) {
					if (messages[y][x] != "") {
						return null;
					}
				}
			}
		}

		ArrayList<String> message = new ArrayList<String>();
		boolean notFound;
		for (int l = 0; l < messagesLength; l++) {
			notFound = true;
			for (int y = 0; y < messages.length; y++) {

				if (messagesLength < messages[y].length) {
					if (messages[y][l + (messages[y].length - messagesLength)] != "") {
						notFound = false;
						message.add(messages[y][l + messages[y].length - messagesLength]);
						break;
					}
				} else {
					if (messages[y][l] != "") {
						notFound = false;
						message.add(messages[y][l]);
						break;
					}
				}

			}
			if (notFound) {
				return null;
			}
		}
		return String.join(" ", message);
	}

}
