package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import com.meli.quasar.application.resources.ResponseDecoded;
import com.meli.quasar.application.resources.SatelliteDto;
import com.meli.quasar.application.resources.SatellitesDto;
import com.meli.quasar.application.resources.mapper.ResponseDecodedMap;
import com.meli.quasar.application.resources.mapper.SatelliteDtoMap;
import com.meli.quasar.application.resources.mapper.SatellitesDtoMap;
import com.meli.quasar.application.services.*;
import com.meli.quasar.domain.entities.*;

@RunWith(value = Parameterized.class)
public class DecoderServiceTest {
	
	private SatellitesDto sat;
	private ResponseDecoded resp;	

	public DecoderServiceTest(SatellitesDto sat, ResponseDecoded resp) {
		this.sat = sat; //new SatellitesDto();
		sat.setSatellites(sat.getSatellites());
		this.resp = resp;
		
	}


	@Parameters
	public static Iterable<Object[]> getData() {

		return Arrays.asList(new Object[][] {
				{ GenerarSatellites(NuevoSatellite("Kenobi", 0, new String[] { "este", "", "", "mensaje", "", "" }),
						NuevoSatellite("Skywalker", 0, new String[] { "este", "", "", "mensaje", "", "" }),
						NuevoSatellite("Sato", 0, new String[] { "este", "", "", "mensaje", "", "" })), null },
				{ GenerarSatellites(
						NuevoSatellite("Sato", (double) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" }),
						NuevoSatellite("Skywalker", (double) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Kenobi", (double) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" })),
						RespuestaEsperada(new double[] { -100.0, 200.0 }, "este es un mensaje ultra secreto") },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (double) 1085.41,
								new String[] { "", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (double) 626.99,
								new String[] { "", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (double) 403.88,
								new String[] { "", "", "", "mensaje", "ultra", "" })), null},
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (double) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (double) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" })),	null},
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (double) 100,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (double) 115.5,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (double) 142.7,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new double[] { -58.31, -69.55 }, "este es un mensaje ultra secreto") },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (double) 583.09,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (double) 412.31,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (double) 781.02,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new double[] { 0, -500 }, "este es un mensaje ultra secreto") },
				{ GenerarSatellites(
						NuevoSatellite("Kenobi", (double) 565.68,
								new String[] { "este", "es", "", "mensaje", "", "secreto" }),
						NuevoSatellite("Skywalker", (double) 360.55,
								new String[] { "este", "", "un", "mensaje", "", "" }),
						NuevoSatellite("Sato", (double) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" }),
						NuevoSatellite("Otro", (double) 608.27,
								new String[] { "este", "", "", "mensaje", "ultra", "" })),
						RespuestaEsperada(new double[] { -100, 200 }, "este es un mensaje ultra secreto")}});

	}

	private static ResponseDecoded RespuestaEsperada(double[] coord, String mensaje) {
		ResponseDecoded resp = new ResponseDecoded();
		Position pos = new Position(coord);

		resp.setMessage(mensaje);
		resp.setPos(pos);

		return resp;
	}

	private static SatelliteDto NuevoSatellite(String name, double distance, String[] mensaje) {
		SatelliteDto sat = new SatelliteDto();
		sat.setName(name);
		sat.setDistance(distance);
		sat.setMessage(mensaje);

		return sat;
	}

	private static SatellitesDto GenerarSatellites(SatelliteDto... sat) {
		SatellitesDto sats = new SatellitesDto();
		
		sats.setSatellites(Arrays.asList(sat));
		return sats;

	}
	
	
	@Test
	public void testProcesarInfo() {	

		DecoderService decoderService = new DecoderService(new SatellitesDtoMap(new SatelliteDtoMap()), new ResponseDecodedMap());

		ResponseDecoded responseDecoded = new ResponseDecoded(); 
		responseDecoded =	decoderService.ProcesarInfo(this.sat);

		if (resp != null) {

			assertEquals(resp.getPos().getX(), responseDecoded.getPos().getX(), 0.05);
			assertEquals(resp.getPos().getY(), responseDecoded.getPos().getY(), 0.05);
			assertEquals(resp.getMessage(), responseDecoded.getMessage());
		} else {

			assertEquals(resp, responseDecoded);
		}

	}

}
