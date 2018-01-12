package org.excepcionestest;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class VehiculoExceptionTest {
	
	@Test
	public void VehiculoException(){
		//arrange
		
		//act
		org.excepciones.VehiculoException ex = new org.excepciones.VehiculoException("error");
		//assert
		assertNotNull(ex);
	}
	@Test
	public void VehiculoException2(){
		//arrange
		
		//act
		org.excepciones.VehiculoException ex = new org.excepciones.VehiculoException("error", new IllegalArgumentException(""));
		//assert
		assertNotNull(ex);
	}
}
