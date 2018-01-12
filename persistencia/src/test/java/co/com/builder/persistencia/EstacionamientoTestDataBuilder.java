package co.com.builder.persistencia;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.com.domain.Estacionamiento;
import co.com.domain.Vehiculo;



public class EstacionamientoTestDataBuilder {
	
	 private String placa;	 
	 private Vehiculo vehiculo;
	 private Timestamp FechaHoraInicio; 
	 private String Observacion;
	 
	 public EstacionamientoTestDataBuilder() {
		 //this.vehiculo = new VehiculoTestDataBuilder().buildMoto();
		 this.placa = "AXT-354";
		 this.Observacion = "ninguna";
		 this.FechaHoraInicio = Timestamp.valueOf(LocalDateTime.now());		 
	 }
	 public  EstacionamientoTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		 this.vehiculo = vehiculo;
		 return this;
	 }
	 public EstacionamientoTestDataBuilder withPlaca(String placa) {
		 this.placa = placa;
		 return this;
	 }
	 public EstacionamientoTestDataBuilder withFechaHoraInicio(Timestamp fechahorainicio) {
		 this.FechaHoraInicio = fechahorainicio;
		 return this;
	 }
	 public EstacionamientoTestDataBuilder withObservacion(String observacion) {
		 this.Observacion = observacion;
		 return this;
	 }
	 public Estacionamiento build() {
			return new Estacionamiento(this.placa,this.FechaHoraInicio,this.Observacion);
	 }
	 public List<Estacionamiento> buildListCarros(int size){
		 List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
		 for(int i =0;i<size;i++) {
			 Vehiculo  vehiculo = new VehiculoTestDataBuilder().withPlaca("axt-"+i).buildCarro();
			 Estacionamiento estacionamiento = new Estacionamiento(vehiculo.getPlaca(),this.FechaHoraInicio,"nada");
			 estacionamiento.setVehiculo(vehiculo);
			 lista.add(estacionamiento);
		 }
		 return lista;
	 } 
	 
}
