package com.meli.quasar;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.meli.quasar.application.decoder.MessageDecoder;


@RunWith(value = Parameterized.class)
public class MessageDecoderTest {
	
	@Parameters
	public static Iterable<Object[]> getData() {		
		return Arrays.asList(new Object[][] {
			{new String[][]{{"este","", "un", "mensaje"},
							{"","es", "un", "mensaje"},
							{"","", "un", "mensaje"}}, "este es un mensaje"},
			
			{new String[][]{{"","", "un", "mensaje"},
							{"","es", "un", "mensaje"},
							{"","", "un", "mensaje"}}, null},
			
			{new String[][]{{"","", "un", "mensaje"},
							{"","es", "un", "mensaje"},
							{"","", "un", "mensaje"}}, null},
			
			{new String[][]{{"","este","", "un", "mensaje"},
							{"","","","es", "un", "mensaje"},
							{"","", "un", "mensaje"}}, "este es un mensaje"},
			
			{new String[][]{{""},
							{"","es", "un", "mensaje"},
							{"","", "un", "mensaje"}}, null}, //ver este caso
			
			{new String[][]{{"","", "un", "mensaje"}}, null},
			
			{new String[][]{{"este","es", "un", "mensaje"}}, "este es un mensaje"},
			
			{new String[][]{{"","", "", ""},
							{"","", "", ""},
							{"este","es", "un", "mensaje"}}, "este es un mensaje"},
			
			{new String[][]{{"","", "", ""},
							{"","", "", ""},
							{"","es", "un", "mensaje"}}, null},
			
			{new String[][]{{"","", "", ""},
							{"","", "", ""},
							{"","", "", ""}}, null},
			
			{new String[][]{}, ""},
		
		});
	}
	
	
	
	private String[][] mensaje;
	private String exp;
	
	public MessageDecoderTest(String[][] mensaje, String exp) {
		this.mensaje = mensaje;
		this.exp = exp;
	}
	
	
	@Test
	public void testGetMessage() {
		
		assertEquals(exp, MessageDecoder.GetMessage(mensaje));
	}

}
