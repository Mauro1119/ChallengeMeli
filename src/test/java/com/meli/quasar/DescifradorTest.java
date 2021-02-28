package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.meli.quasar.entitys.Position;
import com.meli.quasar.entitys.ResponseDecoded;
import com.meli.quasar.entitys.Satellite;
import com.meli.quasar.entitys.Satellites;

@RunWith(value = Parameterized.class)
public class DescifradorTest {

	@Parameters
	public static Iterable<Object[]> getData() {

		Satellites sats = new Satellites();
		Satellite sat = new Satellite();
		sat.setName("Kenobi");
		sat.setDistance(0);
		sat.setMessage(new String[]{"este","","","mensaje","",""});
		sats.addSatellite(sat);
		
		sat.setName("Skywalker");
		sat.setDistance(0);
		sat.setMessage(new String[]{"","es","","","ultra",""});
		
		sat.setName("Sato");
		sat.setDistance(0);
		sat.setMessage(new String[]{"","","un","","","secreto"});
		
		ResponseDecoded resp = new ResponseDecoded();
		Position pos = new Position();
		pos.setXY(new float[] {});
		resp.setMessage("");
		resp.setPos(pos);
		
		return Arrays.asList(new Object[][] {
				{sats, null}			
		});
	}
	
	
	private Satellites sat;
	private ResponseDecoded resp;

	public DescifradorTest(Satellites sat, ResponseDecoded resp) {
		this.sat = sat;
		this.resp = resp;
	}

	@Test
	public void testProcesarInfo() {
		ResponseDecoded respuesta = Descifrador.ProcesarInfo(sat);

		assertEquals(resp, respuesta);

	}

}
