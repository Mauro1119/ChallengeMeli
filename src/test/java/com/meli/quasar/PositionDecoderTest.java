package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.meli.quasar.application.services.PositionDecoderService;

@RunWith (value = Parameterized.class)
public class PositionDecoderTest {

	@Parameters
	public static Iterable<Object[]> getData(){
		
		return Arrays.asList(new Object[][] {
			{new float[] {(float)565.68, (float)360.55, (float)608.27}, new float[] {(float)-100, (float)200}},
			{new float[] {(float)1085.41, (float)626.99, (float)403.88}, new float[] {(float)350, (float)475}},
			{new float[] {(float)0, (float)0, (float)0}, new float[] {}},
			{new float[] {(float)100, (float)105.5, (float)142.7}, new float[] {}}, //ejemplo pdf
			{new float[] {(float)583.09, (float)412.31, (float)781.02}, new float[] {(float)0, (float)-500}},
			{new float[] {(float)538.516480, (float)141.421356, (float)509.901951}, new float[] {(float)0,(float)0}},
			{new float[] {(float)100, (float)105.5, (float)142.7}, new float[] {}},
			{new float[] {(float)100, (float)100, (float)100}, new float[] {}},
			{new float[] {(float)100, (float)100}, new float[] {}},
			{new float[] {(float)100}, new float[] {}},
			{new float[] {}, new float[] {}}
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
		float[] resultado = PositionDecoderService.GetLocation(distances);
		if (resultado.length == 0) {
			assertEquals(resul.length, resultado.length);				
			
		} else {
			assertEquals(resul[0], resultado[0], 0.05);
			assertEquals(resul[1], resultado[1], 0.05);
		}
		
	}

}
