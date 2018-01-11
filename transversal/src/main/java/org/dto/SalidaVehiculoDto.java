package org.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalidaVehiculoDto {
	 private String placa;
	 private Timestamp fechaHoraFin;
	 
	 @JsonCreator
	 public SalidaVehiculoDto(@JsonProperty("placa") String placa,@JsonProperty("fechahorafin") Timestamp fechahorafin ) {
		 this.placa = placa;
		 this.fechaHoraFin = fechahorafin;
	 }
	public String getPlaca() {
		return placa;
	}
	
	public Timestamp getFechaHoraFin() {
		return fechaHoraFin;
	}	 
	 
}
