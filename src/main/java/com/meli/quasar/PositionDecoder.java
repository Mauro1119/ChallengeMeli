package com.meli.quasar;

/*Decodificador de la posición de la nave.*/

public class PositionDecoder {

	public static float[] GetLocation(float... distances) {

		float d1, d2, d3, d, e, f, multi;

		boolean sigueOk = true;
		if (distances.length != 3) {
			return new float[] {};
		}

		// Distancias entre los satelites.
		d1 = (float) 670.8203; // Raiz de 450000
		d2 = (float) 447.2135; // Raiz de 200000
		d3 = (float) 1044.0306; // Raiz de 1090000

		// Verfica si se tocan las circunferencias sino están mal los radios.
		if (!((d1 <= distances[0] + distances[1]) && (d1 >= Math.abs(distances[0] - distances[1])))) {
			sigueOk = false;
		}

		if (!((d2 <= distances[1] + distances[2]) && (d2 >= Math.abs(distances[1] - distances[2])))) {
			sigueOk = false;
		}

		if (!((d3 <= distances[0] + distances[2]) && (d3 >= Math.abs(distances[0] - distances[2])))) {
			sigueOk = false;
		}

		if (!sigueOk) {
			return new float[] {};
		}

		// Arma la matriz con los sistemas de ecuaciones lineales, restando las
		// ecuaciones de las circunferencias.
		float[][] M = new float[2][3];

		d = (-2) * (-500);
		e = (-2) * (-200);
		f = (-500) * (-500) + (-200) * (-200) - distances[0] * distances[0];

		M[0][0] = d;
		M[0][1] = e;
		M[0][2] = (-1) * f;

		d = (-2) * (100);
		e = (-2) * (-100);
		f = (100) * (100) + (-100) * (-100) - distances[1] * distances[1];

		M[0][0] -= d;
		M[0][1] -= e;
		M[0][2] -= (-1) * f;

		d = (-2) * (-500);
		e = (-2) * (-200);
		f = (-500) * (-500) + (-200) * (-200) - distances[0] * distances[0];

		M[1][0] = d;
		M[1][1] = e;
		M[1][2] = (-1) * f;

		d = (-2) * (500);
		e = (-2) * (100);
		f = (500) * (500) + (100) * (100) - distances[2] * distances[2];

		M[1][0] -= d;
		M[1][1] -= e;
		M[1][2] -= (-1) * f;

		// Metod Gauss-Jordan.
		if (M[0][0] != 0) {
			multi = M[0][0];
			for (int i = 0; i < 3; i++) {
				M[0][i] = M[0][i] / multi;
			}

		} else {
			if (M[1][0] != 0) {
				float cambio;
				for (int i = 0; i < 3; i++) {
					cambio = M[0][i];
					M[0][i] = M[1][i];
					M[1][i] = cambio;
				}

				multi = M[0][0];
				for (int i = 0; i < 3; i++) {
					M[0][i] = M[0][i] / multi;
				}

			} else {
				return new float[] {};
			}
		}

		multi = M[1][0];
		for (int i = 0; i < 3; i++) {
			M[1][i] = M[1][i] - M[0][i] * multi;
		}

		if (M[1][1] != 0) {
			multi = M[1][1];
			for (int i = 1; i < 3; i++) {
				M[1][i] = M[1][i] / multi;
			}

		} else {
			return new float[] {};
		}

		multi = M[0][1];
		for (int i = 1; i < 3; i++) {
			M[0][i] = M[0][i] - M[1][i] * multi;
		}

		return new float[] { M[0][2], M[1][2] };
	}
}
