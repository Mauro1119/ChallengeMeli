package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith (value = Parameterized.class)
public class PositionDecoderTest {

	@Parameters
	public static Iterable<Object[]> getData(){
		
		return Arrays.asList(new Object[][] {
			{new float[] {(float)565.68, (float)360.55, (float)608.27}, new float[] {(float)-100, (float)200}},
			{new float[] {(float)100, (float)100, (float)100}, new float[] {}}
						
		});
		
	}
	
	private float[] distances;
	private float[] resul;
	
	public PositionDecoderTest(float[] distances, float[] resul) {
		this.distances = distances;
		this.resul = resul;
	}
	
	
	@Test
	public void testGetLocation() {
		float[] resultado = PositionDecoder.GetLocation(distances);
		if (resultado.length == 0) {
			assertEquals(resul.length, resultado.length);				
			
		} else {
			assertEquals(resul[0], resultado[0], 0.05);
			assertEquals(resul[1], resultado[1], 0.05);
		}
		
	}

}
