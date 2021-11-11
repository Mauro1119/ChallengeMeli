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
			{new double[] {565.68, 360.55, 608.27}, new double[] {-100, 200}},
			{new double[] {1085.41, 626.99, 403.88}, new double[] {350, 475}},
			{new double[] {0, 0, 0}, new double[] {1.05, -51.42}},
			{new double[] {100, 115.5, 142.7}, new double[] {-58.31, -69.55}}, //ejemplo pdf
			{new double[] {583.09, 412.31, 781.02}, new double[] {0,-500}},
			{new double[] {538.516480, 141.421356, 509.901951}, new double[] {0,0}},
			{new double[] {100, 105.5, 142.7}, new double[] {-57.66, -70.11}},
			{new double[] {100, 100, 100}, new double[] {0.21, -50.30}},
			{new double[] {100, 100}, new double[] {}},
			{new double[] {100}, new double[] {}},
			{new double[] {}, new double[] {}}
		});
		
	}
	
	private double[] distances;
	private double[] resul;
	
	public PositionDecoderTest(double[] distances, double[] resul) {
		this.distances = distances;
		this.resul = resul;
	}
	
	
	@Test
	public void testGetLocation() {
		double[][] positions = new double[][] {{-500.0,-200.0},{100.0,-100.0},{500.0,100.0}};
		double[] resultado = PositionDecoderService.GetLocation(positions, distances);
		if (resultado.length == 0) {
			assertEquals(resul.length, resultado.length);				
			
		} else {
			assertEquals(resul[0], resultado[0], 0.05);
			assertEquals(resul[1], resultado[1], 0.05);
		}
		
	}

}
