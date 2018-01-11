package co.com.domain;

import java.sql.Timestamp;
public class Estacionamiento {
	
	 private String placa;		 
	 private Vehiculo vehiculo;	
	 private Timestamp fechaHoraInicio; 
	 private String observacion;
	 
	 public String getPlaca() {
		 return this.placa;
	 }
	 public String getObservacion() {
		 return this.observacion;
	 }
	 public Timestamp getFechaHoraInicio() {
		 return this.fechaHoraInicio;
	 }
	 public Vehiculo getVehiculo() {
		 return this.vehiculo;
	 }
	 public void setVehiculo(Vehiculo vehiculo) {
		 this.vehiculo = vehiculo;
	 }
	 public Estacionamiento() {}	 
	 
	 public Estacionamiento(String placa, Timestamp fechahorainicio, String observacion) {
		 this.placa = placa;
		 this.fechaHoraInicio = fechahorainicio;
		 this.observacion = observacion;
	 }
}
