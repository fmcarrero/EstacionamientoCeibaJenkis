package co.com.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehiculo {
	
	 private String placa;	 
	 private String color; 
	 private long cilindraje;
	 private String observacion;
	 private Estacionamiento estacionamiento;
	 private int idTipoVehiculo;
	 private int tarifaDia =0;
	 private int tarifaHora=0;
	 private int tarifaAdicionalCilidndraje;
	
	 
	 public double getTarifaDia(){ return tarifaDia;}
	 public double getTarifaHora(){ return tarifaHora;}
	 public double getTarifaAdicionalCilidndraje() {return tarifaAdicionalCilidndraje;}
	 
	 public String getObservacion() {
		return observacion;
	}
	 public void setEstacionamiento(Estacionamiento estacionamiento){
		 this.estacionamiento = estacionamiento;
	 }

	public Vehiculo(String placa, String color, long cilindraje){
		 this.placa= placa;
		 this.color = color;
		 this.cilindraje = cilindraje;
	 }
	public Estacionamiento getEstacionamiento(){
		return this.estacionamiento;
	}

	public String getPlaca() {
		return placa;
	}

	public String getColor() {
		return color;
	}

	public long getCilindraje() {
		return cilindraje;
	}
	public int getIdTipoVehiculo(){
		return this.idTipoVehiculo;
	}
	
	@JsonCreator
	 protected Vehiculo(@JsonProperty("placa") String placa,@JsonProperty("color") String color, @JsonProperty("cilindraje") long cilindraje ,@JsonProperty("tipovehiculo") int idTipoVehiculo ,@JsonProperty("observacion")  String observacion) {
		 this.placa = placa;
		 this.color= color;
		 this.cilindraje = cilindraje;
		 this.idTipoVehiculo = idTipoVehiculo;
		 this.observacion =observacion;		 
	 }
}
