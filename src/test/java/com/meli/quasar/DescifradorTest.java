package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.meli.quasar.application.decoder.*;
import com.meli.quasar.application.resources.ResponseDecoded;
import com.meli.quasar.domain.entities.*;

@RunWith(value = Parameterized.class)
public class DescifradorTest {

	@Parameters
	public static Iterable<Object[]> getData() {

		return Arrays.asList(new Object[][] {
				{ GenerarSatellites(NuevoSatellite("Kenobi", 0, new String[] { "este", "", "", "mensaje", "", "" }),
						NuevoSatellite("Skywalker", 0, new String[] { "este", "", "", "mensaje", "", "" }),
						NuevoSatellite("Sato", 0, new String[] { "este", "", "", "mensaje", "", "" })), null },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (float) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new float[] { -100, 200 }, "este es un mensaje ultra secreto") },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 1085.41,
								new String[] { "", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 626.99,
								new String[] { "", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (float) 403.88,
								new String[] { "", "", "", "mensaje", "ultra", "" })), null},
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" })),	null},
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 100,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 105.5,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (float) 142.7,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),null },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 583.09,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 412.31,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (float) 781.02,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new float[] { 0, -500 }, "este es un mensaje ultra secreto") },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (float) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (float) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (float) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" }),
						NuevoSatellite("Otro", (float) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new float[] { -100, 200 }, "este es un mensaje ultra secreto")}});

	}

	private static ResponseDecoded RespuestaEsperada(float[] coord, String mensaje) {
		ResponseDecoded resp = new ResponseDecoded();
		Position pos = new Position();
		pos.setXY(coord);
		resp.setMessage(mensaje);
		resp.setPos(pos);

		return resp;
	}

	private static Satellite NuevoSatellite(String nombre, float distancia, String[] mensaje) {
		Satellite sat = new Satellite();
		sat.setName(nombre);
		sat.setDistance(distancia);
		sat.setMessage(mensaje);

		return sat;
	}

	private static Satellites GenerarSatellites(Satellite... sat) {
		Satellites sats = new Satellites();

		for (int i = 0; i < sat.length; i++) {
			sats.addSatellite(sat[i]);
		}

		return sats;

	}

	private Satellites sat;
	private ResponseDecoded resp;

	public DescifradorTest(Satellites sat, ResponseDecoded resp) {
		this.sat = sat;
		this.resp = resp;
	}

	@Test
	public void testProcesarInfo() {
		ResponseDecoded respuesta = Decoder.ProcesarInfo(sat);

		if (resp != null) {

			assertEquals(resp.getPos().getX(), respuesta.getPos().getX(), 0.05);
			assertEquals(resp.getPos().getY(), respuesta.getPos().getY(), 0.05);
			assertEquals(resp.getMessage(), respuesta.getMessage());
		} else {

			assertEquals(resp, respuesta);
		}

	}

}
