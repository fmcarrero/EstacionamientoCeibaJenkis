package org.util.fechasTest;

import static org.junit.Assert.assertNotNull;

import java.time.DayOfWeek;

import org.junit.Test;
import org.util.fechas.Fechas;

public class FechasTest {
	@Test
	public void getDiaActual() {
		//arrange		
		//act
		DayOfWeek day= new  Fechas().getDiaActual();
		//assert
		assertNotNull(day);
	}
}
