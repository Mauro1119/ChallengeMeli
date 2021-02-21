package com.meli.quasar;

import com.meli.quasar.entitys.Position;
import com.meli.quasar.entitys.ResponseDecoded;
import com.meli.quasar.entitys.Satellite;
import com.meli.quasar.entitys.Satellites;

public class Descifrador {
	public static ResponseDecoded ProcesarInfo(Satellites sat) {

		ResponseDecoded resp = new ResponseDecoded();
		float[] distancias = new float[3];
		String[][] mensajes;
		int err = 0;

		// var respuesta responseDecoded
		if (sat.getSatellites().length == 3) {
			float dist = Find(sat.getSatellites(), "Kenobi");
			if (dist >= 0) {
				distancias[0] = dist;
			} else {
				err = 1;
			}
			dist = Find(sat.getSatellites(), "Skywalker");
			if (dist >= 0) {
				distancias[1] = dist;
			} else {
				err = 1;
			}
			dist = Find(sat.getSatellites(), "Sato");
			if (dist >= 0) {
				distancias[2] = dist;
			} else {
				err = 1;
			}
		} else {
			err = 1;
		}

		if (err == 0) {
			float[] posiciones = PositionDecoder.GetLocation(distancias);

			if (posiciones.length == 0) {
				err = 1;
			} else {
				Position pos = new Position();
				pos.setXY(posiciones);
				resp.setPos(pos);

				mensajes = new String[3][];
				for (int i = 0; i < sat.getSatellites().length; i++) {
					mensajes[i] = sat.getSatellites()[i].getMessage();
				}

				String mens = MessageDecoder.GetMessage(mensajes);
				if (mens == null) {
					err = 1;
				} else {
					resp.setMessage(mens);
				}
			}
		} else {
			err = 1;
		}

		if (err != 1) {
			return resp;
		} else {
			return null;
		}

	}

	private static float Find(Satellite[] sats, String nombre) {
		for (int i = 0; i < sats.length; i++) {
			if (sats[i].getName().toUpperCase().equals(nombre.toUpperCase())){
				return sats[i].getDistance();
			}
		}
		return -1; //No encontrado

	}

}
