package org.util.fechas;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Fechas {
	
	public Fechas(){}
	
	public static DayOfWeek getDiaActual(){
		LocalDate localDate = LocalDate.now();
		 return localDate.getDayOfWeek();
	}
}
